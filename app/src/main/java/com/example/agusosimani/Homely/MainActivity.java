package com.example.agusosimani.Homely;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.agusosimani.Homely.device.Device;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.device.Device;
import com.example.agusosimani.Homely.device.DevicesTab;
import com.example.agusosimani.Homely.routine.RoutinesTab;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static SectionsPageAdapter mSectionsPagerAdapter;
    private static BottomNavigationView mNavigationBar;
    public static Device dummy;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_routines:
                    fragment = new RoutinesTab();
                    break;
                case R.id.navigation_home:
                    fragment = new HomeTab();
                    break;
                case R.id.navigation_devices:
                    fragment = new DevicesTab();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    StringRequest request = new StringRequest(Request.Method.GET, API.baseUrl + "devices/events", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if (response.equals("")) return;
            String[] lines = response.split(System.getProperty("line.separator"));
            System.out.println(Arrays.toString(lines));
            String id = lines[3].split("\"")[3];
            String event = lines[4].split("\"")[3];
            Device triggered = null;
            for (Device d : DevicesTab.getDevices()) {
                if (d.getId().equals(id)) {
                    triggered = d;
                }
            }
            if (NotificationSettings.devicesMap.get(triggered) != null && NotificationSettings.devicesMap.get(triggered)) { //TODO revisar
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(API.mainActivity)
                                .setSmallIcon(R.drawable.ic_edit)
                                .setContentTitle("Notification from device: " + triggered.getName())
                                .setContentText(event)
                                .setAutoCancel(true)
                                .setColorized(true)
                                .setColor(getResources().getColor(R.color.colorPrimary))
                                .setDefaults(Notification.DEFAULT_VIBRATE)
                                .setPriority(Notification.PRIORITY_MAX);
                API.nManager.notify(0, mBuilder.build());

            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Error on notif request: " + error.getMessage());
        }
    });

   /*
    Thread requestThread = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    sleep(1000);
                    API.mRequestQueue.add(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        API.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeTab());
        mNavigationBar=findViewById(R.id.navigation);
        mNavigationBar.setSelectedItemId(R.id.navigation_home);
        mNavigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);//this
/*
        if(!requestThread.isAlive()){
            requestThread.start();
        }
*/


    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.help_id:
                        Intent helpIntent = new Intent(getApplicationContext(), Help.class);
                        startActivity(helpIntent);
                        return true;
                    case R.id.settings_id:
                        Intent settingsIntent = new Intent(getApplicationContext(), NotificationSettings.class);
                        startActivity(settingsIntent);
                        return true;
                }
                return true;
            }
        });
    }

    public void help(View v){
        Intent helpIntent = new Intent(getApplicationContext(), Help.class);
        startActivity(helpIntent);
    }

    public void settings(View v){
        Intent settingsIntent = new Intent(getApplicationContext(), NotificationSettings.class);
        startActivity(settingsIntent);
    }


}
