package com.example.betaomer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Choice extends AppCompatActivity {
    String code = "12345";

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
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setMessage("enter the code");
        adb.setTitle("manager access");
        adb.setView(edittext);


        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String codereceived = edittext.getText().toString();
                        if (code.equals(codereceived)){
                            Intent t=new Intent(Choice.this,Loginok.class);
                            startActivity(t);
                        } else {
                            Toast.makeText(Choice.this, "Your code is incorrect", Toast.LENGTH_LONG).show();
                        }
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
}
