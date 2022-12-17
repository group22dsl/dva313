package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    private Request request;
    public static List<whitelistEntry> whitelist;
    Set<String> whiteListApps = new HashSet<String>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter myFilter = new IntentFilter("testData");

        registerReceiver(myBroadcastReceiver, myFilter);

        SharedPreferences sharedpreferences = getSharedPreferences("AppWhiteList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        whitelist = Database.getDatabase(this).whitelistDAO().getEntireWhitelist();
        for(whitelistEntry list: whitelist){
            whiteListApps.add(list.getID());
        }
        editor.putStringSet("whiteListedApps", whiteListApps);
        editor.apply();

        //  TODO CREATE THREAD, TEST TIMER 5SECONDS DONE !
        //  TODO PARSE JSON FILE INTO DBOBJECT
        //  TODO CHANGE SETTING WITH FUNCTION

        Thread threadReceiver = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    requestConfiguration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        threadReceiver.start();
    }

    public void onSelectWhitelist(View view){
        Intent intent = new Intent(MainActivity.this, WhitelistTableView.class);
        startActivity(intent);
    }
    public void onSelectSettings(View view)
    {
        Intent intent = new Intent(MainActivity.this, SettingsTableView.class);
        startActivity(intent);
    }

    public void requestConfiguration() throws IOException {
        client = new OkHttpClient();
        request = new Request.Builder()
                .url("https://nextcloud.thepotatoservices.com/s/mGaCFbGynG7cLq7/download/test.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Log.d("HTTP_REQUEST", "HTTP request failed, Error: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonStr = response.body().string();
                try {
                    JSONObject data = new JSONObject(jsonStr);
                    Log.v("HTTP_REQUEST", "Json Created, contents: " + data); // TODO GET JSON FILE SETTINGS - FIXED
                } catch (JSONException e) {
                    Log.d("HTTP_REQUEST", "HTTP Request error: " + e);
                    Log.d("HTTP_REQUEST", "HTTP result was: " + jsonStr);
                    e.printStackTrace();
                }
            }
        });
    }
}