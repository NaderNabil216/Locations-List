package com.nadernabil216.locationslist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class GeneralMethods {

    private static final GeneralMethods ourInstance = new GeneralMethods();

    private GeneralMethods() {
    }

    public static GeneralMethods getInstance() {
        return ourInstance;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String getCurrentDate() {
        Locale locale = new Locale("en");
        long dates = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
        return sdf.format(dates);
    }

}
