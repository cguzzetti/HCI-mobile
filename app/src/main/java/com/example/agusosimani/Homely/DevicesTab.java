package com.example.agusosimani.Homely;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DevicesTab extends Fragment{
    private static String TAG = "device_list";
    private static ArrayList<Device> devices;
    private ListView listView;
    static DevicesArrayAdapter adapter;
    private View view;

    public DevicesTab(){
        super();
        devices = new ArrayList<>();
        devices.add(new Device("dummy0",false));
        devices.add(new Device("dummy1",false));
        devices.add(new Device("dummy2",false));
        devices.add(new Device("dummy3",false));
        devices.add(new Device("dummy4",false));

    }
    @Override
    public void onResume(){
        update();
        super.onResume();
    }

    public void update(){
        adapter = new DevicesArrayAdapter(getActivity(), R.layout.list_view_item, DevicesTab.devices);
        ListView lView = view.findViewById(R.id.list_view);
        lView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.device_list, container, false );

        return view;
    }

}
