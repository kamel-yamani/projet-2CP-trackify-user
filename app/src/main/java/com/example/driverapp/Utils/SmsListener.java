package com.example.driverapp.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.driverapp.Fragments.MapFragment;
import com.example.driverapp.MainActivity;
import com.example.driverapp.Models.Coord;

public class SmsListener extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    String phoneNumber;
    String privateCode;



    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals(SMS_RECEIVED)) {
            phoneNumber = MainActivity.currTrackedCar.getNumTele();
            privateCode = MainActivity.currTrackedCar.getCodeSecret();
            boolean stop = false;
            Log.d("LocationRecieved","The location has been recieved");
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                SmsMessage[] msgs = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(msgs[i].getMessageBody());
                }
                String sender = msgs[0].getOriginatingAddress();
                String message = sb.toString();

                if(PhoneNumberUtils.compare(phoneNumber, sender)) {
                    //in case of the car app and the tracker app had the same phone number
                    // the the private code message is resent to the same phone
                    // so we ignore it with the following if statement
                    // TODO questionable condition statement
                    if (!message.contains(privateCode)) {
                        Coord coord = new Coord();
                        coord.recup_coord(message);
                        MainActivity.currTrackedCar.setLastLocationLat(coord.getLat());
                        MainActivity.currTrackedCar.setLastLocationLng(coord.getLng());
                        MainActivity.myCars.update(MainActivity.currTrackedCar);
                        MainActivity.mapFragment.showCarCurrentLocation();
                        MainActivity.locarionProgressDialog.dismiss();
                    }
                }
            }
        }
    }

}