package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListViewAdapterAPPFilters extends BaseAdapter {

    private Context context;
    private List<APPFilters> filters;
    private TextView name;

    public CustomListViewAdapterAPPFilters(Context context, List<APPFilters> filters) {
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
        return filters.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.database_list_view_filters, viewGroup,false);

        APPFilters appFilters = filters.get(i);

        name = (TextView) view.findViewById(R.id.app_name);

        name.setText(appFilters.getAPP_NAME());
        return view;
    }
}
