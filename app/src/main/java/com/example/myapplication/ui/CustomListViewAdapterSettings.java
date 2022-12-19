package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.settings.SettingsEntry;

import java.util.List;

public class CustomListViewAdapterSettings extends BaseAdapter {

    private Context context;
    private List<SettingsEntry> settings;
    private TextView name;

    public CustomListViewAdapterSettings(Context context, List<SettingsEntry> settings) {
        this.context = context;
        this.settings = settings;
    }

    @Override
    public int getCount() {
        return settings.size();
    }

    @Override
    public Object getItem(int i) {
        return settings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return settings.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.database_list_view_appsettings, viewGroup,false);

        SettingsEntry appSettings = settings.get(i);

        name = (TextView) view.findViewById(R.id.settings_name);

        name.setText(appSettings.getNAME());
        return view;
    }
}
