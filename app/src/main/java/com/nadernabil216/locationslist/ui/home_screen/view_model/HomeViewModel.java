package com.nadernabil216.locationslist.ui.home_screen.view_model;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nadernabil216.locationslist.base.BaseViewModel;
import com.nadernabil216.locationslist.callbacks.HomeCallback;
import com.nadernabil216.locationslist.database.LocationsReprosatory;
import com.nadernabil216.locationslist.databinding.ActivityMainBinding;

public class HomeViewModel extends BaseViewModel {
    private ActivityMainBinding binding;
    private HomeCallback homeCallback;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void setBinding(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public void setHomeCallback(HomeCallback homeCallback) {
        this.homeCallback = homeCallback;
    }

    public void getAllData(Context context) {
        LocationsReprosatory locationsReprosatory = new LocationsReprosatory(context);
        locationsReprosatory.getData().observe((LifecycleOwner) context, locations -> homeCallback.setData(locations));
    }

}
