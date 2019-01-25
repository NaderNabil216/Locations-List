package com.nadernabil216.locationslist.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AltitudeResponse {
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    class Location {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

    }

    class Result {

        @SerializedName("elevation")
        @Expose
        private Double elevation;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("resolution")
        @Expose
        private Double resolution;

        public Double getElevation() {
            return elevation;
        }

        public void setElevation(Double elevation) {
            this.elevation = elevation;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Double getResolution() {
            return resolution;
        }

        public void setResolution(Double resolution) {
            this.resolution = resolution;
        }
    }
}
