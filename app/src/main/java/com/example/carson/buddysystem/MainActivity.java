package com.example.carson.buddysystem;

import android.Manifest;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String phoneNumber; //information about where the user is going
    private String location;
    private String timeToBeBack;
    static final int DIALOG_ID = 0;
    private int minute_x;
    private int hour_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Button listener to get data from view
         */
        final Button submitBtn = (Button) findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phoneNumber = (EditText)findViewById(R.id.buddyNumber);
                EditText location = (EditText)findViewById(R.id.location);
                setPhoneNumber(phoneNumber.getText().toString());
                setLocation(location.getText().toString());
                System.out.println(getLocation() + " " + getPhoneNumber() + " " + getTimeToBeBack());
                //onSendTextMessage();
                Intent myIntent = new Intent(MainActivity.this, IdleActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        /**
         * Opens the time picker dialog
         */
        final Button timeBtn = (Button) findViewById(R.id.timeButton);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });
    }

    /**
     * Sets up timePicker dialog
     * @param id = our time picker dialog id
     * @return
     */
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID) {
            return new TimePickerDialog(MainActivity.this, kTimePickerListener, hour_x, minute_x,
                    false);
        }

        return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                    hour_x = hourOfDay;
                    minute_x = minute;
                    if(minute < 10){
                        setTimeToBeBack(hour_x + ":" + "0" + minute_x);
                    }else{
                        setTimeToBeBack(hour_x + ":" + minute_x);
                    }
                 //Toast.makeText(MainActivity.this, hour_x + ":" + minute_x, Toast.LENGTH_LONG).show();
                }
            };

    /**
     * Send a text message
     * Used when a button is pressed for now
     */
    protected void onSendTextMessage(){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
            PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, SMS.class), 0);
            SmsManager sms = SmsManager.getDefault();
            String message = "I will be at: " + getLocation() +".\n" +   //Message to send
                    "I will be back at: " + getTimeToBeBack() + ".\n";
            sms.sendTextMessage(getPhoneNumber(), null, message, pi, null);

            //Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            //sendIntent.putExtra("sms_body", message);
            //sendIntent.setType("vnd.android-dir/mms-sms");
            //startActivity(sendIntent);
    }

    //Getter and setter for phoneNumber
    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String s){this.phoneNumber = s;}

    //Getter and setter for location
    public String getLocation() {return location;}
    public void setLocation(String s) {this.location = s;}

    //Getter and setter for timeToBeBack
    public String getTimeToBeBack() {return timeToBeBack;}
    public void setTimeToBeBack(String s) {this.timeToBeBack = s;}

}
