package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.smarthome.tp2.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DevicesTab extends Fragment {

    private static final String TAG = "DevicesTab";
    public static Device dummy;
    static CustomAdapter adapter;
    public static ArrayList<Device> devices, freeDevices;
    private ListView listView;
    private FloatingActionButton addDevice;
    private View view;

    public static Device getDummy(){
        return dummy;
    }

/*
    @Override
    public void onPause() {
        super.onPause();
        if(CustomAdapter.mySnackbar != null && CustomAdapter.mySnackbar.isShown())
            CustomAdapter.mySnackbar.dismiss();
    }*/

    @Override
    public void onResume() {
        update();
        super.onResume();
    }

    public static ArrayList<Device> filterDevices(List<String> used){
        freeDevices = new ArrayList<>();
        if(devices == null) return freeDevices;
        for(Device d: devices){
            if(!used.contains(d.getId()))
                freeDevices.add(d);
        }
        return freeDevices;
    }

    public static void deleteDevice(Device toRemove){
        if(adapter != null){
            if(toRemove != null){
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Singleton.baseUrl+"devices/" + adapter.getToRemove().getId(), new JSONObject(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },null);
                Singleton.mRequestQueue.add(request);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.devices_tab, container, false);

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

    private void addDummy(){
        JSONObject json = new JSONObject();
        try {
            json.put("typeId", "lsf78ly0eqrjbz91");
            json.put("name", "dummy");
            json.put("meta", "{ type: dummy }");
        }
        catch(JSONException e){

        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,Singleton.baseUrl+"devices", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dummy = new Dummy(response);
            }
        },null);
        Singleton.mRequestQueue.add(request);
    }

    public void update(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,Singleton.baseUrl+"devices", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    devices = new ArrayList<>();
                    JSONArray array = response.getJSONArray("devices");
                    for(int i = 0; i < array.length() ; i++){
                        Device toAdd = null;
                        boolean isDummy = false;
                        switch(array.getJSONObject(i).getString("meta")){
                            case "{ type: lamp }":
                                toAdd = new Lamp(array.getJSONObject(i));
                                break;
                            case "{ type: ac }":
                                toAdd = new AirConditioner(array.getJSONObject(i));
                                break;
                            case "{ type: oven }":
                                toAdd = new Oven(array.getJSONObject(i));
                                break;
                            case "{ type: door }":
                                toAdd = new Door(array.getJSONObject(i));
                                break;
                            case "{ type: refrigerator }":
                                toAdd = new Refrigerator(array.getJSONObject(i));
                                break;
                            case "{ type: timer }":
                                toAdd = new TimerDevice(array.getJSONObject(i));
                                break;
                            case "{ type: blind }":
                                toAdd = new Blind(array.getJSONObject(i));
                                break;
                            case "{ type: alarm }":
                                toAdd = new Alarm(array.getJSONObject(i));
                                break;
                            case "{ type: dummy }":
                                isDummy = true;
                                dummy = new Dummy(array.getJSONObject(i));
                                break;
                        }
                        if(!isDummy)
                            DevicesTab.devices.add(toAdd);
                    }
                    if(dummy == null){
                        addDummy();
                    }
                    adapter = new CustomAdapter(getActivity(), R.layout.adapter_view, DevicesTab.devices);
                    ListView lView = view.findViewById(R.id.list_view);
                    lView.setAdapter(adapter);
                    lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), devices.get(i).getClass());
                            startActivity(intent);
                        }
                    });
                }catch(Exception e){

                }
            }
        },null);
        Singleton.mRequestQueue.add(request);
    }
}
