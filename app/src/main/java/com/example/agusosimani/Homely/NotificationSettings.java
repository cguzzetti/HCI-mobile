package com.example.agusosimani.Homely;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agusosimani.Homely.device.Device;
import com.example.agusosimani.Homely.device.DevicesTab;

import java.util.HashMap;
import java.util.Map;

public class NotificationSettings extends Activity {
    private static final int MY_NOTIFICATION_ID = 1;
    PendingIntent contentIntent;
    static NotificationsAdapter adapter;
    public static HashMap<Device, Boolean> devicesMap = new HashMap<>();
    public static HashMap<Device, Boolean> devicesChangedMap = new HashMap<>();
    private ImageButton back, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_settings);

        adapter = new NotificationsAdapter(this, R.layout.notifications_adapter, DevicesTab.getDevices());
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        back = findViewById(R.id.back_arrow);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Toast.makeText(view.getContext(), view.getContext().getResources().getString(R.string.changes_saved), Toast.LENGTH_LONG).show();
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void update(){

        for (Map.Entry<Device, Boolean> e : devicesChangedMap.entrySet()) {
            devicesMap.put(e.getKey(), e.getValue());
            if (e.getValue()){

            }



        }

    }

}
