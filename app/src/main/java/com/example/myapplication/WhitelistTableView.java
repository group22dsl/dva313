package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class WhitelistTableView extends AppCompatActivity {

    public ListView databaseListView;
    public static final String ENTRY_ID = "";
    public static List<whitelistEntry> whitelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whitelist_table_view);

        databaseListView = (ListView) findViewById(R.id.database_list_Appfilters);
        whitelist = Database.getDatabase(this).whitelistDAO().getEntireWhitelist();

        CustomListViewAdapterWhitelist adapter = new CustomListViewAdapterWhitelist(this, whitelist);
        databaseListView.setAdapter(adapter);

        databaseListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(WhitelistTableView.this, WhitelistEntryView.class);
            intent.putExtra(ENTRY_ID, i);
            startActivity(intent);
        });
    }

    public void onAdd(View view)
    {
        Intent intent = new Intent(this, WhitelistEntryView.class);
        startActivity(intent);
    }

    public void clearTable(View view)
    {
        Database.getDatabase(this).whitelistDAO().resetTable();
        Intent intentClear = new Intent(this, WhitelistTableView.class);
        startActivity(intentClear);
        Toast.makeText(this, "Successfully deleted all tables", Toast.LENGTH_SHORT).show();
    }
}