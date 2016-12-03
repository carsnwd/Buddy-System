package com.example.carson.buddysystem;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.Serializable;

/**
 * Created by jr8699 on 12/3/16.
 */

public class SMS implements Serializable{

    private String phoneNumber; //information about where the user is going
    private String location;
    private String timeToBeBack;

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
