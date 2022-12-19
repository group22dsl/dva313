package com.example.myapplication.database.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.Database;

public class SettingsEntryViewActivity extends AppCompatActivity {

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
            int id = bundle.getInt(SettingsTableViewActivity.ENTRY_ID);
            settings = SettingsTableViewActivity.settings.get(id);

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
                    name.getText().toString(),
                    value.getText().toString()
            );
            Database.getDatabase(this).settingsDAO().addSettings(createSettings);
        }

        Intent intent = new Intent(this, SettingsTableViewActivity.class);
        startActivity(intent);
    }

    public void cancelButton(View view)
    {
        Intent intentCancel = new Intent(this, SettingsTableViewActivity.class);
        startActivity(intentCancel);
    }

    public void deleteButton(View view) {
        if (settings != null)
        {
            Database.getDatabase(this).settingsDAO().deleteAPPSettings(settings);
            Intent intentDelete = new Intent(this, SettingsTableViewActivity.class);
            startActivity(intentDelete);
        }
        else
        {
            Toast.makeText(this, "There is nothing to delete.", Toast.LENGTH_SHORT).show();
        }
    }

}