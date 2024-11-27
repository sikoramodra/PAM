package com.example.pam.ui.task3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pam.R;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {
    private final ArrayList<String> mListItems;
    private final LayoutInflater mLayoutInflater;
    private final OnTaskDeleteListener deleteListener;

    public MyCustomAdapter(Context context, ArrayList<String> arrayList, OnTaskDeleteListener deleteListener) {
        this.mListItems = arrayList;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.deleteListener = deleteListener;
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.due, parent, false);

            holder = new ViewHolder();
            holder.itemName = convertView.findViewById(R.id.due_text_view);
            holder.imageButton = convertView.findViewById(R.id.delete_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set task name
        String task = mListItems.get(position);
        holder.itemName.setText(task);

        // Handle delete button click
        holder.imageButton.setOnClickListener(v -> deleteListener.onTaskDelete(task));

        return convertView;
    }

    public interface OnTaskDeleteListener {
        void onTaskDelete(String task);
    }

    private static class ViewHolder {
        TextView itemName;
        ImageButton imageButton;
    }
}