package com.nadernabil216.locationslist.ui.add_new_location_screen.view;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.andrognito.flashbar.Flashbar;
import com.nadernabil216.locationslist.R;
import com.nadernabil216.locationslist.base.BaseCallBack;
import com.nadernabil216.locationslist.callbacks.AddNewLocationCallBack;
import com.nadernabil216.locationslist.databinding.ActivityAddNewLocationBinding;
import com.nadernabil216.locationslist.ui.add_new_location_screen.view_model.AddNewLocationViewModel;

public class AddNewLocationActivity extends AppCompatActivity implements AddNewLocationCallBack {
    private static final int MY_PERMISSIONS_REQUEST_Location = 100;
    private AddNewLocationViewModel viewModel;
    private ActivityAddNewLocationBinding binding;
    private Flashbar progressFlashBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        checkPermissions();
    }

    // check if location permission is granted or not , if granted get location and fetch altitude immediately
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_Location);

        } else {
            viewModel.getLocation();
        }
    }

    @Override
    public void initialization() {
        viewModel = ViewModelProviders.of(this).get(AddNewLocationViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_location);
        binding.setVm(viewModel);
        viewModel.setCallBack(this);
        viewModel.setContext(this);
        viewModel.setBinding(binding);
    }

    @Override
    public void showSnackBar(String msg) {
        new Flashbar.Builder(this)
                .title(R.string.alert)
                .message(msg)
                .enableSwipeToDismiss()
                .gravity(Flashbar.Gravity.BOTTOM)
                .messageSizeInSp(16f)
                .showIcon()
                .duration(5000)
                .show();

    }

    @Override
    public void showProgressDialog(boolean showProgress) {
        if (showProgress) {
            progressFlashBar = new Flashbar.Builder(this)
                    .gravity(Flashbar.Gravity.BOTTOM)
                    .title(R.string.gettingData)
                    .message(R.string.please_wait)
                    .backgroundColorRes(R.color.colorPrimaryDark)
                    .showProgress(Flashbar.ProgressPosition.RIGHT)
                    .progressTintRes(R.color.white)
                    .duration(3000)
                    .build();
            progressFlashBar.show();
        } else {
            progressFlashBar.dismiss();
        }

    }

    @Override
    public void showSnakeBarWithActionToFinishActivity(String msg) {
        new Flashbar.Builder(this)
                .title(R.string.alert)
                .message(msg)
                .gravity(Flashbar.Gravity.BOTTOM)
                .messageSizeInSp(16f)
                .showIcon()
                .positiveActionText("Close")
                .positiveActionTapListener(flashbar -> {
                   finishActivity();
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Location: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // location permission is granted
                    viewModel.getLocation();
                } else {
                    // location permission is not granted , so show snakebar with retry action
                    showSnakeBarWithActionToFinishActivity("Permission denied");
                }
                return;
            }
        }
    }

    @Override
    public void showSnakeBarWithAction(String msg) {
        new Flashbar.Builder(this)
                .title(R.string.alert)
                .message(msg)
                .gravity(Flashbar.Gravity.BOTTOM)
                .messageSizeInSp(16f)
                .showIcon()
                .positiveActionText("Retry")
                .positiveActionTapListener(flashbar -> {
                    flashbar.dismiss();
                    checkPermissions();
                })
                .show();
    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
