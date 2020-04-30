package com.example.betaomer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.betaomer.FBref.refAuth;

public class Choice extends AppCompatActivity {
    String code = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }



    public void ImW(View view) {
        Intent t=new Intent(Choice.this, Worker.class);
        startActivity(t);
    }  //פעולה שברגע שלוחצים על העפתור worker נכנסים לאזור עובד


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
                            Intent t=new Intent(Choice.this, Loginok.class);
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

    } // פעולה שברגע לחיצה על הmanager נפתח דיאלוג לסיסמא וכאשר הסיסמא נכונה מועבר לאזור אחמש

    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    } //יצירת תפריט

    public boolean onOptionsItemSelected(MenuItem item){

        String str = item.getTitle().toString();

        if (str.equals("Credits")) {

            Intent t = new Intent(this, Credits.class);
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
    }  //העברת אקטיביטי לתפריט שנלחץ עליו
}
