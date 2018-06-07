package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.smarthome.tp2.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class HomeTab extends Fragment {
    private static final String TAG = "HomeTab";

    private GridView grid;
    public static ArrayList<Device> favouriteDevices;
    FloatingActionButton add;
    private int[] images = {R.drawable.ic_delete, R.drawable.ic_edit, R.drawable.ic_launcher_background};
    private View view;
    private static int aux;

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private void filterDevices(){
        final ArrayList<String> used = new ArrayList<>();
        aux = 0;
        final int count = rooms.size();
        for(Room r: rooms){
            Singleton.mRequestQueue.add(new JsonObjectRequest(Request.Method.GET, Singleton.baseUrl + "rooms/" + r.getId() + "/devices", new JSONObject(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray array = response.getJSONArray("devices");
                        for (int i = 0; i < array.length(); i++) {
                            used.add(array.getJSONObject(i).getString("id"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(++aux >= count){
                        freeDevices = DevicesTab.filterDevices(used);
                    }
                }
            }, null));
        }
    }

    public void update(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Singleton.baseUrl + "rooms",new JSONObject(), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                rooms = new ArrayList<>();
                try {
                    JSONArray array = response.getJSONArray("rooms");
                    for(int i = 0; i < array.length() ; i++) {
                        rooms.add(new Room(array.getJSONObject(i)));
                    }
                    final GridAdapter adapter = new GridAdapter(view.getContext(), R.layout.grid_single_item, rooms);
                    grid.setAdapter(adapter);

                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            EditRoom.room = adapter.getItem(i);
                            Intent intent = new Intent(view.getContext(), EditRoom.class);
                            startActivity(intent);
                        }
                    });
                    filterDevices();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        Singleton.mRequestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rooms_tab, container, false);
        this.view = view;

        grid = view.findViewById(R.id.grid_view);
        update();

        add = view.findViewById(R.id.add_room);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddRoom.class));
            }
        });
        return view;
    }
}
