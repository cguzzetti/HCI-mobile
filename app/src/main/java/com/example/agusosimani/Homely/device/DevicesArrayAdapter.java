package com.example.agusosimani.Homely.device;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.NotificationSettings;
import com.example.agusosimani.Homely.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DevicesArrayAdapter extends ArrayAdapter<Device> {
    private static final String TAG = "DevicesAdapter";
    private ArrayList<Device> devices = new ArrayList<>();
   // private Context context;
    public DevicesArrayAdapter(Context context, int resource,  ArrayList<Device> devices) {
        super(context, resource , devices);
        this.devices = devices;
       // this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
       ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
            holder = new ViewHolder();
            String deviceName = getItem(position).getName();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name);
        //    holder.deleteBtnView = (ImageButton) convertView.findViewById(R.id.delete_btn);
        //    holder.editBtnView = (ImageButton) convertView.findViewById(R.id.edit_btn);
            holder.switchView = (Switch) convertView.findViewById(R.id.status_switch);
           // boolean status = getItem(position).isOn();
            boolean status = devices.get(position).isOn();

            if (status){
                holder.switchView.setChecked(true);
            }else{
                holder.switchView.setChecked(false);
            }

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Device device = getItem(position);
        holder.nameTextView.setText(device.getName());

        holder.switchView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                JSONObject myJson = new JSONObject();
                Device dev = devices.get(position);
                try{
                    myJson.put("name", dev.getName());
                    myJson.put("id", dev.getId());
                    myJson.put("typeId", dev.getTypeID());
                    if (dev.isOn()){
                        myJson.put("meta", "{ status: false }");
                    }else{
                        myJson.put("meta", "{ status: true }");
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + devices.get(position).getId(), myJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
                API.mRequestQueue.add(request);
                devices.get(position).changeStatus();

                Notification notif = new Notification();
                Notification notification = new Notification.Builder(getContext())
                        .setContentTitle("Device " + devices.get(position).getName())
                        .setContentText("ESTO RE FUNCA MAESTRO")
                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                        //  .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_download_done))
                        .setSmallIcon(android.R.drawable.stat_sys_download_done).build();

                // Ignore deprecated warning. In newer devices SDK 16+ should use build() method.
                // getNotification() method internally calls build() method.
                API.nManager.notify(API.channelId, notification);
            }
        });
        return convertView;
    }
}

