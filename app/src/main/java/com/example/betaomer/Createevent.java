package com.example.betaomer;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Createevent extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String name,date;
    ListView lw1;
    Button DateBtn,BtnCr;
    DatePickerDialog dpd;
    Calendar c;
    TextView TVD;
    String [] ArrayList = {"Meat - ","Salad - ","Chicken - ","Fish - ","Asian - ","Pasta - ","Fried - "};
    String [] Sidor = {};
    ArrayAdapter<String> adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent);


        BtnCr = (Button) findViewById(R.id.btnCr);
        TVD = (TextView) findViewById(R.id.TVD);
        lw1 = (ListView) findViewById(R.id.lw1);
        DateBtn = (Button) findViewById(R.id.btnPD);
        lw1.setOnItemClickListener(this);
        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ArrayList);
        lw1.setAdapter(adp);

        DateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Createevent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                        Toast.makeText(Createevent.this, "" + dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
                        TVD.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, day, month, year);
                dpd.show();

            }
        });


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setMessage("enter the name");
        adb.setTitle("workers");
        adb.setView(edittext);

        adp=new ArrayAdapter<String> (this,R.layout.support_simple_spinner_dropdown_item,ArrayList);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                name = edittext.getText().toString();
                ArrayList[position] = ArrayList[position] + name;
                lw1.setAdapter(adp);


            }


        }


        );
        adb.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog ad = adb.create();
        ad.show();


    }

    public void BtnCr(View view) {
       date=TVD.getText().toString();
       Sidor=ArrayList[]


    }
}
