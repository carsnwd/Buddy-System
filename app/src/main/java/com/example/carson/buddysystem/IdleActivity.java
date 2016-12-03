package com.example.carson.buddysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for when waiting to hear
 * back from the user when to come back. It
 * will cancel the not reported back text if so.
 */
public class IdleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle);

        /**
         Button listener to cancel the text.
         **/
        final Button reportBackBtn = (Button) findViewById(R.id.reportBackButton);
        reportBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
