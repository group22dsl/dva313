package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListViewAdapterAPPSettings extends BaseAdapter {

    private Context context;
    List<APPSettings> settings;
    private TextView name;


    public CustomListViewAdapterAPPSettings(Context context, List<APPSettings> settings) {
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

        APPSettings appSettings = settings.get(i);

        name = (TextView) view.findViewById(R.id.settings_name);

        name.setText(appSettings.getName());
        return view;
    }
}
