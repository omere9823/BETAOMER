package com.example.betaomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class YourPosition extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText eTfood;
    Button btnAddfood;
    String newfood;
    ArrayList<String> ars = new ArrayList<String>();
    ListView lv2;
    int choose;
    ArrayList<String> als = new ArrayList<>();
    ArrayList<String> sr = new ArrayList<>();;
    ArrayAdapter<CharSequence> adp;
    ArrayAdapter<String> adpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_position);


        btnAddfood=(Button)findViewById(R.id.btnAddfood);
        eTfood=(EditText)findViewById(R.id.eTfood);
        lv2 = findViewById(R.id.lvE);

        Intent dt = getIntent();
        choose = dt.getIntExtra("emda", 999);

        Toast.makeText(YourPosition.this, "" + choose, Toast.LENGTH_LONG).show();

        if (choose==0){
            als.add("Asado");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        if (choose == 1){
            als.add("");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        if (choose == 2){
            als.add("Asado");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        if (choose == 3){
            als.add("Asado");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        if (choose == 4){
            als.add("Asado");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        if (choose == 5){
            als.add("Asado");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        if (choose == 6){
            als.add("Asado");
            als.add("pargit");
            als.add("Wings");
            sr=als;
        }
        lv2.setAdapter(adp);
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
        als.add(newfood);

        /*
        TODO :
        1. add string to als as (TOMATO - FULL)
        2. add object to ArrayList of ingrediatns - New Ingrdiat(newfood)
         */


        // TOMATO - FULL
        //String s = "TOMATO - FULL";
        //String arr[] = s.split(" - ");
       //  arr[0] = TOMATO
        // arr[1] = FULL

        //String status = "MEDIUM";
        //String newS = arr[0]+" - "+status;


    }

    public void Finish(View view) {

        /*
        TODO :
        1. Save ArrayList of ingrediant to firebase event->station->position refernece (got from arguments from getExtra)
        2. Convert to to simple array
        3. Save to firebase using setValue();
        4. TOAST for any result (GOOD / BAD) + exception
         */

    }
}


