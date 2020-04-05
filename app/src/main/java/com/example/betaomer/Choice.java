package com.example.betaomer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }


    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    public void ImW(View view) {
        Intent t=new Intent(Choice.this,Worker.class);
        startActivity(t);
    }


    public void ImM(View view) {
        Intent t=new Intent(Choice.this,Loginok.class);
        startActivity(t);
    }
}
