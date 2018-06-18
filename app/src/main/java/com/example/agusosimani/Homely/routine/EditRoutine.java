package com.example.agusosimani.Homely.routine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.R;
import com.example.agusosimani.Homely.device.Device;
import com.example.agusosimani.Homely.device.DevicesTab;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EditRoutine extends Activity {
    /*
    private static final String TAG = "EditRoutine";

    public static Routine routine;
    private ArrayList<Device> aux;
    private static List<Device> toAdd = new ArrayList<>();
    public EditRoutine edit = this;
    protected ImageButton save, back;
    private RoutinesAdapter adapter;
    private TextView name;
    private ListView listView;
    private FloatingActionButton add;
    public static ArrayList<JSONObject> toRemove,removed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine);

        save = findViewById(R.id.save);
        back = findViewById(R.id.back_arrow);
        add = findViewById(R.id.add_device);
        name = findViewById(R.id.name);

        name.setText(routine.getName());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                aux = (ArrayList) DevicesTab.getDevices().clone();
//                aux.removeAll(routine.devices);
                CharSequence names[] = new CharSequence[aux.size()];

                for(int i = 0; i < aux.size(); i++){
                    names[i] = aux.get(i).getName();
                }

                if(aux.size() != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(edit);
                    builder.setTitle("Choose a device");
                    builder.setItems(names, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            routine.devices.add(aux.get(which));
                            listView = edit.findViewById(R.id.list_view);
                            adapter = new RoutinesAdapter(EditRoutine.this, R.layout.adapter_view, routine.devices);
                            listView.setAdapter(adapter);
                        }
                    });
                    builder.show();
                }
                else{
                    add.setClickable(false);
                    Toast.makeText(EditRoutine.this, R.string.no_more_devices, Toast.LENGTH_LONG).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    for (Device d : routine.devices) {
                        boolean found = false;
                        for (JSONObject obj : routine.actions) {
                            if (obj.getString("deviceId").equals(d.getId()))
                                found = true;
                        }
                        if(!found) {
                            routine.saveActions(d);
                            System.out.println(routine.actions);
                        }
                    }
                } catch(JSONException e){

                }
                routine.save();
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API.baseUrl + "routines/" + routine.getId(), new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject status = response.getJSONObject("routine");
                    routine.setStatus(status);
                } catch(JSONException e) {
                }
                listView = findViewById(R.id.list_view);
                adapter = new RoutinesAdapter(EditRoutine.this, R.layout.adapter_view, routine.devices);
                listView.setAdapter(adapter);
                routine.create(edit);
            }
        }, null);
        API.mRequestQueue.add(request);
    }

    public static void deleteDevice(Device d){
        removed = toRemove;
        if(d == null) return;
        toRemove = new ArrayList<>();
        for(JSONObject obj: EditRoutine.routine.actions){
            try {
                if(obj.getString("deviceId").equals(d.getId()))
                    toRemove.add(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        EditRoutine.routine.actions.removeAll(toRemove);
    }

    public static void revertDelete(){
        //EditRoutine.routine.actions.addAll(toRemove);
    }
    */
}
