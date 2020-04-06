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


    }

    public void AddFood(View view) {
        newfood = eTfood.getText().toString();
        als.add(newfood);
    }
}


