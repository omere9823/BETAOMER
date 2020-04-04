package com.example.betaomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Loginok extends AppCompatActivity {

    Intent t;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginok);
    }


    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public void AddEvent(View view) {
        t=new Intent(Loginok.this,Createevent.class);
        startActivity(t);
    }



}
