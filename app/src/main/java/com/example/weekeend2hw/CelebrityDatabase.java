package com.example.weekeend2hw;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Celebrity.class}, version = 2) //  defines what tables we want to put in. when we make data changes, increment version
public abstract class CelebrityDatabase extends RoomDatabase {

    private static CelebrityDatabase instance;// turns this class into a singleton

    public abstract CelebrityDao celebrityDao();  // use to access DAO

    public static synchronized CelebrityDatabase getInstance(Context context){ // makes singleton only one thread at a time can access.

        if (instance == null){ //checks if an instance all ready exists
            instance = Room.databaseBuilder(context.getApplicationContext(),CelebrityDatabase.class, "celebrity_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){ //populates one field in the database

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
       private CelebrityDao celebrityDao;

       private PopulateDbAsyncTask(CelebrityDatabase db){
           celebrityDao = db.celebrityDao();
       }

        @Override
        protected Void doInBackground(Void... voids) {
           celebrityDao.insert(new Celebrity("Celebrity Name 1","What this Celebrity is known for", 0));
            return null;
        }
    }



}
