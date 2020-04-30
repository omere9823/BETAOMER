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
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refAuth;
import static com.example.betaomer.FBref.refEventt;

public class HistoryEvents extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ListView listView;
    SearchView searchView;
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_events);

        listView = (ListView)findViewById(R.id.liolv1);
        arrayList = new ArrayList<>();
        searchView = (SearchView) findViewById(R.id.searchView);



        Query query =  refEventt.orderByChild("date"); //הבאת תמונה של כל האירועים לפי תאריכים
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    arrayList.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Eventt eventt = ds.getValue(Eventt.class);
                        arrayList.add(eventt.getDate());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,arrayList);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //פעולת חיפוש ברשימה לפי תאריכים
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // פעולת לחיצה על תאריך ברשימה והעברה למסך עם רשימת העמדות והעובדים של אותו אירוע
                Intent toPositionStatusActivity = new Intent(HistoryEvents.this,Selectedevent.class);
                toPositionStatusActivity.putExtra("title",arrayList.get(position));
                startActivity(toPositionStatusActivity);
            }
        });

    }



    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    }   //יצירת תפריט

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
    } //העברת אקטיביטי לתפריט שנלחץ עליו
}
