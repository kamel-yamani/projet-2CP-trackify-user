package com.example.driverapp.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "car_table")
public class Car implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    private String marque;
    private String modele;
    private String matricule;
    private String codeSecret;
    private String numTele;
    private String lastTrackDate ;
    private Double lastLocationLng;
    private Double lastLocationLat;
    private boolean switchState;



    public Car(String marque, String modele, String matricule, String codeSecret, String numTele, boolean switchState) {
        this.marque = marque;
        this.modele = modele;
        this.matricule = matricule;
        this.codeSecret = codeSecret;
        this.numTele = numTele;
        this.switchState = switchState;
    }

    public Car(String [] carData) {
        this.marque = carData[0];
        this.modele = carData[1];
        this.matricule = carData[2];
        this.codeSecret = carData[3];
        this.numTele = carData[4];
    }


    @Override
    public String toString() {
        return "Car{" +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", matricule='" + matricule + '\'' +
                ", codeSecret='" + codeSecret + '\'' +
                ", numTele='" + numTele + '\'' +
                '}';
    }


    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public String getNumTele() {
        return numTele;
    }

    public void setNumTele(String numTele) {
        this.numTele = numTele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastTrackDate() {
        return lastTrackDate;
    }

    public void setLastTrackDate(String lastTrackDate) {
        this.lastTrackDate = lastTrackDate;
    }


    public Double getLastLocationLng() {
        return lastLocationLng;
    }

    public void setLastLocationLng(Double lastLocationLng) {
        this.lastLocationLng = lastLocationLng;
    }

    public Double getLastLocationLat() {
        return lastLocationLat;
    }

    public void setLastLocationLat(Double lastLocationLat) {
        this.lastLocationLat = lastLocationLat;
    }

    public boolean getSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }
}

