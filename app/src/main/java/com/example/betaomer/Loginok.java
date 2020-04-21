package com.example.betaomer;

import android.content.Intent;
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

        Query query =  refEventt.orderByChild("date").startAt(getCurrentDate()); //שאילתה של קבלת תמונה של האירועים לפי התאריך של היום והלאה, כדי להציג ברשימת אירועים
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
    } //קבלת התאריך של היום בשביל המיון בפייר בייס

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

    } // הצגת הרשימה של האירועים, ובעת לחיצה על אירוע העברה למסך עם טבלה של מצב העמדות, שולח נתון של תאריך האירוע

    public void AddEvent(View view) {
        Intent t=new Intent(Loginok.this,Createevent.class);
        startActivity(t);
    } // העברה למסך יצירת אירוע

    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    } //יצירת תפריט

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

        return true;
    } //העברת אקטיביטי לתפריט שנלחץ עליו
}
