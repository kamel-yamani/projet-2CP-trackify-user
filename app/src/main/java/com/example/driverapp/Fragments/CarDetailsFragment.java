package com.example.driverapp.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.driverapp.Models.Car;
import com.example.driverapp.R;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class CarDetailsFragment extends Fragment {

    TextView carName,carPhone,date,hour,minute;
    View mfragment;
    Car thisCar;

    public CarDetailsFragment(Car car) {
       thisCar = car;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mfragment =inflater.inflate(R.layout.fragment_car_details, container, false);

        return mfragment;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carName = mfragment.findViewById(R.id.infoName);
        carPhone = mfragment.findViewById(R.id.infoPhone);
        date = mfragment.findViewById(R.id.infoDate);
        hour = mfragment.findViewById(R.id.infoTimeHour);
        minute = mfragment.findViewById(R.id.infoTimeMin);

        carName.setText(thisCar.getMarque()+ " "+thisCar.getModele());
        carPhone.setText(thisCar.getNumTele());

        SimpleDateFormat fd = new SimpleDateFormat("dd. MM. yyyy");
        SimpleDateFormat fh = new SimpleDateFormat("HH");
        SimpleDateFormat fm = new SimpleDateFormat("mm");

        date.setText(fd.format(Date.parse(thisCar.getLastTrackDate())));
        hour.setText(fh.format(Date.parse(thisCar.getLastTrackDate())));
        minute.setText(fm.format(Date.parse(thisCar.getLastTrackDate())));


    }


}