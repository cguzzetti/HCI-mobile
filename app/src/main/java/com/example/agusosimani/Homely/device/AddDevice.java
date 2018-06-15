package com.example.agusosimani.Homely.device;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddDevice extends Activity {
    private static final String TAG = "AddDevice";
    private static Spinner type;
    private static ArrayAdapter typeAdapter;
    private static EditText nameInput;
    private static ImageButton save, back;
    private String name;
    private String typeSelection;
    private static HashMap<String,String> typeMap;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_device);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API.baseUrl + "devicetypes", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray array = response.getJSONArray("devices");
                    typeMap = new HashMap<>();
                    for(int i =0; i< array.length(); i++){
                        typeMap.put(array.getJSONObject(i).getString("name"), array.getJSONObject(i).getString("id"));
                    }
                }catch(JSONException e){

                }
            }
        }, null);
        API.mRequestQueue.add(request);
        setUpType();
        save = findViewById(R.id.save);
        back = findViewById(R.id.back_arrow);
        nameInput = findViewById(R.id.name);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view){
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view){
                name = nameInput.getText().toString();
                if (name.length() ==0){
                    nameInput.setError(getResources().getString(R.string.device_name_error));
                }else{
                    JSONObject json = new JSONObject();
                    try{
                        json.put("typeId", typeMap.get(typeSelection.toString().toLowerCase()));
                        json.put("name", name);
                        json.put("meta", "{ type: " + typeSelection.toString().toLowerCase() + " }");
                    }catch(JSONException e){

                    }
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API.baseUrl + "devices", json, new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.added_successfully), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }, null);
                    API.mRequestQueue.add(request);
                }
            }
        });
    }


    private void setUpType(){
        type= findViewById(R.id.type);

        typeAdapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(typeAdapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeSelection = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
