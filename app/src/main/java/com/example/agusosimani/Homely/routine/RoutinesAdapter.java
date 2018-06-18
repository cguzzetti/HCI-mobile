package com.example.agusosimani.Homely.routine;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class RoutinesAdapter extends ArrayAdapter<Routine> {

    private static final String TAG = "RoutineAdapter";
    private List<Routine> routines = new ArrayList<>();
    private Context context;
    private int resource;

    public RoutinesAdapter(Context context, int resource, List<Routine> routines) {
        super(context, resource, routines);
        this.routines = routines;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        String routineName = getItem(position).getName();

        TextView tvName = convertView.findViewById(R.id.routine_name);
        tvName.setText(routineName);
        Button run = convertView.findViewById(R.id.run_button);

        final View finalConvertView = convertView;
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Routine toRun = getItem(position);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "routines/" + toRun.getId() + "/execute", new JSONObject(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(finalConvertView.getContext(), finalConvertView.getResources().getString(R.string.run_routine), Toast.LENGTH_LONG).show();
                    }
                }, null);
                API.mRequestQueue.add(request);
            }
        });



        return convertView;
    }


}
