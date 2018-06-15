package com.example.agusosimani.Homely.device;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class Device {
    private boolean on;
    private String name, id;

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

    Device(String name, boolean on) {
        this.on = on;
        this.name = name;
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


}
