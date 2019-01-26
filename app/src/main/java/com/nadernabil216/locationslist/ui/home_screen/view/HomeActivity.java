package com.nadernabil216.locationslist.ui.home_screen.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andrognito.flashbar.Flashbar;
import com.nadernabil216.locationslist.R;
import com.nadernabil216.locationslist.adapters.LocationsAdapter;
import com.nadernabil216.locationslist.callbacks.HomeCallback;
import com.nadernabil216.locationslist.databinding.ActivityMainBinding;
import com.nadernabil216.locationslist.models.objects.Location;
import com.nadernabil216.locationslist.ui.home_screen.view_model.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeCallback {
    private HomeViewModel viewModel;
    private ActivityMainBinding binding;
    private LocationsAdapter adapter;
    private ArrayList<Location> locations;
    private Flashbar progressFlashBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        viewModel.getAllData(this);
    }

    @Override
    public void initialization() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(viewModel);
        viewModel.setBinding(binding);
        viewModel.setHomeCallback(this);
        locations = new ArrayList<>();
        adapter = new LocationsAdapter(this, locations);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.rvLocations.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.rvLocations.setLayoutManager(mLayoutManager);
        binding.rvLocations.setItemAnimator(new DefaultItemAnimator());
        binding.rvLocations.setAdapter(adapter);
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

    }

    @Override
    public void finishActivity() {
        onBackPressed();
    }

    @Override
    public void setData(List<Location> locations) {
        if (!locations.isEmpty()) {
            binding.tvNoData.setVisibility(View.GONE);
            this.locations.addAll(locations);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
