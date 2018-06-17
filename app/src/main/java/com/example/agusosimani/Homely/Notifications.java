package com.example.agusosimani.Homely;

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

