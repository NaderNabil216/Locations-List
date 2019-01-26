package com.nadernabil216.locationslist.rest;


import com.nadernabil216.locationslist.models.responses.AltitudeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/maps/api/elevation/json")
    Call<AltitudeResponse> getAltitiude (@Query("locations") String latAndLng, @Query("key") String key);
}
