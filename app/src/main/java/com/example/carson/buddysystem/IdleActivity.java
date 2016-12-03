package com.example.carson.buddysystem;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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

    private SMS alertObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        alertObject = (SMS)i.getSerializableExtra("SMSObject");
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
                onSendTextMessage();
                //_t.cancel(); //cancel the task since the user clicked the button
            }
        });
    }

    class WaitTask extends TimerTask{

        public void run (){//runs if the user does not respond to the button
            //ask if done here
            _t.cancel(); //end the task
        }
    }

    /**
     * Send a text message
     * Used when a button is pressed for now
     */
    protected void onSendTextMessage(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, SMS.class), 0);
        SmsManager sms = SmsManager.getDefault();
        String message = "I will be at: " + alertObject.getLocation() +".\n" +   //Message to send
                "I will be back at: " + alertObject.getTimeToBeBack() + ".\n";
        sms.sendTextMessage(alertObject.getPhoneNumber(), null, message, pi, null);

        //Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        //sendIntent.putExtra("sms_body", message);
        //sendIntent.setType("vnd.android-dir/mms-sms");
        //startActivity(sendIntent);
    }
}

