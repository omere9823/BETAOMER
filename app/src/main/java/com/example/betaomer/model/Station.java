package com.example.betaomer.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable {
    String _name ;
    ArrayList<Ingrediant> _array;

    public Station(){}

    public Station(String name){
        this._name = name;
    }

    /*
    public Station(String name,String [] args){
        this._name = name;
        this._array = new Ingrediant[args.length];
        for(int i = 0 ; i < args.length ; i++){
            this._array[i] = new Ingrediant(args[i]);
        }
    }
     */

    /*
    public Station(String name, int amount){
        this._name = name;

        if(name == "salad"){ // salad
            this._array = new Ingrediant[amount];
            for(int i=0;i<amount;i++){
                this._array[i] = new Ingrediant("salad type - "+(i+1)); // TODO change to user input
            }

        } else if(name == "kebab"){ // kebab
            this._array = new Ingrediant[amount];
            for(int i=0;i<amount;i++){
                this._array[i] = new Ingrediant("kebab type - "+(i+1)); // TODO change to user input
            }
        }
    }

     */

/*    public void set_array(String[] args){
        this._array = new Ingrediant[args.length];
        for(int i = 0 ; i < args.length ; i++){
            this._array[i] = new Ingrediant(args[i]);
        }
    }

 */

    public void set_array(ArrayList<Ingrediant> arryList) {
        this._array = arryList;
    }

    public void set_name(String name){
        this._name = name;
    }

    public String get_name(){
        return this._name;
    }

    public ArrayList<Ingrediant> get_ingrediats(){
        return this._array;
    }

    /*
    public ArrayList<Ingrediant> get_array(){
        return this._array;
    }

     */


    /*public void setNewAmount(int position, Ingrediant.status st){
        this._array[position].set_status(st);
    }
     */

    /*
    public void setNewAmount(int position, String st){
        this._array[position].set_status(st);
    }

    public void setNewAmount(String name, String st){
        for(int i=0 ; i<this._array.length;i++){
            if(name == this._array[i].get_name()){
                this._array[i].set_status(st);
                return;
            }
        }
    }

     */

    @Override
    public String toString(){
        return this._name;
    }

}



//Station one = new Station("salad",["tomato","carrot"]);
