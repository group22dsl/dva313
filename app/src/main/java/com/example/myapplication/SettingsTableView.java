package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SettingsTableView extends AppCompatActivity {

    public ListView databaseListView;
    public static final String ENTRY_ID = "";
    public static List<SettingsEntry> settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_table_view);

        databaseListView = (ListView) findViewById(R.id.database_list_Appsettings);
        settings = Database.getDatabase(this).settingsDAO().getAllSettings();

        CustomListViewAdapterSettings adapter = new CustomListViewAdapterSettings(this, settings);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(SettingsTableView.this, SettingsEntryView.class);
            intent.putExtra(ENTRY_ID, i);
            startActivity(intent);
        });
    }

    public void onAdd(View view)
    {
        Intent intent = new Intent(this, SettingsEntryView.class);
        startActivity(intent);
    }

    public void deleteTable(View view)
    {
        Database.getDatabase(this).settingsDAO().resetTable();
        Intent intentClear = new Intent(this, SettingsTableView.class);
        startActivity(intentClear);
        Toast.makeText(this, "Successfully deleted all tables", Toast.LENGTH_SHORT).show();
    }
}