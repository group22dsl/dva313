package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText name;
    private APPSettings settings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = (EditText) findViewById(R.id.settings_name_text);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null)
        {
            int id = bundle.getInt(APPSettingsActivity.APP_SETTING_ID);
            settings = Database.getDatabase(this).settingsDAO().getSettings(id);

            name.setText(settings.getName());
        }
    }

    public void saveButton(View view)
    {
        if (settings != null)
        {
            settings.setName(name.getText().toString());
            Database.getDatabase(this).settingsDAO().updateAPPSettings(settings);
        }
        else
        {
            APPSettings createSettings = new APPSettings(name.getText().toString());
            Database.getDatabase(this).settingsDAO().addSettings(createSettings);
        }

        Intent intent = new Intent(this, APPSettingsActivity.class);
        startActivity(intent);
    }

    public void cancelButton(View view)
    {
        Intent intentCancel = new Intent(this, APPSettingsActivity.class);
        startActivity(intentCancel);
    }

    public void deleteButton(View view)
    {
        Database.getDatabase(this).settingsDAO().deleteAPPSettings(settings);
        Intent intentDelete = new Intent(this, APPSettingsActivity.class);
        startActivity(intentDelete);
    }


}