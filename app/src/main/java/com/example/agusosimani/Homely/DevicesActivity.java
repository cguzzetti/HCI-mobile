package com.example.agusosimani.Homely;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DevicesActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_routines:
                    Intent intentR = new Intent(DevicesActivity.this, RoutinesActivity.class);
                    startActivity(intentR);
                    return true;
                case R.id.navigation_home:
                    Intent intentH = new Intent(DevicesActivity.this, MainActivity.class);
                    startActivity(intentH);
                    return true;
                case R.id.navigation_devices:
                    Intent intentD = new Intent(DevicesActivity.this, DevicesActivity.class);
                    startActivity(intentD);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_devices);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);

        Device[] values = new Device[]{
                new Device("Luke's bedroom light", false),
                new Device("Hallway light", true),
                new Device("Oven", false),
                new Device( "AC", true),
                new Device( "Robert's Computer", false)
        };

        DevicesArrayAdapter adapter = new DevicesArrayAdapter(this, values);

        ListView listView = (ListView) this.findViewById(R.id.list_view);
        if (listView != null){
            listView.setAdapter(adapter);
        }

    }
}
