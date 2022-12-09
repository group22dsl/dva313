package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class APPSettingsActivity extends AppCompatActivity {

    public ListView databaseListView;
    public static final String APP_SETTING_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appsettings);

        databaseListView = (ListView) findViewById(R.id.database_list_Appsettings);

        List<APPSettings> settings = Database.getDatabase(this).settingsDAO().getAllSettings();

        CustomListViewAdapterAPPSettings adapter = new CustomListViewAdapterAPPSettings(this, settings);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                APPSettings setting = (APPSettings) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(APPSettingsActivity.this, SettingsActivity.class);
                intent.putExtra(APP_SETTING_ID, setting.getId());
                startActivity(intent);
            }
        });
    }

    public void onAdd(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void deleteTable(View view)
    {
        Database.getDatabase(this).settingsDAO().resetTable();
        Intent intentClear = new Intent(this, APPSettingsActivity.class);
        startActivity(intentClear);
        Toast.makeText(this, "Successfully deleted all tables", Toast.LENGTH_SHORT).show();
    }
}