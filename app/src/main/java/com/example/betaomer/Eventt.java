package com.example.betaomer;

import java.util.ArrayList;

public class Eventt {

    String date,uid2;
    //ArrayAdapter<String> adp;
    //String[] ArrayList;
    ArrayList<String> ars;

    public Eventt(){}
    public Eventt(String date, ArrayList<String> ars){
        this.date=date;
        this.ars = ars;
        this.uid2=uid2;

        //this.adp = adp;
        //this.ArrayList = ArrayList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getArs() {
        return ars;
    }

    public void setArs(ArrayList<String> ars) {
        this.ars = ars;
    }

    public String getUid() {
        return uid2;
    }

    public void setUid(String uid) {
        this.uid2=uid;
    }
    /* public String[] getArrayList() {
        return ArrayList;
    }

    public void setArrayList(String[] arrayList) {
        ArrayList = arrayList;
    }*/
    /*public ArrayAdapter<String> getAdp() {
        return adp;
    }

    public void setAdp(ArrayAdapter<String> adp) {
        this.adp = adp;
    }*/
}
