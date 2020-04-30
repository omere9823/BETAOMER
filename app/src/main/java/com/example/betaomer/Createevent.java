package com.example.betaomer;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import static com.example.betaomer.FBref.refAuth;
import static com.example.betaomer.FBref.refEventt;


public class Createevent extends AppCompatActivity /*implements AdapterView.OnItemClickListener*/ {


    String eventdate, viewdate;
    String name;
    ListView lw1;
    Button DateBtn, BtnCr, BtnAdd;
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


        DateBtn.setOnClickListener(new View.OnClickListener() { //לחיצה על כפתור התאריך נפתח לוח שנה ובוחרים בו תאריך
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
                        if (month < 9) {
                            if (dayOfMonth < 10) {
                                eventdate = "" + year + "_0" + (month + 1) + "_0" + dayOfMonth;
                                viewdate = "" + dayOfMonth + "_0" + (month + 1) + "_0" + year;
                            } else {
                                eventdate = "" + year + "_0" + (month + 1) + "_" + dayOfMonth;
                                viewdate = "" + dayOfMonth + "_0" + (month + 1) + "_" + year;
                            }
                        } else if (dayOfMonth < 10) {
                            eventdate = "" + year + "_" + (month + 1) + "_0" + dayOfMonth;
                            viewdate = "" + dayOfMonth + "_" + (month + 1) + "_0" + year;

                        } else {
                            eventdate = "" + year + "_" + (month + 1) + "_" + dayOfMonth;
                            viewdate = "" + dayOfMonth + "_" + (month + 1) + "_" + year;
                        }
                        TVD.setText(viewdate);

                    }
                }, day, month, year);
                dpd.show();

            }
        });
    }


    public void BtnAD(View view) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setMessage("enter the name");
        adb.setTitle("Position and Worker");
        adb.setView(edittext);


        adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ars);

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

    } //הוספת עמדה לרשימה עם שם העמדה ושם העובד ע"י אדיט טקסט


    public void BtnCr(View view) {

        Eventt = new Eventt(eventdate, stations);
        try {
            refEventt.child(eventdate).setValue(Eventt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Successful registration", Toast.LENGTH_LONG).show();
        finish();

    } //יצירת האירוע ע"י הכנסת הנתונים לפייר בייס וסגירת המסך


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    } //יצירת תפריט

    public boolean onOptionsItemSelected(MenuItem item) {

        String str = item.getTitle().toString();

        if (str.equals("Credits")) {

            Intent t = new Intent(this, Credits.class);
            startActivity(t);
        }

        if (str.equals("History Events")) {

            Intent t = new Intent(this, HistoryEvents.class);
            startActivity(t);
        }

        if (str.equals("LogOut")) {
            refAuth.signOut();
            SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("stayConnect",false);
            editor.commit();
            Intent t = new Intent(this, MainActivity.class);
            startActivity(t);
        }


        return true;
    } //העברת אקטיביטי לתפריט שנלחץ עליו

}
