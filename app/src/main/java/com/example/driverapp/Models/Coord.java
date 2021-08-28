package com.example.driverapp.Models;

public class Coord {
    public double lat;   //variable statique qui contient le lattitude
    public double lng;   //variable dynamique qui contient le longitude

    public void recup_coord(String contenu_sms){       //Methode qui recupére les 2 coords
        String tmp[] = contenu_sms.split(",");  //séparé le msg reçu en 2 String (délimitateur est la virgule)
        this.lat=Double.parseDouble(tmp[0]);    //1er string est lat
        this.lng=Double.parseDouble(tmp[1]);    //2eme est lng
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
