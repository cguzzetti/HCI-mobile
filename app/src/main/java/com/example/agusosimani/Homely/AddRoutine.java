package com.example.agusosimani.Homely;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.smarthome.tp2.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AddRoutine extends Activity {


    private static ImageButton save, back;
    private static EditText nameInput;
    private static String name;
    private static AddRoutine act;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_routine);
        act = this;
        save = findViewById(R.id.save);
        back = findViewById(R.id.back_arrow);
        nameInput = findViewById(R.id.name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                name = nameInput.getText().toString();
//                if(name.length() < 3){
//                    nameInput.setError(getResources().getString(R.string.device_name_error));
//                } else {
//                    JSONArray actions = new JSONArray();
//                    JSONObject dummyAction = new JSONObject();
//                    JSONObject obj = new JSONObject();
//                    try {
//                        dummyAction.put("deviceId", com.smarthome.tp2.Devices.DevicesTab.dummy.getId());
//                        dummyAction.put("actionName", "close");
//                        dummyAction.put("params", new JSONArray());
//                        dummyAction.put("meta", "dummy");
//                        obj.put("name", name);
//                        obj.put("actions", new JSONArray().put(dummyAction));
//                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Singleton.baseUrl + "routines", obj, new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                Toast.makeText(view.getContext(), getResources().getString(R.string.routine_added), Toast.LENGTH_LONG).show();
//                                finish();
//                            }
//                        }, null);
//                        Singleton.mRequestQueue.add(request);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        });
    }


}
