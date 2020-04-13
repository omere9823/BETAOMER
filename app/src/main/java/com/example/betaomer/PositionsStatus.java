package com.example.betaomer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.betaomer.model.Ingrediant;
import com.example.betaomer.model.Station;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import static com.example.betaomer.FBref.refEventt;


public class PositionsStatus extends Activity {
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_positions_status);


        TextView title = (TextView)findViewById(R.id.cph1);
        this.tableLayout = (TableLayout)findViewById(R.id.cp_tl);

        Intent dt = getIntent();

        String titleraw = dt.getStringExtra("title");
        titleraw = titleraw.replace('_','/');
        title.setText(String.format("Event day - %s", titleraw));

        this.tableLayout.removeAllViews();
        //fillTitleFromStation(null);
        //fillIngrediants(null);


        final Query query =  refEventt.child("2020_08_13").child("ars");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ArrayList<Station> arrayList = new ArrayList<Station>();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Station st = ds.getValue(Station.class);

                        ArrayList<Ingrediant> arrayListIngrediants = new ArrayList<>();
                        for(DataSnapshot obj : ds.child("_ingrediats").getChildren()){
                            Ingrediant ingrediant = obj.getValue(Ingrediant.class);
                            arrayListIngrediants.add(ingrediant);
                        }
                        st.set_array(arrayListIngrediants);

                        //arrayList.add(st);
                        fillTitleFromStation(st);
                        fillIngrediants(st.get_ingrediats());
                    }
                    //Station arrayList [] = dataSnapshot.getValue(Station.class);
                    //Query query2 =  query.getRef().child("ars");


                    //Eventt eventt = dataSnapshot.getValue(Eventt.class);
                    //handleEvent(eventt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //fetch table to variable
        // get event id from getContext() exats - string - "2024_07_24"
        // query to get all the data
        // fill table
            // each station will have special header
            // each station will print all ingrediant
    }

    private void handleStations(ArrayList<Station> arrayList){}

    private void handleEvent(Eventt eventt){
        ArrayList<Station> arrayListStation = eventt.getArs();
        for(Station station : arrayListStation){
            fillTitleFromStation(station);
            fillIngrediants(station.get_ingrediats());
        }
    }

    private void fillTitleFromStation(Station station){
        TextView textView = new TextView(this);
        textView.setText(station.get_name());
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setPadding(4,4,4,4);

        TableRow tableRow = new TableRow(this);
        tableRow.setHorizontalGravity(1);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.MATCH_PARENT));

        tableRow.addView(textView);

        //this.tableLayout.removeAllViews();
        this.tableLayout.addView(tableRow);
        //this.tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    }

    private void fillIngrediants(ArrayList<Ingrediant> arrayList){
        TextView textView = new TextView(this);
        textView.setText("Tomato");
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView.setPadding(4,4,4,4);

        TextView textView2 = new TextView(this);
        textView2.setText("FULL");
        textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        textView2.setPadding(4,4,4,4);


        TableRow tableRow = new TableRow(this);
        tableRow.setHorizontalGravity(1);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.MATCH_PARENT));

        tableRow.addView(textView);
        tableRow.addView(textView2);

        //this.tableLayout.removeAllViews();
        this.tableLayout.addView(tableRow);
    }

}
