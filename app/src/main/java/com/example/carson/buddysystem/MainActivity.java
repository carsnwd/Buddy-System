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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Mainactivity of our app
 */
public class MainActivity extends AppCompatActivity {

    static final int DIALOG_ID = 0;
    private int minute_x;
    private int hour_x;
    private SMS alertObject;
    private String timeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        Button listener to get data from view
         **/
        final Button submitBtn = (Button) findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phoneNumber = (EditText)findViewById(R.id.buddyNumber);
                EditText location = (EditText)findViewById(R.id.location);
                alertObject = new SMS();
                alertObject.setPhoneNumber(phoneNumber.getText().toString());
                alertObject.setLocation(location.getText().toString());
                alertObject.setTimeToBeBack(timeString);
                //onSendTextMessage();
                Intent idleIntent = new Intent(MainActivity.this, IdleActivity.class);
                idleIntent.putExtra("SMSObject", alertObject);
                MainActivity.this.startActivity(idleIntent);
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
                    final Button timeBtn = (Button) findViewById(R.id.timeButton);
                    if(minute < 10){
                        timeString = hour_x + ":" + "0" + minute_x;
                        timeBtn.setText(timeString);
                    }else{
                        timeString = hour_x + ":" + minute_x;
                        timeBtn.setText(timeString);
                    }
                 //Toast.makeText(MainActivity.this, hour_x + ":" + minute_x, Toast.LENGTH_LONG).show();
                }
            };
}
