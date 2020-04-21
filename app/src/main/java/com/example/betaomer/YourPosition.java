package com.example.betaomer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.betaomer.model.Ingrediant;
import com.example.betaomer.model.Station;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refEventt;

public class YourPosition extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Ingrediant> ingrediantArrayList;

    EditText eTfood;
    Button btnAddfood;
    String newfood, status;
    ListView lv2;
    ArrayList<String> sr = new ArrayList<>();
    ;
    ArrayAdapter<String> adpp;
    private String _event_date;
    private int _station_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_position);


        this.ingrediantArrayList = new ArrayList<Ingrediant>();

        btnAddfood = (Button) findViewById(R.id.btnAddfood);
        eTfood = (EditText) findViewById(R.id.eTfood);
        lv2 = findViewById(R.id.lvE);

        Intent dt = getIntent();

        _event_date = dt.getStringExtra("event_date");
        _station_position = dt.getIntExtra("station_position", 0);

        Query query = refEventt.child(_event_date).child("ars").child(String.valueOf(_station_position)).child("_ingrediats"); // שאילתה של קבלת תמונה מהפייר בייס לפי רשימת העמדות והעמדה שנלחץ עליה, הצגת המצרכים שלה. במידה ואין מצרכים  והאירוע חדש לא יוצג כלום
        query.addListenerForSingleValueEvent(VEL);

        lv2.setOnItemClickListener(YourPosition.this);
        lv2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    com.google.firebase.database.ValueEventListener VEL = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dS) {
            if (dS.exists()) {

                for (DataSnapshot data : dS.getChildren()) {
                    Ingrediant ingrediant = data.getValue(Ingrediant.class);
                    ingrediantArrayList.add(ingrediant);
                    sr.add(ingrediant.toString());

                }
                adpp = new ArrayAdapter<String>(YourPosition.this, R.layout.support_simple_spinner_dropdown_item, sr);
                lv2.setAdapter(adpp);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };


    private void func(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = (View) getLayoutInflater().inflate(R.layout.dialog_status, null);
        RadioGroup group = (RadioGroup) dialogView.findViewById(R.id.ds1);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int choiceRaw = checkedId % 4;
                switch (choiceRaw) {
                    case 1:
                        status = "FULL";
                        break;
                    case 2:
                        status = "MED";
                        break;
                    case 3:
                        status = "LOW";
                        break;
                    case 0:
                        status = "EMPTY";
                        break;
                    default:
                        break;
                }


            }
        });

        builder.setView(dialogView);
        builder.setTitle("Edit Ingrediant");
        builder.setMessage("Enter new status");
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Ingrediant ingrediant = new Ingrediant(ingrediantArrayList.get(position).get_name());
                ingrediant.set_status(status);

                ingrediantArrayList.set(position, ingrediant);
                sr.set(position, ingrediant.toString());


                adpp.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    } // בעת לחיצה על המצרך, נפתח דיאלוג לשינוי המצב, המצב משתנה אך ורק בהצגת הרשימה, כדי לשנות בפייר בייס יש ללחוץ על שליחה למטבח

    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (true)
            func(position);
        else {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(this);
            edittext.setText(this.ingrediantArrayList.get(position).get_status());

            adb.setMessage("Enter new status to " + this.ingrediantArrayList.get(position).get_name());
            adb.setTitle("Edit Ingrediant");
            adb.setView(edittext);

            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String status = edittext.getText().toString(); // FULL -> EMPTY
                            Ingrediant ingrediant = new Ingrediant(ingrediantArrayList.get(position).get_name());
                            ingrediant.set_status(status);

                            ingrediantArrayList.set(position, ingrediant);
                            sr.set(position, ingrediant.toString());

                            adpp.notifyDataSetChanged();
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

    } //לחיצה על מצרך

    public void AddFood(View view) {
        if (!TextUtils.isEmpty(eTfood.getText().toString())) {
            newfood = eTfood.getText().toString();
            Ingrediant ingrediant = new Ingrediant(newfood);
            this.ingrediantArrayList.add(ingrediant);
            sr.add(ingrediant.toString());
            adpp = new ArrayAdapter<String>(YourPosition.this, R.layout.support_simple_spinner_dropdown_item, sr);
            lv2.setAdapter(adpp);
            eTfood.setText("");
        } else {

            Toast.makeText(YourPosition.this, "You have to fill the new food", Toast.LENGTH_LONG).show();
        }
    } //כאשר רוצים להוסיף מצרך מזינים באדיט טקסט ולוחצים add

        public void Finish (View view){

            try {
                final Context context = this;
                final DatabaseReference dr1 = refEventt.child(_event_date)
                        .child("ars")
                        .child(String.valueOf(_station_position));

                Query query = dr1;
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Station station2 = dataSnapshot.getValue(Station.class);
                            station2.set_array(ingrediantArrayList);
                            dr1.setValue(station2);
                            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        } // שליחה למטבח את נתוני הרשימה המעודכנת

        public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.menu.main, menu);

            return true;

        }  //יצירת תפריט

        public boolean onOptionsItemSelected (MenuItem item){

            String str = item.getTitle().toString();

            if (str.equals("Credits")) {

                Intent t = new Intent(this, Credits.class);
                startActivity(t);
            }

            if (str.equals("History Events")) {

                Intent t = new Intent(this, HistoryEvents.class);
                startActivity(t);
            }

            return true;
        }  //העברת אקטיביטי לתפריט שנלחץ עליו

    }


