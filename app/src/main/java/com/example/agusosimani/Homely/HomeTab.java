package com.example.agusosimani.Homely;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


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


    //hola
    public void update(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_tab, container, false);
        this.view = view;
        return view;
    }
}
