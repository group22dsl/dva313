package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;


public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    private Request request;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //  TODO CREATE THREAD, TEST TIMER 5SECONDS DONE !
        //  TODO PARSE JSON FILE INTO DBOBJECT
        //  TODO CHANGE SETTING WITH FUNCTION

        startListeningThreads();
        startPostingThread();
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter myFilter = new IntentFilter("testData");
        registerReceiver(myBroadcastReceiver, myFilter);

        SharedPreferences sharedpreferences = getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //this should get from DB
        String[] listOfExistingScores = {"com.example.a", "true", "Three"};
        //end

        Set<String> set = new HashSet<String>();
        for (int l = 0; l < listOfExistingScores.length; l++) {
            set.add(listOfExistingScores[l]);
        }

        editor.putStringSet("whiteList", set);

        editor.commit(); // commit changes
    }

    public void startListeningThreads(){
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
    public void startPostingThread(){
        Thread threadSender = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    sendAnalytics();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        threadSender.start();
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
    public void sendAnalytics() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("title","test");
        json.put("name","plplpl");


        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://testretreuyguy.free.beeceptor.com/")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        Log.v("response",response.toString());


    }

}