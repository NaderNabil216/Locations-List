package com.nadernabil216.locationslist.models.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nadernabil216.locationslist.models.responses.AltitudeResponse;

public  class Result {

    @SerializedName("elevation")
    @Expose
    private Double elevation;
    @SerializedName("location")
    @Expose
    private ResultLocation location;
    @SerializedName("resolution")
    @Expose
    private Double resolution;

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public ResultLocation getLocation() {
        return location;
    }

    public void setLocation(ResultLocation location) {
        this.location = location;
    }

    public Double getResolution() {
        return resolution;
    }

    public void setResolution(Double resolution) {
        this.resolution = resolution;
    }
}