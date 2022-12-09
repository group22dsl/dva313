package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsEntryView extends AppCompatActivity {

    private EditText name;
    private EditText value;
    private SettingsEntry settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_add_entry);

        name  = (EditText) findViewById(R.id.settings_name_text);
        value = (EditText) findViewById(R.id.settings_value_text);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null)
        {
            int id = bundle.getInt(SettingsTableView.ENTRY_ID);
            settings = SettingsTableView.settings.get(id);

            name.setText(settings.getNAME());
            value.setText(settings.getVALUE());
        }
    }

    public void saveButton(View view)
    {
        if (settings != null)
        {
            settings.setNAME(name.getText().toString());
            settings.setVALUE(value.getText().toString());
            Database.getDatabase(this).settingsDAO().updateAPPSettings(settings);
        }
        else
        {
            SettingsEntry createSettings = new SettingsEntry(
                    Integer.parseInt(SettingsTableView.ENTRY_ID),
                    name.getText().toString(),
                    value.getText().toString()
            );
            Database.getDatabase(this).settingsDAO().addSettings(createSettings);
        }

        Intent intent = new Intent(this, SettingsTableView.class);
        startActivity(intent);
    }

    public void cancelButton(View view)
    {
        Intent intentCancel = new Intent(this, SettingsTableView.class);
        startActivity(intentCancel);
    }

    public void deleteButton(View view)
    {
        Database.getDatabase(this).settingsDAO().deleteAPPSettings(settings);
        Intent intentDelete = new Intent(this, SettingsTableView.class);
        startActivity(intentDelete);
    }


}