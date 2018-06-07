package com.example.agusosimani.Homely;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DevicesArrayAdapter extends ArrayAdapter<Device> {
    private static final String TAG = "DevicesAdapter";
    private ArrayList<Device> devices = new ArrayList<>();
   // private Context context;
    public DevicesArrayAdapter(Context context, int resource,  ArrayList<Device> devices) {
        super(context, resource , devices);
        //this.devices = devices;
       // this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
       ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
            holder = new ViewHolder();
            String deviceName = getItem(position).getName();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name);
            holder.deleteBtnView = (ImageButton) convertView.findViewById(R.id.delete_btn);
            holder.editBtnView = (ImageButton) convertView.findViewById(R.id.edit_btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Device device = getItem(position);
        holder.nameTextView.setText(device.getName());
        return convertView;
    }
}
