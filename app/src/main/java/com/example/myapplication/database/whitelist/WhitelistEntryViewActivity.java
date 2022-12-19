package com.example.myapplication.database.whitelist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.Database;

public class WhitelistEntryViewActivity extends AppCompatActivity {

    private EditText name;
    private EditText send_freq;
    private com.example.myapplication.database.whitelist.WhitelistEntry WhitelistEntry;

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
            int id = bundle.getInt(WhitelistTableViewActivity.ENTRY_ID);
            WhitelistEntry = WhitelistTableViewActivity.whitelist.get(id);
            name.setText(WhitelistEntry.getID());
        }
    }

    public void saveButton(View view)
    {
        if(WhitelistEntry != null)
        {
            WhitelistEntry.setID(name.getText().toString());
            WhitelistEntry.setSEND_FREQ(Integer.parseInt(send_freq.getText().toString()));
            Database.getDatabase(this).whitelistDAO().updateWhitelist(WhitelistEntry);
        }
        else
        {
            com.example.myapplication.database.whitelist.WhitelistEntry createWhitelist = new WhitelistEntry(name.getText().toString(), Integer.parseInt(send_freq.getText().toString()));
            Database.getDatabase(this).whitelistDAO().addWhitelistEntry(createWhitelist);
        }
        Intent intent = new Intent(this, WhitelistTableViewActivity.class);
        startActivity(intent);
    }

    public void cancelButton(View view)
    {
        Intent intentCancel = new Intent(this, WhitelistTableViewActivity.class);
        startActivity(intentCancel);
    }

    public void deleteButton(View view)
    {
        if (WhitelistEntry != null)
        {
            Database.getDatabase(this).whitelistDAO().deleteWhitelist(WhitelistEntry);
            Intent intentDelete = new Intent(this, WhitelistTableViewActivity.class);
            startActivity(intentDelete);
        }
        else
        {
            Toast.makeText(this, "There is nothing to delete.", Toast.LENGTH_SHORT).show();
        }
    }



}