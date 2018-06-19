package com.example.agusosimani.Homely.routine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.R;
import com.example.agusosimani.Homely.device.Device;
import com.example.agusosimani.Homely.device.DevicesArrayAdapter;
import com.example.agusosimani.Homely.device.DevicesTab;

import org.json.JSONArray;
import org.json.JSONException;
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

        final String routineName = getItem(position).getName();

        TextView tvName = convertView.findViewById(R.id.routine_name);
        tvName.setText(routineName);
        Button run = convertView.findViewById(R.id.run_button);

        final View finalConvertView = convertView;
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Routine toRun = getItem(position);
                final JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "routines/" + toRun.getId() + "/execute", new JSONObject(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(finalConvertView.getContext(), finalConvertView.getResources().getString(R.string.run_routine), Toast.LENGTH_LONG).show();
                    }
                }, null);
                API.mRequestQueue.add(request);

                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, API.baseUrl + "routines", new JSONObject(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray routinesArr = response.getJSONArray("routines");
                            for (int i =0; i< routinesArr.length(); i++){
                                if(routinesArr.getJSONObject(i).getString("id").equals(toRun.getId())){
                                    JSONArray actions = routinesArr.getJSONObject(i).getJSONArray("actions");
                                    for(int j = 0; j<actions.length(); j++){
                                        switch(actions.getJSONObject(j).getString("actionName")) {
                                            //lamp et al
                                            case "turnOff":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(j).getString("deviceId"))){
                                                        if (d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: false }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            case "turnOn":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(j).getString("deviceId"))){
                                                        if (!d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: true }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            //blind
                                            case "up":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(j).getString("deviceId"))){
                                                        if (!d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: true }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            case "down":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(j).getString("deviceId"))){
                                                        if (d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: false }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            //door
                                            case "open":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(j).getString("deviceId"))){
                                                        if (!d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: true }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            case "close":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(i).getString("deviceId"))){
                                                        if (d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: false }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            //alarm
                                            case "armStay":
                                                System.out.println("ENTRE2");
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(i).getString("deviceId"))){
                                                        if (!d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: true }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            case "armAway":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(i).getString("deviceId"))){
                                                        if (!d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: true }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            case "disarm":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(i).getString("deviceId"))){
                                                        if (d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: false }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            //timer
                                            case "start":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(i).getString("deviceId"))){
                                                        if (!d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: true }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;
                                            case "stop":
                                                for (Device d: DevicesTab.getDevices()){
                                                    if (d.getId().equals(actions.getJSONObject(i).getString("deviceId"))){
                                                        if (d.isOn()){
                                                            d.changeStatus();
                                                            JSONObject dev = new JSONObject();
                                                            dev.put("name", d.getName());
                                                            dev.put("id", d.getId());
                                                            dev.put("typeId", d.getTypeID());
                                                            dev.put("meta", "{ status: false }");
                                                            JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.PUT, API.baseUrl + "devices/" + d.getId(), dev, new Response.Listener<JSONObject>() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {

                                                                }
                                                            }, null);
                                                            API.mRequestQueue.add(request3);

                                                        }

                                                    }
                                                }
                                                break;


                                        }

                                    }

                                }
                            }
                        }catch(JSONException e){
                            //handler
                        }
                    }
                }, null);
                API.mRequestQueue.add(request2);
            }
        });



        return convertView;
    }


}
