package com.example.agusosimani.Homely;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.agusosimani.Homely.device.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationsAdapter extends ArrayAdapter<Device>{
    private static final String TAG = "NotificationsAdapter";

    private List<Device> devices = new ArrayList<>();
    private Context context;
    private int resource;


    static class ViewHolder {
        public TextView tvDeviceName;
        public ToggleButton btnState;
    }

    public NotificationsAdapter(Context context, int resource, List<Device> devices) {
        super(context, resource, devices);
        this.devices = devices;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        if (devices == null) return 0;
        return devices.size();
    }

    @Override
    public Device getItem(int pos) {
        return devices.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Device device = getItem(position);
        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(resource, null);

            viewHolder.btnState = convertView.findViewById(R.id.toggleButton);
            viewHolder.tvDeviceName = convertView.findViewById(R.id.device_name);

            convertView.setTag(viewHolder);

        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvDeviceName.setText(device.getName());
        viewHolder.btnState.setTag(devices.get(position));

        //viewHolder.btnState.setChecked(device.isNotificationsActivated());

         for (Map.Entry<Device, Boolean> e : NotificationSettings.devicesMap.entrySet()) {
            if (e.getKey().getId().equals(device.getId()))
                viewHolder.btnState.setChecked(e.getValue());
        }

        viewHolder.btnState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String deviceName = viewHolder.tvDeviceName.getText().toString();
                final Device device = getItem(position);
                if (viewHolder.btnState.isPressed()){
                    if(b) {
                        device.turnOnNotifications();
                    }else {
                        device.turnOffNotifications();

                    }
                    NotificationSettings.devicesChangedMap.put(device,b);
                }

                for (Device d:devices) {
                    if(d.getName().equals(deviceName))
                        NotificationSettings.devicesChangedMap.put(d, b);
                }

            }
        });

        return convertView;
    }
}
