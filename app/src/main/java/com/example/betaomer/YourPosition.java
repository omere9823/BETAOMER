package com.example.betaomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.betaomer.model.Ingrediant;
import com.example.betaomer.model.Station;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.betaomer.FBref.refEventt;

public class YourPosition extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Ingrediant> ingrediantArrayList ;

    EditText eTfood;
    Button btnAddfood;
    String newfood;
    ListView lv2;
    ArrayList<String> sr = new ArrayList<>();;
    ArrayAdapter<String> adpp;
    private String _event_date;
    private int _station_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_position);

        this.ingrediantArrayList = new ArrayList<Ingrediant>();


        btnAddfood=(Button)findViewById(R.id.btnAddfood);
        eTfood=(EditText)findViewById(R.id.eTfood);
        lv2 = findViewById(R.id.lvE);

        Intent dt = getIntent();

        _event_date = dt.getStringExtra("event_date");
        _station_position = dt.getIntExtra("station_position",0);


        adpp = new ArrayAdapter<String>(YourPosition.this, R.layout.support_simple_spinner_dropdown_item, sr);
        lv2.setAdapter(adpp);


        lv2.setOnItemClickListener(YourPosition.this);
        lv2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*
        TODO :
        1. Open dialog and send item POSITION from ArrayList with als.get(2)
           1.1.  From the dialog, get the text from als with the POSITION - "CARROT - FULL" - and put it inside textview
           1.2   update string inside the textview
        2. on "OK" click, set the value from the als by the POSITION with the new string - "CARROT - EMPTY"
        3. if no change - adapter.updatePosition(3); // adapter.update(4)
        4. update the ingrediants ArrayList by position
         */

    }

    public void AddFood(View view) {
        newfood = eTfood.getText().toString();
        Ingrediant ingrediant = new Ingrediant(newfood);
        this.ingrediantArrayList.add(ingrediant);
        sr.add(ingrediant.toString());
        adpp.notifyDataSetChanged();



    }

    public void Finish(View view) {

        try {
            final Context context  = this;
            final DatabaseReference dr1 = refEventt.child(_event_date)
                    .child("ars")
                    .child(String.valueOf(_station_position));

            Query query = dr1;
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        Station station2 = dataSnapshot.getValue(Station.class);
                        station2.set_array(ingrediantArrayList);
                        dr1.setValue(station2);
                        Toast.makeText(context,"OK",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        } catch (Exception e){
            e.printStackTrace();
        }

        /*
        TODO :
        1. Save ArrayList of ingrediant to firebase event->station->position refernece (got from arguments from getExtra)
        2. Convert to to simple array
        3. Save to firebase using setValue();
        4. TOAST for any result (GOOD / BAD) + exception
         */
    }
}


