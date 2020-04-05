package com.example.betaomer;

import java.util.ArrayList;
import java.util.Date;

public class Eventt {

    String date,uid2;
    private Date date2;
    //ArrayAdapter<String> adp;
    //String[] ArrayList;
    ArrayList<String> ars;

    public Eventt(){}
    public Eventt(String date, ArrayList<String> ars){
        this.date=date;
        this.ars = ars;

        //this.adp = adp;
        //this.ArrayList = ArrayList;
    }

    public Eventt(String date, ArrayList<String> ars, Date dt){
        this.date=date;
        this.ars = ars;
        this.date2 = dt;

        //this.adp = adp;
        //this.ArrayList = ArrayList;
    }

    public Date getDate2(){
        return date2;
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
