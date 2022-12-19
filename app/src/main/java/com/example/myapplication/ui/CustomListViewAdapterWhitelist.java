package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.whitelist.WhitelistEntry;

import java.util.List;

public class CustomListViewAdapterWhitelist extends BaseAdapter {

    private Context context;
    private List<WhitelistEntry> filters;
    private TextView name;

    public CustomListViewAdapterWhitelist(Context context, List<WhitelistEntry> filters) {
        this.context = context;
        this.filters = filters;
    }

    @Override
    public int getCount() {
        return filters.size();
    }

    @Override
    public Object getItem(int i) {
        return filters.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.database_list_view_filters, viewGroup,false);

        WhitelistEntry appFilters = filters.get(i);

        name = (TextView) view.findViewById(R.id.app_name);

        name.setText(appFilters.getID());
        return view;
    }
}
