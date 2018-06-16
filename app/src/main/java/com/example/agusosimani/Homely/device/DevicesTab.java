package com.example.agusosimani.Homely.device;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    }
    @Override
    public void onResume(){
        update();
        super.onResume();
    }

    public void update(){
        System.out.println("Entro");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API.baseUrl + "devices", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                   devices = new ArrayList<>();
                    JSONArray array =response.getJSONArray("devices");
                    for(int i =0; i< array.length(); i++){
                        Device toAdd = null;
                        switch(array.getJSONObject(i).getString("typeId")){
                            case "go46xmbqeomjrsjr":
                                toAdd = new Lamp(array.getJSONObject(i));
                                break;
                            case "li6cbv5sdlatti0j":
                                toAdd = new AC(array.getJSONObject(i));
                                break;
                            case "im77xxyulpegfmv8":
                                toAdd = new Oven(array.getJSONObject(i));
                                break;
                            case "lsf78ly0eqrjbz91":
                                toAdd = new Door(array.getJSONObject(i));
                                break;
                            case "rnizejqr2di0okho":
                                toAdd = new Refrigerator(array.getJSONObject(i));
                                break;
                            case "ofglvd9gqX8yfl3l":
                                toAdd = new Timer(array.getJSONObject(i));
                                break;
                            case "eu0v2xgprrhhg41g":
                                toAdd = new Blind(array.getJSONObject(i));
                                break;
                            case "mxztsyjzsrq7iaqc":
                                toAdd = new Alarm(array.getJSONObject(i));
                                break;
                        }
                        devices.add(toAdd);
                    }
                    adapter = new DevicesArrayAdapter(getActivity(), R.layout.list_view_item, DevicesTab.devices);
                    ListView lView = view.findViewById(R.id.list_view);
                    lView.setAdapter(adapter);

                }catch(JSONException e){

                }
            }
        }, null);
    API.mRequestQueue.add(request);

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
