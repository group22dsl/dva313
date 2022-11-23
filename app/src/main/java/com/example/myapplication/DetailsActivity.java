package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    public EditText title;
    public EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = (EditText) findViewById(R.id.title_input);
        description = (EditText) findViewById(R.id.description_input);
    }

    public void onSave(View view)
    {
        TODOTask task = new TODOTask(title.getText().toString(), description.getText().toString());
        Database.getDatabase(this).dao().addTask(task);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onCancel(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onDelete(View view)
    {

    }




}