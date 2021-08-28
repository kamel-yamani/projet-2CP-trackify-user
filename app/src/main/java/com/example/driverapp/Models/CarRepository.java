package com.example.driverapp.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepository {
    private com.example.driverapp.Models.CarDao carDao;
    private LiveData<List<Car>> allCars;
    //private List<Car> carList;

    public CarRepository (Application application){
        CarDb carDatabase = CarDb.getInstance(application);
        carDao = carDatabase.carDao();
        allCars = carDao.getAllCars();
        //carList = carDao.getCarList();
    }

    public void insert (Car car) {
        new InsertCarAsyncTask(carDao).execute(car);
    }

    public void update (Car car) {
        new UpdateCarAsyncTask(carDao).execute(car);
    }

    public void delete (Car car){
        new DeleteCarAsyncTask(carDao).execute(car);
    }

    public LiveData<List<Car>> getAllCars (){
        return allCars;
    }

    //public List<Car> getCarList () {return carList; }

    private static class InsertCarAsyncTask extends AsyncTask<Car, Void, Void> {
        private com.example.driverapp.Models.CarDao carDao;

        public InsertCarAsyncTask (com.example.driverapp.Models.CarDao carDao){
            this.carDao = carDao;
        }
        @Override
        protected Void doInBackground(Car... cars) {
            carDao.insert(cars[0]);
            return null;
        }
    }

    private static class UpdateCarAsyncTask extends AsyncTask<Car, Void, Void> {
        private com.example.driverapp.Models.CarDao carDao;

        public UpdateCarAsyncTask (com.example.driverapp.Models.CarDao carDao){
            this.carDao = carDao;
        }
        @Override
        protected Void doInBackground(Car... cars) {
            carDao.update(cars[0]);
            return null;
        }
    }

    private static class DeleteCarAsyncTask extends AsyncTask<Car, Void, Void> {
        private com.example.driverapp.Models.CarDao carDao;

        public DeleteCarAsyncTask (com.example.driverapp.Models.CarDao carDao){
            this.carDao = carDao;
        }
        @Override
        protected Void doInBackground(Car... cars) {
            carDao.delete(cars[0]);
            return null;
        }
    }


}
