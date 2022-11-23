package com.example.myapplication;

import android.content.Context;
import android.view.ContentInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {

    private Context context;
    private List<TODOTask> tasks;
    private TextView title;
    private TextView description;

    public CustomListViewAdapter(Context context, List<TODOTask> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tasks.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        view = inflater.inflate(R.layout.database_list_view, viewGroup, false);

        TODOTask todoTask = tasks.get(i);

        title = (TextView) view.findViewById(R.id.title_textView);
        description = (TextView) view.findViewById(R.id.description_subTitle);

        title.setText(todoTask.getTitle());
        description.setText(todoTask.getDescription());
        return view;
    }
}
