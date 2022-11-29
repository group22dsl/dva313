package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class TODOTaskActivity extends AppCompatActivity {

    public ListView databaseListView;
    public static final String TASK_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todotask);

        databaseListView = (ListView) findViewById(R.id.database_list);

        List<TODOTask> tasks = Database.getDatabase(this).dao().getAllTasks();

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, tasks);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TODOTask task = (TODOTask) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(TODOTaskActivity.this, DetailsActivity.class);
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