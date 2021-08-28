package com.example.driverapp.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void insert (Car car);

    @Update
    void update (Car car);

    @Delete
    void delete (Car car);

    @Query("SELECT * FROM car_table ORDER BY marque")
    LiveData<List<Car>> getAllCars ();


}
