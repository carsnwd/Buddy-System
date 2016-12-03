package com.example.carson.buddysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String phoneNumber; //information about where the user is going
    private String location;
    private String timeToBeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submitBtn = (Button) findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    /**
     * Send a text message
     * Used when a button is pressed for now
     */
    protected void onSendTextMessage(){


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
