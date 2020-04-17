package com.example.betaomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refEventt;

public class Loginok extends AppCompatActivity {
    private ArrayList<String> arrayList;


    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginok);

        listView = (ListView)findViewById(R.id.liolv1);
        progressBar = (ProgressBar)findViewById(R.id.aliopb1);
        progressBar.setProgress(1);

        arrayList = new ArrayList<>();

        Query query =  refEventt.orderByChild("date").startAt(getCurrentDate());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
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
        this.progressBar.setProgress(0);
        this.progressBar.setVisibility(View.GONE);
    }

    public void AddEvent(View view) {
        Intent t=new Intent(Loginok.this,Createevent.class);
        startActivity(t);
    }

    public void LiveEvent(View view) {
        Intent t=new Intent(Loginok.this, PositionsStatus.class);
        t.putExtra("title","2020_08_13");
        startActivity(t);
    }

    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){

        String str = item.getTitle().toString();

        if (str.equals("Credits")) {

            Intent t = new Intent(this,Credits.class);
            startActivity(t);
        }

        return true;
    }
}
