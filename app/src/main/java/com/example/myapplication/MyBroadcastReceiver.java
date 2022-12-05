package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String collectedData;

    @Override
    public void onReceive(Context context, Intent intent) {
        collectedData = intent.getStringExtra("data");
        String[] myStrings = collectedData.split("@");
        JSONObject jsonObject = new JSONObject();

        try {
            // TODO: string sent MUST have this structure for parsing into JSON to work.
            jsonObject.put("Origin",myStrings[0]);
            //We use our function here to turn the given string to an actual date.
            jsonObject.put("Time",convertToDate(myStrings[1]));
            jsonObject.put("Information",myStrings[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, jsonObject.toString(), Toast.LENGTH_LONG).show();
    }
    //This function converts string to actual date.
    private String convertToDate(String stringDate) {
        Date date = new Date(Long.parseLong(stringDate));
        //We return a SimpleDateFormat object with the date variable on line 38.
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
