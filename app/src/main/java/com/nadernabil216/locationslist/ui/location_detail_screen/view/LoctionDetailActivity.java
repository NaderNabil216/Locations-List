package com.nadernabil216.locationslist.ui.location_detail_screen.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nadernabil216.locationslist.R;
import com.nadernabil216.locationslist.callbacks.LocationDetailCallBack;
import com.nadernabil216.locationslist.databinding.ActivityLoctionDetailBinding;
import com.nadernabil216.locationslist.models.objects.Location;
import com.nadernabil216.locationslist.ui.location_detail_screen.view_model.LocationDetailViewModel;
import com.nadernabil216.locationslist.utils.Constants;

public class LoctionDetailActivity extends AppCompatActivity implements LocationDetailCallBack {
    private LocationDetailViewModel viewModel;
    private ActivityLoctionDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        int locationId = getIntent().getIntExtra(Constants.IntentKeys.locationId, 1);
        viewModel.getLocationDetail(this, locationId);
    }

    @Override
    public void initialization() {
        viewModel = ViewModelProviders.of(this).get(LocationDetailViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loction_detail);
        viewModel.setLocationDetailCallBack(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setDataAndDisableFields (Location location) {
        binding.edName.setText(location.getTitle());
        binding.edDate.setText(location.getCurrentDate());
        binding.edLatitude.setText(String.valueOf(location.getLat()));
        binding.edLongitude.setText(String.valueOf(location.getLng()));
        binding.edAltitude.setText(String.valueOf(location.getAlt()));

        binding.edName.setEnabled(false);
        binding.edDate.setEnabled(false);
        binding.edLatitude.setEnabled(false);
        binding.edLongitude.setEnabled(false);
        binding.edAltitude.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
