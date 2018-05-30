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

    private ArrayList<String> devices = new ArrayList<>();

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

        devices.add("Oven");
        devices.add("AC");
        devices.add("Johns lights");
        devices.add("Living room lights");
        devices.add("Johns computer");
        devices.add("Hallway lights");


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(DevicesActivity.this, android.R.layout.simple_list_item_activated_1, devices);
        final ListView listview = (ListView) findViewById(R.id.list_view);
        if (listview != null){
            listview.setAdapter(adapter);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DevicesActivity.this,"Activated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
