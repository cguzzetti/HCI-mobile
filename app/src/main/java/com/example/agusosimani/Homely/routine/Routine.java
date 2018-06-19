package com.example.agusosimani.Homely.routine;

import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.agusosimani.Homely.API;
import com.example.agusosimani.Homely.device.Device;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Routine {

    private String name, id;
    public List<Device> devices = new ArrayList<>();
    public ArrayList<JSONObject> actions;
    private JSONObject status;
    protected int layout;
    private  RoutinesAdapter adapter;

    public void setStatus(JSONObject status) {
        this.status = status;
    }

    public JSONObject getStatus() {
        return status;
    }

    protected Response.Listener doNothing = new Response.Listener() {
        @Override
        public void onResponse(Object response) {

        }
    };
    protected  Response.ErrorListener error = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
        }
    };

    public int getLayout() {
        return layout;
    }


    public Routine(JSONObject obj){
        try {
            this.name = obj.getString("name");
            this.id = obj.getString("id");
            actions = new ArrayList<>();
            JSONArray aux = obj.getJSONArray("actions");
            for (int i = 0; i < aux.length(); i++) {
                this.actions.add(aux.getJSONObject(i));
            }
        } catch(JSONException e){

        }
    }

    protected void apiRequest(String action, JSONArray params){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, API.baseUrl + "routines/" + id + "/" + action, params, doNothing, error);
        API.mRequestQueue.add(request);
    }

    protected void apiRequest(String action){
        apiRequest(action, new JSONArray());
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


}
