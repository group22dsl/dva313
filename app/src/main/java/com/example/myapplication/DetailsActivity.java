package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.Database;

public class DetailsActivity extends AppCompatActivity {

    public EditText title;
    public EditText description;
    private TODOTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = findViewById(R.id.title_input);
        description = findViewById(R.id.description_input);
        task = null;

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null)
        {
            int id = bundle.getInt(TODOTaskActivity.TASK_ID);
            task = Database.getDatabase(this).dao().getTask(id);

            title.setText(task.getTitle());
            description.setText(task.getDescription());
        }
    }

    public void onSave(View view)
    {
        if (task != null) {
            TODOTask updateTask = new TODOTask(task.getId(), title.getText().toString(), description.getText().toString());
            Database.getDatabase(this).dao().updateTask(updateTask);
        }
        else {
            TODOTask task = new TODOTask(title.getText().toString(), description.getText().toString());
            Database.getDatabase(this).dao().addTask(task);

        }

        Intent intent = new Intent(this, TODOTaskActivity.class);
        startActivity(intent);
    }

    public void onCancel(View view)
    {
        Intent intent = new Intent(this, TODOTaskActivity.class);
        startActivity(intent);
    }
    public void onDelete(View view)
    {
        TODOTask deleteTask = new TODOTask(task.getId(), title.getText().toString(), description.getText().toString());
        Database.getDatabase(this).dao().deleteTask(deleteTask);
        Intent intent = new Intent(this, TODOTaskActivity.class);
        startActivity(intent);
    }







}