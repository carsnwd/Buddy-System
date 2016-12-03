package com.example.carson.buddysystem;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jr8699 on 12/3/16.
 */

public class SMS extends Activity {
    protected void sendText(String number, String location, String time){
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, SMS.class), 0);
        SmsManager sms = SmsManager.getDefault();
        String message = "I will be at: " + location +".\n" +   //Message to send
                            "I will be back at: " + time ".\n";
        sms.sendTextMessage(number, null, message, pi, null);
    }
}
