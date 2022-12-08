package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class APPFiltersActivity extends AppCompatActivity {

    public ListView databaseListView;
    public static final String APP_FILTER_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appfilters);

        databaseListView = (ListView) findViewById(R.id.database_list_Appfilters);

        List<APPFilters> filters = Database.getDatabase(this).appFiltersDAO().getAllFilters();

        CustomListViewAdapterAPPFilters adapter = new CustomListViewAdapterAPPFilters(this, filters);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                APPFilters filter = (APPFilters) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(APPFiltersActivity.this, FiltersActivity.class);
                intent.putExtra(APP_FILTER_ID, filter.getId());
                startActivity(intent);
            }
        });
    }

    public void onAdd(View view)
    {
        Intent intent = new Intent(this, FiltersActivity.class);
        startActivity(intent);
    }

    public void clearTablesButton(View view)
    {
        Database.getDatabase(this).appFiltersDAO().clearTable();
        Intent intentClear = new Intent(this, APPFiltersActivity.class);
        startActivity(intentClear);
        Toast.makeText(this, "Successfully deleted all tables", Toast.LENGTH_SHORT).show();
    }
}