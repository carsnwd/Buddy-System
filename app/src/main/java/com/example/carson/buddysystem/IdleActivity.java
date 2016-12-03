package com.example.carson.buddysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Activity for when waiting to hear
 * back from the user when to come back. It
 * will cancel the not reported back text if so.
 */
public class IdleActivity extends AppCompatActivity {
    public Timer _t; //timer for the task


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);

        _t = new Timer();
        _t.schedule(new WaitTask(), 10000); //in ms? //add real time in here when ready

        /**
         Button listener to cancel the text.
         **/
        final Button reportBackBtn = (Button) findViewById(R.id.reportBackButton);
        reportBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _t.cancel(); //cancel the task since the user clicked the button
            }
        });
    }

    class WaitTask extends TimerTask{

        public void run (){//runs if the user does not respond to the button
            //ask if done here
            _t.cancel(); //end the task
        }
    }
}

