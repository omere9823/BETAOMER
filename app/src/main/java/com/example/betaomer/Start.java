package com.example.betaomer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar pb;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent t = new Intent(Start.this,MainActivity.class);
                startActivity(t);
                finish();
            }
        },SPLASH_TIME_OUT);


        prog();
    }


   public void prog() {

        pb = (ProgressBar) findViewById(R.id.pb);

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {

                counter++;
                pb.setProgress(counter);

                if (counter==100)
                    t.cancel();

            }
        };
        t.schedule(tt,0,30);
    }
    /**
     * Charging line operation according to the initial screen's birth.
     */
}
