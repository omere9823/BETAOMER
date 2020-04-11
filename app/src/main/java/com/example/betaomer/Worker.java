package com.example.betaomer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.renderscript.Sampler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betaomer.model.Station;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.betaomer.FBref.refEventt;

public class Worker extends AppCompatActivity implements AdapterView.OnItemClickListener {


    String  str1, str2, chose, dateToday;
    TextView tvdate;
    ListView lv;
    Calendar c;
    ArrayList<String> als = new ArrayList<String>();
    ArrayList<Eventt> aleventt = new ArrayList<Eventt>();
    ArrayAdapter<String> adp;
    int alls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);


        tvdate = (TextView) findViewById(R.id.tvdate);
        lv = findViewById(R.id.lv);

        lv.setOnItemClickListener(Worker.this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

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


        tvdate.setText(dateToday);

        Query query =  refEventt.orderByChild("date").startAt(dateToday).limitToFirst(1);
        query.addListenerForSingleValueEvent(VEL);

    }


    com.google.firebase.database.ValueEventListener VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dS) {
            if (dS.exists()) {
                als.clear();
                aleventt.clear();

                List<String> strings  = new ArrayList<>();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();

                    Eventt eventt = data.getValue(Eventt.class);
                    ArrayList<Station> arrayList = eventt.ars;

                    strings = new ArrayList<>(arrayList.size());
                    for (Station station : arrayList) {
                        strings.add(station != null ? station.toString() : null);
                    }

                }
                adp = new ArrayAdapter<String>(Worker.this, R.layout.support_simple_spinner_dropdown_item, strings);
                lv.setAdapter(adp);
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };

    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        alls = position;
        final Intent t=new Intent(this,YourPosition.class);
        //t.putExtra("emda",alls);

        Query query3 = refEventt.orderByChild("date").startAt(dateToday).limitToFirst(1);
        query3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String date = "";

                    for (DataSnapshot sd : dataSnapshot.getChildren()){
                        Eventt event = sd.getValue(Eventt.class);
                        date = event.getDate();
                        break;
                    }
                    //DatabaseReference dr1 = refEventt.orderByChild("date").startAt(dateToday).limitToFirst(1).getRef();
                    //DatabaseReference dr2 = dr1.limitToFirst(1).getRef();
                    //DatabaseReference dr3 = dr2.child(date);
                    //DatabaseReference dr4 = dr3.child("ars"); // []

                    t.putExtra("event_date", date);
                    t.putExtra("station_position",position);  // 0,1

                    startActivity(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //startActivity(t);

    }


    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

}

/*public static void btnRed(View view){
    //ars.set(position,Emdot.get(position)+"Empty")
}*/
