package com.example.agusosimani.Homely;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class DevicesTab extends Fragment{
    private static String TAG = "device_list";
    private static ArrayList<Device> devices;
    private ListView listView;
    private View view;

    @Override
    public void onResume(){
        update();
        super.onResume();
    }

    public void update(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.device_list, container, false );

        return view;
    }
}
