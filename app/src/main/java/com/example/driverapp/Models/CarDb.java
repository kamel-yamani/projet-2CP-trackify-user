package com.example.driverapp.Models;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database( entities = Car.class, version = 1, exportSchema = false)
public abstract class CarDb extends RoomDatabase {

    private static CarDb instance;

    public abstract CarDao carDao();

    public static synchronized CarDb getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CarDb.class, "car_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void> {
        private CarDao carDao;

        private PopulateDbAsyncTask(CarDb carDb){
            carDao = carDb.carDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
