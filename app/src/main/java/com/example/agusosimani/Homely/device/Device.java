package com.example.agusosimani.Homely.device;

public class Device {
    private boolean on;
    private String name;

    Device(String name, boolean on){
        this.on = on;
        this.name = name;
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


}
