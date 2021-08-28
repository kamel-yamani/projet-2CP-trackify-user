package com.example.driverapp.Models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarViewModel extends AndroidViewModel {

    CarRepository repository;
    LiveData<List<Car>> allCars;
    //List<Car> carList;

    public CarViewModel(Application application){
        super(application);
        repository = new CarRepository(application);
        allCars = repository.getAllCars();
    }

    public void insert (Car car) {
        repository.insert(car);
    }

    public void update (Car car) {
        repository.update(car);
    }

    public void delete (Car car) {
        repository.delete(car);
    }

    public LiveData<List<Car>> getAllCars () {
        return repository.getAllCars();
    }

    //public List<Car> getCarList () { return repository.getCarList(); }
}
