package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public ListView databaseListView;
    public static final String TASK_ID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        registerReceiver(myBroadcastReceiver, myFilter);

        databaseListView = (ListView) findViewById(R.id.database_list);

        List<TODOTask> tasks = Database.getDatabase(this).dao().getAllTasks();

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, tasks);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TODOTask task = (TODOTask) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(TASK_ID, task.getId());

                startActivity(intent);
            }
        });
    }



    public void onAddClick(View view)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}