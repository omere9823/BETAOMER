package com.example.betaomer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.betaomer.model.Station;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refEventt;

public class Selectedevent extends AppCompatActivity {

    TextView title;
    ListView listView;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedevent);

        title = (TextView)findViewById(R.id.title10);
        listView = (ListView) findViewById(R.id.lv10);

        Intent dt = getIntent();

        String titleraw = dt.getStringExtra("title");
        String titleraw2 = titleraw.replace('_','/');
        title.setText(String.format("Event day - %s", titleraw2));


        Query query =  refEventt.orderByChild("date").equalTo(titleraw);
        /**
         * Bring an event list by a specific date.
         */
        query.addListenerForSingleValueEvent(VEL);
    }

    com.google.firebase.database.ValueEventListener VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dS) {
            if (dS.exists()) {

                List<String> strings  = new ArrayList<>();
                for(DataSnapshot data : dS.getChildren()) {

                    Eventt eventt = data.getValue(Eventt.class);
                    ArrayList<Station> arrayList = eventt.ars;

                    strings = new ArrayList<>(arrayList.size());
                    /**
                     * Bringing the list of positions to a string.
                     */

                    for (Station station : arrayList) {
                        strings.add(station != null ? station.toString() : null);
                    }

                }
                adp = new ArrayAdapter<String>(Selectedevent.this, R.layout.support_simple_spinner_dropdown_item, strings);
                listView.setAdapter(adp);
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };



}
