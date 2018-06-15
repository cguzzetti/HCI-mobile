package com.example.agusosimani.Homely;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class API {
    public static RequestQueue mRequestQueue;
    public final static String baseUrl  = "http://127.0.0.1:8080/api";
    public static NotificationManager nManager;
    public static MainActivity mainActivity;
    public static String channelId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void init(MainActivity mainActiv){
        mainActivity = mainActiv;
        channelId = "1";
        mRequestQueue = Volley.newRequestQueue(mainActivity);
        nManager = (NotificationManager) mainActivity.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
