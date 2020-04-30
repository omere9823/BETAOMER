package com.example.betaomer.model;

import java.io.Serializable;

public class Ingrediant implements Serializable {


    String _name;
    String _status;

    public Ingrediant(){}

    public Ingrediant(String n){
        this._name = n;
        this._status = "FULL";
    }

    public String get_status(){
        return this._status;
    }

    public void set_status(String s){
        this._status = s;
    }


    public String get_name(){
        return this._name;
    }

    public void set_name(String nam){
        this._name = nam;
    }

    @Override
    public String toString(){
        return this._name + " - "+ this._status;
    }
}
