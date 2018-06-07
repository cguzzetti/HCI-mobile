package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.agusosimani.Homely.device.DevicesTab;

public class RoutinesActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_routines:
                    Intent intentR = new Intent(RoutinesActivity.this, RoutinesActivity.class);
                    startActivity(intentR);
                    return true;
                case R.id.navigation_home:
                    Intent intentH = new Intent(RoutinesActivity.this, MainActivity.class);
                    startActivity(intentH);
                    return true;
                case R.id.navigation_devices:
                    Intent intentD = new Intent(RoutinesActivity.this, DevicesTab.class);
                    startActivity(intentD);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_routines);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
    }
}