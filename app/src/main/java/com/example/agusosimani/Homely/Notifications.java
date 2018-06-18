package com.example.agusosimani.Homely;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.provider.Settings;

import com.example.agusosimani.Homely.device.Device;

import java.util.ArrayList;
import java.util.List;

public class Notifications {
    public static List<Device> devicesWithNotifications = new ArrayList<>();


    public void add(Device d){
        devicesWithNotifications.add(d);
    }

    public void remove(Device d){
        devicesWithNotifications.remove(d);
    }

    public boolean contains(Device d){
        return devicesWithNotifications.contains(d);
    }

}

