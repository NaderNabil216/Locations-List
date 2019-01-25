package com.nadernabil216.locationslist.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

public class BaseViewModel extends AndroidViewModel implements Observable {

    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    protected void notifyDataChanged() {
        registry.notifyCallbacks(this, 0, null);
    }
}
