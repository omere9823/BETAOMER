package com.example.betaomer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refAuth;
import static com.example.betaomer.FBref.refEventt;

public class Loginok extends AppCompatActivity {
    private ArrayList<String> arrayList;


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginok);

        listView = (ListView)findViewById(R.id.liolv1);


        arrayList = new ArrayList<>();

        Query query =  refEventt.orderByChild("date").startAt(getCurrentDate());
        /**
         * Query a photo of the events by today's date and beyond to view a list of events.
         */
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    arrayList.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Eventt eventt = ds.getValue(Eventt.class);
                        arrayList.add(eventt.getDate());
                    }
                    fillAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String dateToday;

        if (month<10) {
            if (day<10) {
                dateToday="" + year + "_0" + (month+1) + "_0" + day;
            } else {
                dateToday="" + year + "_0" + (month+1) + "_" + day;
            }
        } else if (day<10) {
            dateToday="" + year + "_" + (month+1) + "_0" + day;
        } else {
            dateToday="" + year + "_" + (month+1) + "_" + day;
        }
        return dateToday;
    }
    /**
     * Getting today's date for the sort at Fair Base.
     */


    private void fillAdapter(){
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayList);
        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toPositionStatusActivity = new Intent(Loginok.this,PositionsStatus.class);
                toPositionStatusActivity.putExtra("title",arrayList.get(position));
                startActivity(toPositionStatusActivity);
            }
        });

    }
    /**
     * View the list of events, and when you click a transfer event to a screen with the status table, sends a given date of the event.
     */


    public void AddEvent(View view) {
        Intent t=new Intent(Loginok.this,Createevent.class);
        startActivity(t);
    }
    /**
     * Transfer to event creation screen.
     */


    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    }
    /**
     * Create a menu.
     */


    public boolean onOptionsItemSelected(MenuItem item){

        String str = item.getTitle().toString();

        if (str.equals("Credits")) {

            Intent t = new Intent(this,Credits.class);
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
    }
    /**
     * Move activity to the clicked menu.
     */

}
