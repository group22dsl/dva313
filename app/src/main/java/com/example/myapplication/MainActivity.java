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
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  TODO CREATE THREAD, TEST TIMER 5SECONDS DONE !
        //  TODO PARSE JSON FILE INTO DBOBJECT
        //  TODO CHANGE SETTING WITH FUNCTION

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    requestConfiguration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter myFilter = new IntentFilter("testData");

        SharedPreferences sharedpreferences = getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //this should get from DB
        String[] listOfExistingScores = {"com.example.a", "true", "Three"};
        //end

        Set<String> set = new HashSet<String>();
        for(int l=0; l<listOfExistingScores.length; l++){
            set.add(listOfExistingScores[l]);
        }

        editor.putStringSet("whiteList", set);

        editor.commit(); // commit changes
;    }

    public void onSelectTodo(View view){
        Intent intent = new Intent(MainActivity.this, TODOTaskActivity.class);
        startActivity(intent);
    }

    public void onSelectAPPFilters(View view){
        Intent intent = new Intent(MainActivity.this, APPFiltersActivity.class);
        startActivity(intent);
    }
    public void requestConfiguration() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
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
                    Log.v("HTTP_REQUEST", "Json Created, contents: " + data);
                } catch (JSONException e) {
                    Log.d("HTTP_REQUEST", "HTTP Request error: " + e);
                    Log.d("HTTP_REQUEST", "HTTP result was: " + jsonStr);
                    e.printStackTrace();
                }
            }
        });
    }


}
