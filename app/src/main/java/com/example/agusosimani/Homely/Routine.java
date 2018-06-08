package com.example.agusosimani.Homely;

import android.widget.ListView;

//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.smarthome.tp2.Devices.AirConditioner;
//import com.smarthome.tp2.Devices.Alarm;
//import com.smarthome.tp2.Devices.Blind;
//import com.smarthome.tp2.Devices.Device;
//import com.smarthome.tp2.Devices.DevicesTab;
//import com.smarthome.tp2.Devices.Door;
//import com.smarthome.tp2.Devices.Lamp;
//import com.smarthome.tp2.Devices.Oven;
//import com.smarthome.tp2.Devices.Refrigerator;
//import com.smarthome.tp2.Singleton;

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
//    private  RoutineAdapter adapter;
//    private EditRoutine edit;

    public void setStatus(JSONObject status) {
        this.status = status;
    }

    public JSONObject getStatus() {
        return status;
    }

//    protected Response.Listener doNothing = new Response.Listener() {
//        @Override
//        public void onResponse(Object response) {
//
//        }
//    };
//    protected  Response.ErrorListener error = new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//        }
//    };

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

//    protected void apiRequest(String action, JSONArray params){
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, Singleton.baseUrl + "routines/" + id + "/" + action, params, doNothing, error);
//        Singleton.mRequestQueue.add(request);
//    }

//    protected void apiRequest(String action){
//        apiRequest(action, new JSONArray());
//    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

//    public void create(EditRoutine edit){
//        this.edit = edit;
//        update();
//        System.out.println(devices.toString());
//        adapter = new RoutineAdapter(edit, R.layout.adapter_view, devices);
//        ListView lView = edit.findViewById(R.id.list_view);
//        lView.setAdapter(adapter);
//    }

//    public void saveActions(Device device){
//        EditDeviceConfig.backup = new ArrayList<>();
//        ArrayList<JSONObject> aux = EditDeviceConfig.backup;
//        try {
//            switch (device.getType()) {
//                case "door": {
//                    Door d = (Door) device;
//                    aux.add(newAction(device).put("actionName", d.getState() ? "open" : "close").put("params", new JSONArray()).put("meta"," "));
//                    aux.add(newAction(device).put("actionName", d.getLock() ? "lock" : "unlock").put("params", new JSONArray()).put("meta"," "));
//                    break;
//                }
//                case "lamp": {
//                    Lamp l = (Lamp) device;
//                    aux.add(newAction(device).put("actionName", l.getState() ? "turnOn" : "turnOff").put("params", new JSONArray()).put("meta"," "));
//                    aux.add(newAction(device).put("actionName", "changeBrightness").put("params",new JSONArray().put(l.getBrightness())));
//                    aux.add(newAction(device).put("actionName", "changeColor").put("params",new JSONArray().put(l.getColor())));
//                    break;
//                }
//                case "oven": {
//                    Oven o = (Oven) device;
//                    aux.add(newAction(device).put("actionName", o.getState() ? "turnOn" : "turnOff").put("params", new JSONArray()).put("meta"," "));
//                    aux.add(newAction(device).put("actionName", "setTemperature").put("params",new JSONArray().put(o.getTemperature())));
//                    aux.add(newAction(device).put("actionName", "setConvection").put("params",new JSONArray().put(o.getConvection())));
//                    aux.add(newAction(device).put("actionName", "setGrill").put("params",new JSONArray().put(o.getGrill())));
//                    aux.add(newAction(device).put("actionName", "setHeat").put("params",new JSONArray().put(o.getHeat())));
//                    break;
//                }
//                case "refrigerator": {
//                    Refrigerator r = (Refrigerator) device;
//                    aux.add(newAction(device).put("actionName", "setTemperature").put("params",new JSONArray().put(r.getTemperature())));
//                    aux.add(newAction(device).put("actionName", "setFreezerTemperature").put("params",new JSONArray().put(r.getFreezerTemperature())));
//                    aux.add(newAction(device).put("actionName", "setMode").put("params",new JSONArray().put(r.getMode())));
//                    break;
//                }
//                case "ac": {
//                    AirConditioner a = (AirConditioner) device;
//                    aux.add(newAction(device).put("actionName", a.getState() ? "turnOn" : "turnOff").put("params", new JSONArray()).put("meta"," "));
//                    aux.add(newAction(device).put("actionName", "setTemperature").put("params",new JSONArray().put(a.getTemperature())));
//                    aux.add(newAction(device).put("actionName", "setFanSpeed").put("params",new JSONArray().put(a.getFanSpeed())));
//                    aux.add(newAction(device).put("actionName", "setHorizontalSwing").put("params",new JSONArray().put(a.getHorizontalSwing())));
//                    aux.add(newAction(device).put("actionName", "setMode").put("params",new JSONArray().put(a.getMode())));
//                    aux.add(newAction(device).put("actionName", "setVerticalSwing").put("params",new JSONArray().put(a.getVerticalSwing())));
//                    break;
//                }
//                case "timer": {/*
//                    TimerDevice t = (TimerDevice) device;
//                    aux.add(newAction(device).put("actionName", t.getState() ? "turnOn" : "turnOff").put("params", new Object[0]));
//                    Integer[] param = new Integer[1];
//                    param[0] = l.getBrightness();
//                    aux.add(newAction(device).put("actionName", "changeBrightness").put("params",param));
//                    String[] param1 = new String[1];
//                    param1[0] = l.getColor();
//                    aux.add(newAction(device).put("actionName", "changeColor").put("params",param1));
//                    */
//                    break;
//                }
//                case "alarm": {
//                    Alarm a = (Alarm) device;
//                    aux.add(newAction(device).put("actionName", (a.getState()=="disarmed" ? "disarm" : (a.getState()=="armedStay"?"armStay":"armAway"))).put("params", new JSONArray().put(a.getSecurityCode())).put("meta"," "));
//                    break;
//                }
//                case "blind": {
//                    Blind b = (Blind) device;
//                    aux.add(newAction(device).put("actionName", b.getState() ? "up" : "down").put("params", new JSONArray()).put("meta"," "));
//                    break;
//                }
//            }
//        } catch(JSONException e){
//            //e.printStackTrace();
//        }
//        actions.addAll(aux);
//    }

//    private JSONObject newAction(Device device){
//        try {
//            return new JSONObject().put("deviceId", device.getId());//.put("meta", " ");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public void save(){
//        JSONObject obj = new JSONObject();
//        try{
//            obj.put("name", name);
//            obj.put("actions", actions);
//            obj.put("meta", " ");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Singleton.baseUrl + "routines/" + id, obj, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//        }, null);
//        Singleton.mRequestQueue.add(request);
//    }


//    private void update(){
//        for(int i = 0; i < actions.size() ;i++){
//            for(Device dev: DevicesTab.devices){
//                try {
//                    JSONObject obj = actions.get(i);
//                    if(obj.getString("deviceId").equals(dev.getId())) {
//                        boolean present = false;
//                        for (Device d : devices) {
//                            if (d.getId().equals(dev.getId()))
//                                present = true;
//                        }
//                        if (!present)
//                            devices.add(dev);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
