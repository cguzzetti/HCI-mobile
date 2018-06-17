package com.example.agusosimani.Homely.device;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class Device {
    private boolean on;
    private String name, id, type;

    protected Response.Listener doNothing = new Response.Listener() {
        @Override
        public void onResponse(Object response) {

        }
    };
    protected Response.ErrorListener error = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };

    Device(JSONObject obj) {
        try {
            this.name = obj.getString("name");
            this.id = obj.getString("id");
            this.type  = obj.getString("typeId");
            System.out.println(obj.getString("meta"));
            /*
             *  Por alguna razón el getJSONObject de meta no me funciona, así que hay que hardcodear
             */
            if ("{ status: false }".equals(obj.getString("meta"))){
                on = false;
            }else{
                on = true;
            }
        }catch(JSONException e){

        }
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;

        Device aux = (Device) o;
        return id.equals(aux.getId());

    }

    public String toString(){
        return name;
    }
    public boolean isOn(){
        return on;
    }

    public void changeStatus(){
        this.on = !this.on;
    }

    //getter and setters
    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getId(){
        return id;
    }

    public String getTypeID(){
        return type;
    }


}
