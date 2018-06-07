package com.example.agusosimani.Homely.device;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.agusosimani.Homely.R;

import java.util.ArrayList;

public class DevicesTab extends Fragment{
    private static String TAG = "device_list";
    private static ArrayList<Device> devices;
    private ListView listView;
    static DevicesArrayAdapter adapter;
    private View view;
    private FloatingActionButton addDevice;

    public DevicesTab(){
        super();
        devices = new ArrayList<>();
        devices.add(new Device("Dummy0",false));
        devices.add(new Device("Dummy1",true));
        devices.add(new Device("Dummy2",false));
        devices.add(new Device("Dummy3",true));
        devices.add(new Device("Dummy4",false));

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

        addDevice = view.findViewById(R.id.add_device);

        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddDevice.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
