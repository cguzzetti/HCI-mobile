package com.example.agusosimani.Homely.routine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.agusosimani.Homely.R;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoutinesTab extends Fragment {


    private static final String TAG = "RoutinesTab";
    static RoutinesAdapter adapter;
    static ArrayList<Routine> routines;
    private ListView listView;
    private FloatingActionButton addRoutine;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.routines_tab, container, false);
        update();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private void update(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API.baseUrl+"routines", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    routines = new ArrayList<>();
                    JSONArray array = response.getJSONArray("routines");
                    System.out.println(routines);
                    for(int i = 0; i < array.length() ; i++)
                        routines.add(new Routine(array.getJSONObject(i)));
                    adapter = new RoutinesAdapter(getActivity(), R.layout.adapter_routine_view, routines);
                    ListView lView = view.findViewById(R.id.list_view);
                    lView.setAdapter(adapter);
                }catch(JSONException e){
                    Log.e("ERROR", e.getMessage());
                }
            }
        },null);
        API.mRequestQueue.add(request);
    }

}
