package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView databaseListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseListView = (ListView) findViewById(R.id.database_list);

        List<TODOTask> tasks = Database.getDatabase(this).dao().getAllTasks();

        databaseListView = findViewById(R.id.database_list);
        setUpAdapter();
    }

    public void setUpAdapter()
    {
        List<TODOTask> todoList = Database.getDatabase(this).dao().getAllTasks();

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, todoList);
        databaseListView.setAdapter(adapter);
    }

    public void onAddClick(View view)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}