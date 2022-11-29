package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSelectTodo(View view){
        Intent intent = new Intent(MainActivity.this, TODOTaskActivity.class);
        startActivity(intent);
    }

    public void onSelectAPPFilters(View view){
        Intent intent = new Intent(MainActivity.this, APPFiltersActivity.class);
        startActivity(intent);
    }

}