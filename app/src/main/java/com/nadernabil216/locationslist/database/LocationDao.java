package com.nadernabil216.locationslist.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.graphics.Movie;


import com.nadernabil216.locationslist.models.objects.Location;

import java.util.List;

@Dao
public interface LocationDao {

    @Insert
    void insert(Location Location);

    @Insert
    void insertAllData(List<Location> locations);

    @Query("SELECT * FROM location_table LIMIT 1")
    Location getAnyLocation ();

    @Query("SELECT * FROM location_table")
    LiveData<List<Location>> getAllLocations ();

    @Query("SELECT * FROM location_table WHERE id = :locationId")
    LiveData<Location> getLocation(int locationId);

    @Query("DELETE FROM location_table WHERE id = :locationId")
    void deleteMovie(int locationId);

    @Query("DELETE FROM location_table")
    void deleteAll();


}
