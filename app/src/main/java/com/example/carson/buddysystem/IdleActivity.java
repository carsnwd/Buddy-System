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
import java.math.BigInteger;
import java.util.Calendar;

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
        int taskTime = stringToMS(alertObject.getTimeToBeBack());
        System.out.println("IODEBUG " + taskTime + "  " + alertObject.getTimeToBeBack());
        _t.schedule(new WaitTask(), taskTime); //in ms? //add real time in here when ready
        //~11/12 days would be the maximum with this or an overflow will occur

        /**
         Button listener to cancel the text.
         **/
        final Button reportBackBtn = (Button) findViewById(R.id.reportBackButton);
        reportBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _t.cancel(); //cancel the task since the user clicked the button
                IdleActivity.super.onBackPressed();
            }
        });
    }

    class WaitTask extends TimerTask{

        public void run (){//runs if the user does not respond to the button
            onSendTextMessage();
            //could have a better message
            IdleActivity.super.onBackPressed();
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

    /**
     * Convert a time string into milliseconds
     * @param s
     * @return
     */
    protected int stringToMS(String s){
        int i = 0;
        String sub = "";
        int hours = -1;
        int minutes = -1;

        //current time
        Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);

        while(i<s.length()){
            if(s.charAt(i) != ':'){ //check to see if we want to split
                sub = sub + s.charAt(i);
            }else{
                if(hours == -1) { //parse the hours
                    hours = Integer.parseInt(sub);
                    sub = ""; //reset the substring
                }
                else //parse the minutes
                    minutes = Integer.parseInt(sub);
            }
            i++;
        }

        //only able to go out 24hours with the current implementation
        if(hours < currentHour) //error handling, set to next day if hour is before
                hours = hours + 24;

        //convert it all into milliseconds
        int end = 0;
        if(hours > 0) end = 1000*60*60*hours;
        if(minutes > 0) end = end + 1000*60*minutes;

        int current = 0;
        if(currentHour > 0) current = 1000*60*60*hours;
        if(currentMinute > 0) current = current + 1000*60*minutes;

        return Math.abs(current - end);
    }
}

