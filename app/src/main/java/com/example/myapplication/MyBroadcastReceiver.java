package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.JsonReader;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String collectedData;
    CacheDAO cacheDAO;
    WhitelistDAO whitelistDAO;
    cacheEntry entry;

    @Override
    public void onReceive(Context context, Intent intent) {

        collectedData = intent.getStringExtra("data");
        String ID;
        try {
            JSONObject jsonObject = new JSONObject(collectedData);
            ID=jsonObject.get("ID").toString();
            //TODO must be in the try. Because we use the json object
            SharedPreferences sharedpreferences = context.getSharedPreferences("appSettings", Context.MODE_PRIVATE);
            Set<String> whiteListApps = new HashSet<String>();
            //whiteListApps = sharedpreferences.getStringSet("whiteList",null);
            List<whitelistEntry> testWhitelist = whitelistDAO.getEntireWhitelist();
            //index 2 is where ID is placed.
            //boolean contains = whiteListApps.contains(ID);
            for (int i = 0; ; i++)
            if(contains == true){
                Toast.makeText(context, "You got new message from whitelisted app" , Toast.LENGTH_SHORT ).show();
                Toast.makeText(context, collectedData, Toast.LENGTH_LONG).show();
                entry = new cacheEntry(ID, jsonObject.get("Priority").toString(), jsonObject.get("Data").toString());
                cacheDAO.addCacheEntry(entry);
            }
            else{
                Toast.makeText(context, "You got new message from blacklisted app" , Toast.LENGTH_SHORT ).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    //This function converts string to actual date.
    private String convertToDate(String stringDate) {
        Date date = new Date(Long.parseLong(stringDate));
        //We return a SimpleDateFormat object with the date variable on line 38.
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
