package com.nadernabil216.locationslist.ui.home_screen.view_model;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.nadernabil216.locationslist.base.BaseViewModel;
import com.nadernabil216.locationslist.callbacks.HomeCallback;
import com.nadernabil216.locationslist.database.LocationsReprosatory;
import com.nadernabil216.locationslist.databinding.ActivityMainBinding;
import com.nadernabil216.locationslist.models.objects.Location;
import com.nadernabil216.locationslist.ui.add_new_location_screen.view.AddNewLocationActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HomeViewModel extends BaseViewModel {
    private ActivityMainBinding binding;
    private HomeCallback homeCallback;
    private Context context ;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void setBinding(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public void setHomeCallback(HomeCallback homeCallback) {
        this.homeCallback = homeCallback;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // get all data from database in insertion order then sorting data separately
    public void getAllData(Context context) {
        LocationsReprosatory locationsReprosatory = new LocationsReprosatory(context);
        locationsReprosatory.getData().observe((LifecycleOwner) context, locations ->{
            List<Location> sortedLocations = sortArray(locations);
            homeCallback.setData(sortedLocations);
        } );
    }

    //using java built in sorting with comparator ( quick sort )
    private List<Location> sortArray(List<Location> locations){
        Location[] unSortedArray = locations.toArray(new Location[locations.size()]);
        Arrays.sort(unSortedArray, (location, t1) -> {
            return location.getTitle().compareToIgnoreCase(t1.getTitle()); // To compare string values
        });
        return new ArrayList<Location>(Arrays.asList(unSortedArray));
    }

    public View.OnClickListener addNewLocation() {
        return view -> context.startActivity(new Intent(context,AddNewLocationActivity.class));
    }
}
