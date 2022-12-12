package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

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

    public void onSelectWhitelist(View view){
        Intent intent = new Intent(MainActivity.this, WhitelistTableView.class);
        startActivity(intent);
    }

    public void onSelectSettings(View view)
    {
        Intent intent = new Intent(MainActivity.this, SettingsTableView.class);
        startActivity(intent);
    }

}