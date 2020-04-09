package com.example.betaomer.model;

import java.io.Serializable;

public class Ingrediant implements Serializable {
    static enum status  {
            FULL,
            MEDIUM,
            LOW,
            EMPTY
    };

    private String _name;
    private status _status;

    public Ingrediant(){}

    public Ingrediant(String name){
        this._name = _name;
        this._status = status.FULL;
    }

    public String get_name(){
        return this._name;
    }
    public status get_status(){
        return this._status;
    }

    public void set_status(status s){
        this._status = s;
    }

    public void set_name(String nam){
        this._name = nam;
    }


}
