package com.nadernabil216.locationslist.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.content.Context;
import android.os.AsyncTask;

import com.nadernabil216.locationslist.models.objects.Location;

import java.util.List;

public class LocationsReprosatory {
    private LocationDao dao;
    private Context context;

    public LocationsReprosatory(Context context) {
        this.context = context;
        LocationDataBase dataBase = LocationDataBase.getINSTANCE(context);
        dao = dataBase.movieDao();
    }

    public LiveData<List<Location>> getData() {
        return dao.getAllLocations();
    }

    public void insert(Location location) {
        new insertAsyncTask(dao).execute(location);
    }

    public LiveData<Location> getLocation (int locationId) {
        return dao.getLocation(locationId);
    }

    public void delete() {
        dao.deleteAll();
    }

    private static class insertAsyncTask extends AsyncTask<Location, Void, Void> {

        private LocationDao mAsyncTaskDao;

        insertAsyncTask(LocationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Location... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
