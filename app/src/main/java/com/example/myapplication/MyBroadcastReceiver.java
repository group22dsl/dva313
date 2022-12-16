package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import androidx.room.Dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String collectedData;
    public CacheDAO cacheDAO;
    public WhitelistDAO whitelistDAO;
    private cacheEntry entry;

    @Override
    public void onReceive(Context context, Intent intent) {

        collectedData = intent.getStringExtra("data");
        String ID;

        boolean existsInWhitelist = false;
        try {
            JSONObject jsonObject = new JSONObject(collectedData);
            ID=jsonObject.get("ID").toString();
            //TODO must be in the try. Because we use the json object
            SharedPreferences sharedpreferences = context.getSharedPreferences("appSettings", Context.MODE_PRIVATE);
            Set<String> whiteListApps = new HashSet<String>();
            //whiteListApps = sharedpreferences.getStringSet("whiteList",null);
            //TODO is the error because of this list or because of what is sent from database?
            whitelistDAO.getEntireWhitelist().size();
            for (int i = 0; whitelistDAO.getEntireWhitelist().size() > i; i++){
                if(whitelistDAO.getEntireWhitelist().get(i).getID() == ID){
                    Toast.makeText(context, "You got new message from whitelisted app" , Toast.LENGTH_SHORT ).show();
                    Toast.makeText(context, collectedData, Toast.LENGTH_LONG).show();
                    entry = new cacheEntry(ID, jsonObject.get("Priority").toString(), jsonObject.get("Data").toString());
                    cacheDAO.addCacheEntry(entry);
                    existsInWhitelist = true;
                    break;
                }
            }
            if (!existsInWhitelist){
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
