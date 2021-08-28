package com.example.driverapp.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.driverapp.MainActivity;
import com.example.driverapp.Models.Car;
import com.example.driverapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    ImageButton btnClrMarkers;
    private GoogleMap mMap;
    private Geocoder geocoder;
    private int ACCESS_LOCATION_REQUEST_CODE = 10001;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;

    Marker userLocationMarker;
    Circle userLocationAccuracyCircle;

    public MapFragment(){

    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            /**
             * this map fragment is called in one of two manners
             * 1: if the user navigates to the map without selecting a car (directly from the btm menu)
             *here we figure out two cases:
             *1.a- if the current tracked car (static) in the main activity is null: we don't show(the marker and the car details)
             *1.b- else : we show the car details, and:
             *1.b.1- if the car coordinates != null: we show them with a (previous_location_marker)
             *1_b.2- else: we don't show the marker (but car details still shown)
             * 2: if the user selects a car to track(by clicking on "view on map button"(or its shortcut in collapsed version),
             or by swiping to right): this car becomes the current tracked car (static) in the main activity, and we repeat 1.b;
             * 3: now when the user is on the map, and the car details of the car is shown and he clicked on the CURSOR
             -> the message is sent to the car phone number, and the sms Listener waits for the location sent over sms
             -> when we get the coordinates, we pin a (current_location_marker)
             */
            mMap = googleMap;
            addCurrentTrackedCarMarker(googleMap);
            clearPreviousMarkers(googleMap);
        }

        public void clearPreviousMarkers(GoogleMap googleMap){
            btnClrMarkers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    googleMap.clear();
                    addCurrentTrackedCarMarker(googleMap);
                }
            });
        }
        public void addCurrentTrackedCarMarker(GoogleMap googleMap){
            if (MainActivity.currTrackedCar != null) {
                if (MainActivity.currTrackedCar.getLastLocationLat() != null && MainActivity.currTrackedCar.getLastLocationLng() != null) {

                    Car trackedCar = MainActivity.currTrackedCar;
                    LatLng latLng = new LatLng(trackedCar.getLastLocationLat(), trackedCar.getLastLocationLng()); //cord.lat et cord.lng contient les 2 coordonées
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(trackedCar.getMarque() + " " + trackedCar.getModele()).icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker)));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16), 3000, null);
                }
            }
        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        btnClrMarkers = v.findViewById(R.id.clearMarkers);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showCarCurrentLocation();
    }

    public void showCarCurrentLocation(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
            geocoder = new Geocoder(getContext());
        }
    }

    //from here i was working for

    public void fetchCurrentCarLocation(){

    }

    private void setUserLocationMarker(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        if (userLocationMarker == null) {
            //Create a new marker
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_as_marker));
            markerOptions.rotation(location.getBearing());
            markerOptions.anchor((float) 0.5, (float) 0.5);
            userLocationMarker = mMap.addMarker(markerOptions);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        } else  {
            //use the previously created marker
            userLocationMarker.setPosition(latLng);
            userLocationMarker.setRotation(location.getBearing());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        }

        if (userLocationAccuracyCircle == null) {
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(latLng);
            circleOptions.strokeWidth(4);
            circleOptions.strokeColor(R.color.violet);
            circleOptions.fillColor(R.color.medium_grey);
            circleOptions.radius(location.getAccuracy());
            userLocationAccuracyCircle = mMap.addCircle(circleOptions);
        } else {
            userLocationAccuracyCircle.setCenter(latLng);
            userLocationAccuracyCircle.setRadius(location.getAccuracy());
        }
    }



}