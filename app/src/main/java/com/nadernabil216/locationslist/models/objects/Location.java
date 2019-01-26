package com.nadernabil216.locationslist.models.objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "location_table")
public class Location {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int _id;

    @ColumnInfo(name = "lat")
    private double lat = 0.0;

    @ColumnInfo(name = "lng")
    private double lng = 0.0;

    @ColumnInfo(name = "alt")
    private double alt = 0.0;

    @ColumnInfo(name = "currentDate")
    private String currentDate = "";
    @NonNull
    @ColumnInfo(name = "title")
    private String title = "";

    public Location() {
    }

    public Location(double lat, double lng, double alt, String currentDate, String title) {
        this.lat = lat;
        this.lng = lng;
        this.alt = alt;
        this.currentDate = currentDate;
        this.title = title;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
