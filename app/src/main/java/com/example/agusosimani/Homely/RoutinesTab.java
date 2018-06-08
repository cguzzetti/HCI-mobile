package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoutinesTab extends Fragment {


    private static final String TAG = "RoutinesTab";

    //static CustomRoutineListAdapter adapter;
    //static ArrayList<Routine> routines;
    private ListView listView;
    private FloatingActionButton addRoutine;
    private View view;

    public static void deleteRoutine(Routine toRemove){
//        if(adapter != null){
//            if(toRemove != null){
//                JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Singleton.baseUrl+"routines/" + toRemove.getId(), new JSONObject(), new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("SUCCESS", "routine deleted.");
//                    }
//                },null);
//                Singleton.mRequestQueue.add(request);
//            }
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //if(CustomAdapter.mySnackbar != null && CustomAdapter.mySnackbar.isShown())
          //  CustomAdapter.mySnackbar.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.routines_tab, container, false);
        addRoutine = view.findViewById(R.id.add_routine);
        addRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddRoutine.class);
                startActivity(intent);
            }
        });

        update();
        return view;
    }

    private void update(){
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Singleton.baseUrl+"routines", new JSONObject(), new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    routines = new ArrayList<>();
//                    JSONArray array = response.getJSONArray("routines");
//                    System.out.println(routines);
//                    for(int i = 0; i < array.length() ; i++)
//                        routines.add(new Routine(array.getJSONObject(i)));
//                    adapter = new CustomRoutineListAdapter(getActivity(), R.layout.adapter_routine_view, routines);
//                    ListView lView = view.findViewById(R.id.list_view);
//                    lView.setAdapter(adapter);
//                }catch(JSONException e){
//                    Log.e("ERROR", e.getMessage());
//                }
//            }
//        },null);
//        Singleton.mRequestQueue.add(request);
    }

}
