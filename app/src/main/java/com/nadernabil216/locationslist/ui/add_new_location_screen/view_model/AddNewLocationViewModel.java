package com.nadernabil216.locationslist.ui.add_new_location_screen.view_model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nadernabil216.locationslist.base.BaseViewModel;
import com.nadernabil216.locationslist.callbacks.AddNewLocationCallBack;
import com.nadernabil216.locationslist.database.LocationsReprosatory;
import com.nadernabil216.locationslist.databinding.ActivityAddNewLocationBinding;
import com.nadernabil216.locationslist.models.objects.Location;
import com.nadernabil216.locationslist.models.responses.AltitudeResponse;
import com.nadernabil216.locationslist.rest.RetrofitClient;
import com.nadernabil216.locationslist.utils.Constants;
import com.nadernabil216.locationslist.utils.GeneralMethods;

import locationprovider.davidserrano.com.LocationProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewLocationViewModel extends BaseViewModel {
    private AddNewLocationCallBack callBack;
    private LocationsReprosatory reprosatory;
    private ActivityAddNewLocationBinding binding;
    private Context context;
    private Location location = new Location();
    private  LocationProvider locationProvider ;

    public AddNewLocationViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCallBack(AddNewLocationCallBack callBack) {
        this.callBack = callBack;
    }

    public void setBinding(ActivityAddNewLocationBinding binding) {
        this.binding = binding;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // using location provider external library to help fetching location from gsp / network according to phone settings
    public void getLocation() {
        callBack.showProgressDialog(true);
        //create a callback
        LocationProvider.LocationCallback callback = new LocationProvider.LocationCallback() {
            @Override
            public void onNewLocationAvailable(float lat, float lon) {
                //location update
            }

            @Override
            public void locationServicesNotEnabled() {
                callBack.showSnakeBarWithAction("Gps is closed");
            }

            @Override
            public void updateLocationInBackground(float lat, float lon) {
                //if a listener returns after the main locationAvailable callback, it will go here
                location.setLat(lat);
                location.setLng(lon);
                locationProvider.onLocationPause();
                getAltitudeFromApi();
            }

            @Override
            public void networkListenerInitialised() {
                //when the library switched from GPS only to GPS & network
            }

            @Override
            public void locationRequestStopped() {

            }
        };

        //initialise an instance with the two required parameters
        locationProvider = new LocationProvider.Builder()
                .setContext(context)
                .setListener(callback)
                .create();

        //start getting location
        locationProvider.requestLocation();
    }


    // fetch altitude using doodle geo / maps api
    public void getAltitudeFromApi() {
        @SuppressLint("DefaultLocale") String latlngString = String.format("%f,%f", location.getLat(), location.getLng());
        RetrofitClient.getInstance().getApi().getAltitiude(latlngString, Constants.ConstantsKeys.apiKey).enqueue(new Callback<AltitudeResponse>() {
            @Override
            public void onResponse(Call<AltitudeResponse> call, Response<AltitudeResponse> response) {
                callBack.showProgressDialog(false);
                location.setAlt(response.body().getResults().get(0).getElevation());
                setDateDataInView();
            }

            @Override
            public void onFailure(Call<AltitudeResponse> call, Throwable t) {

            }
        });
    }

    // set fetched data into view throw binding instance
    private void setDateDataInView(){
        location.setCurrentDate(GeneralMethods.getInstance().getCurrentDate());
        binding.edLatitude.setText(String.valueOf(location.getLat()) );
        binding.edLongitude.setText(String.valueOf(location.getLng()) );
        binding.edAltitude.setText(String.valueOf(location.getAlt()) );
        binding.edDate.setText(location.getCurrentDate());
    }

    public View.OnClickListener cancelAdding(){
        return v -> callBack.finishActivity();
    }

    public View.OnClickListener saveData() {
        return v -> {
            if (location.getAlt()==0.0){
                callBack.showSnackBar("Wait until location is fetched");
            }else {
                showSetTitleDialog();
            }
           };
    }

    //showing popup dialog to enter location title then save it
    private void showSetTitleDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Location Title");
        alert.setMessage("Please enter a title for this location ");

        final EditText input = new EditText(context);
        alert.setView(input);

        alert.setPositiveButton("Ok", (dialog, whichButton) -> {
            String title = input.getText().toString();
            if (title.isEmpty()) {
                Toast.makeText(context, "Empty title", Toast.LENGTH_SHORT).show();
            } else {
               setTitleAndSaveLocation(title);
            }
            return;
        });

        alert.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
            return;
        });
        alert.show();
    }

    // set title from dialog to location instance and save the instance to room database
    private void setTitleAndSaveLocation(String title){
        location.setTitle(title);
        reprosatory=new LocationsReprosatory(context);
        reprosatory.insert(location);
        callBack.showSnakeBarWithActionToFinishActivity("Location added to database");
    }

}
