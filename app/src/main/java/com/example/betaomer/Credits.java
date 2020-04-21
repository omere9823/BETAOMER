package com.example.betaomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    }  //יצירת תפריט

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
