package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FiltersActivity extends AppCompatActivity {

    private EditText name;
    private APPFilters filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        name = (EditText) findViewById(R.id.app_name_text);

        filter = null;

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
            APPFilters updateFilter = new APPFilters(filter.getId(), name.getText().toString(), true, true);
            Database.getDatabase(this).appFiltersDAO().updateAPPFilter(updateFilter);
        }
        else
        {
            APPFilters createFilter = new APPFilters(name.getText().toString(), true, true);
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
        APPFilters deleteFilter = new APPFilters(filter.getId(), name.getText().toString(), true, true);
        Database.getDatabase(this).appFiltersDAO().deleteAPPFilter(deleteFilter);
        Intent intentDelete = new Intent(this, APPFiltersActivity.class);
        startActivity(intentDelete);
    }
}