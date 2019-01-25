package com.nadernabil216.locationslist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nadernabil216.locationslist.models.objects.Location;

@Database(entities = {Location.class}, version = 1)
public abstract class LocationDataBase extends RoomDatabase {

    private static LocationDataBase INSTANCE;

    static LocationDataBase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocationDataBase.class, "locations_room")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    public abstract LocationDao movieDao();
}
