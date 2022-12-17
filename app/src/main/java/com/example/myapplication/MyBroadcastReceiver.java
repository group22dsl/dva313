package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String collectedData;

    @Override
    public void onReceive(Context context, Intent intent) {
        collectedData = intent.getStringExtra("data");

        System.out.println("INCOMMING MESSAGE RECEIVED START");
        System.out.println(collectedData);
        System.out.println("INCOMMING MESSAGE RECEIVED END");

//        try {
//
//            SharedPreferences sharedpreferences = context.getSharedPreferences("appSettings", Context.MODE_PRIVATE);
//            Set<String> whiteListApps = new HashSet<String>();
//            whiteListApps = sharedpreferences.getStringSet("whiteList",null);
//
//            boolean contains = whiteListApps.contains();
//            if(contains == true){
//                Toast.makeText(context, "You got new message from whitelisted app" , Toast.LENGTH_SHORT ).show();
//            }
//            else{
//                Toast.makeText(context, "You got new message from blacklisted app" , Toast.LENGTH_SHORT ).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
    //This function converts string to actual date.
    private String convertToDate(String stringDate) {
        Date date = new Date(Long.parseLong(stringDate));
        //We return a SimpleDateFormat object with the date variable on line 38.
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
