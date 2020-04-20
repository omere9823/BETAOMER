package com.example.betaomer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
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
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

import static com.example.betaomer.FBref.refEventt;

public class PositionsStatus extends Activity {
    private TableLayout tableLayout;
    private TableRow.LayoutParams tvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_positions_status);


        TextView title = (TextView)findViewById(R.id.cph1);
        this.tableLayout = (TableLayout)findViewById(R.id.cp_tl);

        Intent dt = getIntent();

        final String titleraw = dt.getStringExtra("title");
        String titleraw2 = titleraw.replace('_','/');
        title.setText(String.format("Event day - %s", titleraw2));

        this.tableLayout.removeAllViews();

        this.tvp = new TableRow.LayoutParams(this,null);
        this.tvp.width = TableRow.LayoutParams.FILL_PARENT;
        this.tvp.height = TableRow.LayoutParams.WRAP_CONTENT;


        TimerTask scanTask;
        final Handler handler = new Handler();
        Timer t = new Timer();

        scanTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {

                        fetchResults(titleraw);

                    }
                });
            }};
        t.schedule(scanTask, 500,10000);  // here is t.schedule( , delay, period);



    }

    private void fetchResults(String titleraw){


        final Query query =  refEventt.child(titleraw).child("ars");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    tableLayout.removeAllViews();

                    ArrayList<Station> arrayList = new ArrayList<Station>();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Station st = ds.getValue(Station.class);

                        ArrayList<Ingrediant> arrayListIngrediants = new ArrayList<>();
                        for(DataSnapshot obj : ds.child("_ingrediats").getChildren()){
                            Ingrediant ingrediant = obj.getValue(Ingrediant.class);
                            arrayListIngrediants.add(ingrediant);
                        }
                        st.set_array(arrayListIngrediants);
                        fillTitleFromStation(st);
                        fillIngrediants(st.get_ingrediats());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
        // create layout params for textview
        TableRow.LayoutParams params = new TableRow.LayoutParams(this,null);
        params.span = 2;
        params.width = TableRow.LayoutParams.FILL_PARENT;
        params.height = TableRow.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;

        // create text view
        TextView textView = new TextView(this);
        textView.setText(station.get_name());
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        //textView.setPadding(0,10,0,10);
        textView.setLayoutParams(params); // causes layout update


        // set layout params for row
        TableRow.LayoutParams newHead = new TableRow.LayoutParams(this,null);
        newHead.width = TableRow.LayoutParams.FILL_PARENT;
        newHead.height = TableRow.LayoutParams.WRAP_CONTENT;
        //newHead.setMargins(10,10,10,10);

        // set row
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(newHead);
        tableRow.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        // add textview to row
        tableRow.addView(textView);

        // add row to table
        this.tableLayout.addView(tableRow);
    }

    private void fillIngrediants(ArrayList<Ingrediant> arrayList){

        for(Ingrediant ingrediant : arrayList){
            // First CELL
            TextView textView = new TextView(this);
            textView.setText(ingrediant.get_name());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setLayoutParams(this.tvp);
            //textView.setPadding(0,10,0,10);

            // Second CELL
            TextView textView2 = new TextView(this);
            textView2.setText(ingrediant.get_status());
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView2.setLayoutParams(this.tvp);
            //textView.setPadding(0,10,0,10);
            switch (ingrediant.get_status()){
                case "FULL" : textView2.setBackgroundColor(getResources().getColor(R.color.full)); break;
                case "MED" : textView2.setBackgroundColor(getResources().getColor(R.color.med)); break;
                case "LOW" : textView2.setBackgroundColor(getResources().getColor(R.color.low)); break;
                case "EMPTY" : textView2.setBackgroundColor(getResources().getColor(R.color.empty)); break;
                default: break;
            }

            // set row
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(this.tvp);

            tableRow.addView(textView);
            tableRow.addView(textView2);

            this.tableLayout.addView(tableRow);
        }

    }

}
