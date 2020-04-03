package com.example.betaomer;

import java.util.ArrayList;

public class Eventt {

    String date;
    ArrayList <String> arrayList;

    public Eventt(){}
    public Eventt(String date, ArrayList<String> arrayList){
        this.date=date;
        this.arrayList=arrayList;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public String getDate() {
        return date;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
