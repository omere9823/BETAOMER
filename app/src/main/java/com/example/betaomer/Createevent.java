package com.example.betaomer;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betaomer.model.Station;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refEventt;




public class Createevent extends AppCompatActivity /*implements AdapterView.OnItemClickListener*/ {


    String eventdate;
    String name,date,date2;
    ListView lw1;
    Button DateBtn ,BtnCr ,BtnAdd;
    DatePickerDialog dpd;
    Calendar c;
    TextView TVD;
    Eventt Eventt;
    ArrayList<String> ars;

    ArrayList<Station> stations;

    ArrayAdapter<String> adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent);


        BtnAdd = (Button) findViewById(R.id.btnAdd);
        BtnCr = (Button) findViewById(R.id.btnCr);
        TVD = (TextView) findViewById(R.id.TVD);
        lw1 = (ListView) findViewById(R.id.lw1);
        DateBtn = (Button) findViewById(R.id.btnPD);


        ars = new ArrayList<>();
        stations = new ArrayList<>();

        // OPEN DIALOG - WHEN "Create station" button click
        // ASK FOR (Station name) and (Employer name) - "salad - omri"
        // APPROVE - SUBMIT
        //stations.add(new Station("name"));
        //



       //adp = new ArrayAdapter<Station>(this, R.layout.support_simple_spinner_dropdown_item, stations);
        //lw1.setAdapter(adp);



        DateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                final int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(Createevent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Toast.makeText(Createevent.this, "" + dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
                        if (month<9) {
                            if (dayOfMonth<10) {
                                eventdate="" + year + "_0" + (month+1) + "_0" + dayOfMonth;
                            } else {
                                eventdate="" + year + "_0" + (month+1) + "_" + dayOfMonth;
                            }
                        } else if (dayOfMonth<10) {
                                eventdate="" + year + "_" + (month+1) + "_0" + dayOfMonth;
                            } else {
                                eventdate="" + year + "_" + (month+1) + "_" + dayOfMonth;
                        }
                        TVD.setText(eventdate);

                    }
                }, day, month, year);
                dpd.show();

            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public void BtnAD(View view) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setMessage("enter the name");
        adb.setTitle("Position and Worker");
        adb.setView(edittext);



        adp=new ArrayAdapter<String> (this,R.layout.support_simple_spinner_dropdown_item,ars);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        name = edittext.getText().toString();
                        ars.add(name);
                        stations.add(new Station(name));
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


    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setMessage("enter the name");
        adb.setTitle("workers");
        adb.setView(edittext);

        adp=new ArrayAdapter<String> (this,R.layout.support_simple_spinner_dropdown_item,ars);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                name = edittext.getText().toString();
                ars.set(position, ars.get(position) + name);
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
*/

    public void BtnCr(View view) {

        Eventt = new Eventt(eventdate, stations);
        try{
            refEventt.child(eventdate).setValue(Eventt);
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Successful registration", Toast.LENGTH_LONG).show();
        finish();

    }

}
