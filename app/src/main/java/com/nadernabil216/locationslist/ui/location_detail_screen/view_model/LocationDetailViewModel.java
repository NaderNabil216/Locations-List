package com.nadernabil216.locationslist.ui.location_detail_screen.view_model;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nadernabil216.locationslist.base.BaseViewModel;
import com.nadernabil216.locationslist.callbacks.LocationDetailCallBack;
import com.nadernabil216.locationslist.database.LocationsReprosatory;
import com.nadernabil216.locationslist.models.objects.Location;

public class LocationDetailViewModel extends BaseViewModel {
    private LocationDetailCallBack locationDetailCallBack ;
    private LocationsReprosatory locationsReprosatory ;
    public LocationDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void setLocationDetailCallBack(LocationDetailCallBack locationDetailCallBack) {
        this.locationDetailCallBack = locationDetailCallBack;
    }

    public void getLocationDetail(Context context , int locationId){
        locationsReprosatory = new LocationsReprosatory(context);
        locationsReprosatory.getLocation(locationId).observe((LifecycleOwner) context, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                locationDetailCallBack.setDataAndDisableFields(location);
            }
        });
    }
}
