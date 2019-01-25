package com.nadernabil216.locationslist.ui.home_screen.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nadernabil216.locationslist.R;
import com.nadernabil216.locationslist.base.BaseCallBack;

public class HomeActivity extends AppCompatActivity implements BaseCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initialization() {

    }

    @Override
    public void showSnackBar(String msg) {

    }

    @Override
    public void showProgressDialog(boolean showProgress) {

    }

    @Override
    public void showSnakeBarWithActionToFinishActivity(String msg) {

    }

    @Override
    public void finishActivity() {

    }
}
