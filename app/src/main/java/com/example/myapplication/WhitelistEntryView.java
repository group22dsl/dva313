package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WhitelistEntryView extends AppCompatActivity {

    private EditText name;
    private EditText send_freq;
    private whitelistEntry WhitelistEntry;
    public static List<whitelistEntry> whitelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whitelist_add_entry);

        name =      (EditText) findViewById(R.id.name_text);
        send_freq = (EditText) findViewById(R.id.send_freq_num);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            int id = bundle.getInt(WhitelistTableView.ENTRY_ID);
            WhitelistEntry = WhitelistTableView.whitelist.get(id);
            name.setText(WhitelistEntry.getID());
        }
    }

    public void addOrUpdateSharedPreferencesWhilteListedApps(String idName){
        SharedPreferences sharedpreferences = this.getSharedPreferences("AppWhiteList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        whitelist = Database.getDatabase(this).whitelistDAO().getEntireWhitelist();
        Set<String> whiteListApps = new HashSet<String>();
        for(whitelistEntry list: whitelist){
            whiteListApps.add(list.getID());
        }
        whiteListApps.add(idName);
        editor.putStringSet("whiteListedApps", whiteListApps);
    }

    public void saveButton(View view)
    {
        if(WhitelistEntry != null)
        {
            WhitelistEntry.setID(name.getText().toString());
            WhitelistEntry.setSEND_FREQ(Integer.parseInt(send_freq.getText().toString()));
            Database.getDatabase(this).whitelistDAO().updateWhitelist(WhitelistEntry);
            //addOrUpdateSharedPreferencesWhilteListedApps(name.getText().toString());
        }
        else
        {
            whitelistEntry createWhitelist = new whitelistEntry(name.getText().toString(), Integer.parseInt(send_freq.getText().toString()));
            Database.getDatabase(this).whitelistDAO().addWhitelistEntry(createWhitelist);
            //addOrUpdateSharedPreferencesWhilteListedApps(name.getText().toString());
        }
        Intent intent = new Intent(this, WhitelistTableView.class);
        startActivity(intent);
    }

    public void cancelButton(View view)
    {
        Intent intentCancel = new Intent(this, WhitelistTableView.class);
        startActivity(intentCancel);
    }

    public void deleteButton(View view)
    {
        if (WhitelistEntry != null)
        {
            Database.getDatabase(this).whitelistDAO().deleteWhitelist(WhitelistEntry);
            Intent intentDelete = new Intent(this, WhitelistTableView.class);
            startActivity(intentDelete);
        }
        else
        {
            Toast.makeText(this, "There is nothing to delete.", Toast.LENGTH_SHORT).show();
        }
    }



}