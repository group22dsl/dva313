package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FiltersActivity extends AppCompatActivity {

    private EditText name;
    private APPFilters filter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        name = (EditText) findViewById(R.id.app_name_text);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            int id = bundle.getInt(APPFiltersActivity.APP_FILTER_ID);
            filter = Database.getDatabase(this).appFiltersDAO().getAPPFilter(id);

            name.setText(filter.getAPP_NAME());
        }
    }


    public void saveButton(View view)
    {
        if(filter != null)
        {
            filter.setAPP_NAME(name.getText().toString());
            Database.getDatabase(this).appFiltersDAO().updateAPPFilter(filter);
        }
        else
        {
            APPFilters createFilter = new APPFilters(name.getText().toString(), false, false);
            Database.getDatabase(this).appFiltersDAO().addAppFilter(createFilter);
        }
        Intent intent = new Intent(this, APPFiltersActivity.class);
        startActivity(intent);
    }

    public void cancelButton(View view)
    {
        Intent intentCancel = new Intent(this, APPFiltersActivity.class);
        startActivity(intentCancel);
    }

    public void deleteButton(View view)
    {
        Database.getDatabase(this).appFiltersDAO().deleteAPPFilter(filter);
        Intent intentDelete = new Intent(this, APPFiltersActivity.class);
        startActivity(intentDelete);
    }
}