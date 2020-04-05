package com.example.betaomer;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.betaomer.FBref.refEventt;

public class Worker extends AppCompatActivity {


    String dateOfevent, str1, str2;
    TextView tvdate;
    ListView lv;
    Calendar c;
    ArrayList<String> als = new ArrayList<String>();
    ArrayList<Eventt> aleventt = new ArrayList<Eventt>();


    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);


        tvdate = (TextView) findViewById(R.id.tvdate);
        lv = findViewById(R.id.lv);

        Calendar calendar = Calendar.getInstance();
        String dateToday = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
        tvdate.setText(dateToday);



        Query query =  refEventt.orderByChild("date2").startAt(dateToday).limitToFirst(1);
        query.addListenerForSingleValueEvent(VEL);

    }


    com.google.firebase.database.ValueEventListener VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dS) {
            if (dS.exists()) {
                als.clear();
                aleventt.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Eventt eventt = data.getValue(Eventt.class);
                    aleventt.add(eventt);
                    als = eventt.getArs();
                }
            }

            adp = new ArrayAdapter<String>(Worker.this, R.layout.support_simple_spinner_dropdown_item, als);
            lv.setAdapter(adp);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };

    //public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        //Intent t=new Intent(Worker.this,YourPosition.class);
        //startActivity(t);

    //}


    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

}
