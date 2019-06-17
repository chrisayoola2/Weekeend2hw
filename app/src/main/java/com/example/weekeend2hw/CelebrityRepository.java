package com.example.weekeend2hw;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CelebrityRepository {
    private CelebrityDao celebrityDao;
    private LiveData<List<Celebrity>> allCelebrities;


    public CelebrityRepository(Application application) { // constructor for variables
        CelebrityDatabase database = CelebrityDatabase.getInstance(application);
        celebrityDao = database.celebrityDao(); // abstract method that was created in daatabase class
        allCelebrities = celebrityDao.getAllCelebrity(); // abstract method created in DAO interface

    }

    public void insert(Celebrity celebrity) {
        new InsertCelebrityAsyncTask(celebrityDao).execute(celebrity);

    }

    public void update(Celebrity celebrity) {
        new UpdateCelebrityAsyncTask(celebrityDao).execute(celebrity); //Api

    }

    public void delete(Celebrity celebrity) {
        new DeleteCelebrityAsyncTask(celebrityDao).execute(celebrity);
    }

    public void deleteAllNotes() {
        new DeleteAllCelebrityAsyncTask(celebrityDao).execute();
    }

    public LiveData<List<Celebrity>> getAllCelebrities() {
        return allCelebrities;
    }

    private static class InsertCelebrityAsyncTask extends AsyncTask<Celebrity, Void, Void> { //static so

        private CelebrityDao celebrityDao;

        private InsertCelebrityAsyncTask(CelebrityDao celebrityDao) {
            this.celebrityDao = celebrityDao;
        }

        @Override
        protected Void doInBackground(Celebrity... celebrities) {
            celebrityDao.insert(celebrities[0]);
            return null;
        }
    }

    private static class UpdateCelebrityAsyncTask extends AsyncTask<Celebrity, Void, Void> { //static so

        private CelebrityDao celebrityDao;

        private UpdateCelebrityAsyncTask(CelebrityDao celebrityDao) {
            this.celebrityDao = celebrityDao;
        }

        @Override
        protected Void doInBackground(Celebrity... celebrities) {
            celebrityDao.update(celebrities[0]);
            return null;
        }
    }

    private static class DeleteCelebrityAsyncTask extends AsyncTask<Celebrity, Void, Void> { //static so

        private CelebrityDao celebrityDao;

        private DeleteCelebrityAsyncTask(CelebrityDao celebrityDao) {
            this.celebrityDao = celebrityDao;
        }

        @Override
        protected Void doInBackground(Celebrity... celebrities) {
            celebrityDao.delete(celebrities[0]);
            return null;
        }
    }

    private static class DeleteAllCelebrityAsyncTask extends AsyncTask<Void, Void, Void> { //static so

        private CelebrityDao celebrityDao;

        private DeleteAllCelebrityAsyncTask(CelebrityDao celebrityDao) {
            this.celebrityDao = celebrityDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            celebrityDao.deleteAllCelebrity();
            return null;
        }
    }


}
