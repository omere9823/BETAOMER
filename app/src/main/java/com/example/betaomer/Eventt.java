package com.example.betaomer;

import com.example.betaomer.model.Station;

import java.util.ArrayList;
import java.util.Date;

public class Eventt {

    String date;
    private Date date2;
    //ArrayAdapter<String> adp;
    //String[] ArrayList;
    ArrayList<Station> ars;

    public Eventt(){}
    public Eventt(String date, ArrayList<Station> ars){
        this.date=date;
        this.ars = ars;

    }

    public Eventt(String date, ArrayList<Station> ars, Date dt){
        this.date=date;
        this.ars = ars;


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

    public ArrayList<Station> getArs() {
        return ars;
    }

    public void setArs(ArrayList<Station> ars) {
        this.ars = ars;
    }


}
