package com.example.carson.buddysystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String phoneNumber; //information about where the user is going
    private String location;
    private String timeToBeBack;

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
                EditText timeToBeBack = (EditText)findViewById(R.id.timeBack);
                setPhoneNumber(phoneNumber.getText().toString());
                setLocation(location.getText().toString());
                setTimeToBeBack(timeToBeBack.getText().toString());

                //System.out.println(getLocation() + " " + getPhoneNumber() + " " + getTimeToBeBack());
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
