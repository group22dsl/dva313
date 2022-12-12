package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter myFilter = new IntentFilter("testData");

        SharedPreferences sharedpreferences = getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        //this settings should get from DB

        String[] listOfSettings = {{"AppID": 1, "AppName": "nuwan"}, {"AppID": 1, "AppName": "nuwan"}}
        //end

        Set<String> set = new HashSet<String>();
        for(int l=0; l<listOfSettings.length; l++){
            set.add(listOfSettings[l]);
        }

        editor.putStringSet("settings", set);

        editor.commit(); // commit changes

        saveDataFromsharedPrefToJson(listOfSettings, editor);
    }

    public void onSelectTodo(View view){
        Intent intent = new Intent(MainActivity.this, TODOTaskActivity.class);
        startActivity(intent);
    }

    public void onSelectAPPFilters(View view){
        Intent intent = new Intent(MainActivity.this, APPFiltersActivity.class);
        startActivity(intent);
    }

    //This method should save Settings and Whitelist data from shared preferences to Json
    public void saveDataFromsharedPrefToJson(String[] dataFromDB, SharedPreferences.Editor spEditor){
        Gson gson = new Gson();
        String savedSettings = gson.toJson(dataFromDB);
        spEditor.put("Settings", savedSettings);
        spEditor.commit();
    }
}