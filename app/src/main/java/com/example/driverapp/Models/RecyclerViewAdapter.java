package com.example.driverapp.Models;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.telephony.SmsManager;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.driverapp.Fragments.ListFragment;
import com.example.driverapp.Fragments.MapFragment;
import com.example.driverapp.MainActivity;
import com.example.driverapp.R;

import org.jetbrains.annotations.NotNull;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    public List<Car> carList = new ArrayList<Car>();


    Context context;
    MainActivity mainActivity;

    public RecyclerViewAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        setCars(MainActivity.myCarsList);
    }

    public void setCars (List<Car> cars){
        this.carList = cars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_list_item,parent,false);
        context = view.getContext();
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(position == 0){
            holder.expandedCarView.setVisibility(View.VISIBLE);
            holder.localiserRacco.setVisibility(View.GONE);
            holder.letter.setVisibility(View.GONE);
            holder.smallPhoneNumber.setVisibility(View.GONE);
        }
        holder.marqueModele.setText(carList.get(position).getMarque() +" " +carList.get(position).getModele());
        holder.matricule.setText(carList.get(position).getMatricule());
        holder.codeSecret.setText(carList.get(position).getCodeSecret());
        holder.numTele.setText(carList.get(position).getNumTele());
        holder.smallPhoneNumber.setText(carList.get(position).getNumTele());
        holder.letter.setText(String.valueOf(carList.get(position).getModele().charAt(0)));
        Resources res = context.getResources();
        Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.car, null);

        holder.localiserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                si la voiture selectionee est jamais trackee avant on met la date corrante
                sinon on modifie pas sa date de derniere localisation, parece que elle est modifiee
                quand on click sur le button curseur (in center) sur la map
                */
                if (carList.get(position).getLastTrackDate() == null){
                    carList.get(position).setLastTrackDate(new Date(System.currentTimeMillis()).toLocaleString());
                }

                MainActivity.currTrackedCar = carList.get(position);
                mainActivity.setMapFragment();
                mainActivity.btmNavView.setSelectedItemId(R.id.mapButton);
                if(MainActivity.currTrackedCar.getLastLocationLat() == null || MainActivity.currTrackedCar.getLastLocationLng() == null){
                    MainActivity.mapFragment = new MapFragment();
                    mainActivity.setMapFragment();
                }

//                MainActivity.mapFragment.showCarCurrentLocation();
            }
        });
        holder.localiserRacco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                si la voiture selectionee est jamais trackee avant on met la date corrante
                sinon on modifie pas sa date de derniere localisation, parece que elle est modifiee
                quand on click sur le button curseur (in center) sur la map
                */
                if (carList.get(position).getLastTrackDate() == null){
                    carList.get(position).setLastTrackDate(new Date(System.currentTimeMillis()).toLocaleString());
                }
                MainActivity.currTrackedCar = carList.get(position);
                mainActivity.setMapFragment();
                mainActivity.btmNavView.setSelectedItemId(R.id.mapButton);
                if(MainActivity.currTrackedCar.getLastLocationLat() == null || MainActivity.currTrackedCar.getLastLocationLng() == null){
                    MainActivity.mapFragment = new MapFragment();
                    mainActivity.setMapFragment();
                }
//                MainActivity.mapFragment.showCarCurrentLocation();

            }
        });

        holder.enable4g.setChecked(carList.get(position).getSwitchState());
        holder.enable4g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Car car = carList.get(position);
                if (isChecked){

                    sendSMS( car.getNumTele(),
                            car.getCodeSecret() + "enable4g");

                    car.setSwitchState(true);

                    Toast.makeText(context, "Position Collection with 4G Enabled", Toast.LENGTH_LONG).show();
                }else{

                    sendSMS( car.getNumTele(),
                            car.getCodeSecret() + "disable4g");

                    car.setSwitchState(false);

                    Toast.makeText(context, "Position Collection with 4G Disabled", Toast.LENGTH_LONG).show();
                }

                MainActivity.myCars.update(car);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView marqueModele;
        TextView matricule;
        TextView codeSecret;
        TextView numTele;
        ConstraintLayout expandedCarView;
        CardView cardView;
        Button localiserBtn;
        ImageButton localiserRacco;
        TextView letter;
        TextView smallPhoneNumber;
        Switch enable4g;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            marqueModele = itemView.findViewById(R.id.marque_modele);
            matricule = itemView.findViewById(R.id.value_matricule);
            codeSecret = itemView.findViewById(R.id.value_code_secret);
            numTele = itemView.findViewById(R.id.value_num_tele);
            localiserBtn = itemView.findViewById(R.id.localiser_btn);
            expandedCarView = itemView.findViewById(R.id.expanded_cardView);
            cardView = itemView.findViewById(R.id.cardView);
            localiserRacco = itemView.findViewById(R.id.locate_racco);
            letter = itemView.findViewById(R.id.letter);
            smallPhoneNumber = itemView.findViewById(R.id.small_phoneNumber);
            enable4g = itemView.findViewById(R.id.enable_4g);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandedCarView.getVisibility()==View.GONE){
                        TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                        expandedCarView.setVisibility(View.VISIBLE);
                        localiserRacco.setVisibility(View.GONE);
                        letter.setVisibility(View.GONE);
                        smallPhoneNumber.setVisibility(View.GONE);
                    }
                    else{
                        TransitionManager.beginDelayedTransition(cardView,new AutoTransition());
                        expandedCarView.setVisibility(View.GONE);
                        localiserRacco.setVisibility(View.VISIBLE);
                        letter.setVisibility(View.VISIBLE);
                        smallPhoneNumber.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
    }

    //filtering operation
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            HashMap<String, Car> allCars = new HashMap<String, Car>();
            List<Car> filtredList = new ArrayList<>();

            for (Car car: MainActivity.myCarsList) {
                allCars.put(car.getMarque()+" "+car.getModele(), car);
            }

            for (String carName: allCars.keySet()) {
                if (carName.toLowerCase().contains(constraint.toString().toLowerCase())) {
                    filtredList.add(allCars.get(carName));
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filtredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            setCars((List<Car>) results.values);

        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    public void sendSMS(String phoneNo,String SMS ) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, SMS, null, null);

        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }
    }
}
