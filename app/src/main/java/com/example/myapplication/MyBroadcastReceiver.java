package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public String collectedData;
    public static List<whitelistEntry> whitelist;

    @Override
    public void onReceive(Context context, Intent intent) {
        collectedData = intent.getStringExtra("data");

        try {
            JSONObject jsonObj = new JSONObject(collectedData);

            SharedPreferences sharedpreferences = context.getSharedPreferences("AppWhiteList", Context.MODE_PRIVATE);
            Set<String> whiteListAppsNew = new HashSet<String>();

            whiteListAppsNew = sharedpreferences.getStringSet("whiteListedApps", null);
            if(whiteListAppsNew == null || whiteListAppsNew.size() == 0 ){
                whitelist = Database.getDatabase(context).whitelistDAO().getEntireWhitelist();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Set<String> whiteListApps = new HashSet<String>();
                whitelist = Database.getDatabase(context).whitelistDAO().getEntireWhitelist();
                for(whitelistEntry list: whitelist){
                    whiteListApps.add(list.getID());
                }
                editor.putStringSet("whiteListedApps", whiteListApps);
                editor.apply();
                whiteListAppsNew = sharedpreferences.getStringSet("whiteListedApps", null);
            }
            if(whiteListAppsNew != null && whiteListAppsNew.size() > 0 ) {
                boolean contains = whiteListAppsNew.contains(jsonObj.getString("ID"));
                if (contains == true) {
                    Toast.makeText(context, "You got new message from whitelisted app", Toast.LENGTH_SHORT).show();

                    Thread threadSender = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sendAnalytics(jsonObj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    threadSender.start();

                } else {
                    Toast.makeText(context, "You got new message from blacklisted app", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Please check whitelisted apps in settings",  Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendAnalytics(JSONObject jsonObj) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(jsonObj.toString(), JSON);

        Request request = new Request.Builder()
                //.url("https://nextcloud.thepotatoservices.com/apps/files/?dir=/DVA313%20-%20Software%20Engineering%202&fileid=15430")
                .url("https://savejsonfile.free.beeceptor.com")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Log.v("response",response.toString());
    }
}
