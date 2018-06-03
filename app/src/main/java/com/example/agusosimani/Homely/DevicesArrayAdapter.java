package com.example.agusosimani.Homely;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DevicesArrayAdapter extends ArrayAdapter<Device> {
    public DevicesArrayAdapter(Activity context, Device[] devices) {
        super(context, R.layout.list_view_item , devices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
       ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Device device = getItem(position);
        holder.nameTextView.setText(device.getName());

        return convertView;
    }
}
