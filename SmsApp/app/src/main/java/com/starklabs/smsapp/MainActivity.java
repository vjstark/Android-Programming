package com.starklabs.smsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etPhone,etMessage;
    Button btnSendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = (EditText)findViewById(R.id.etPhone);
        etMessage = (EditText)findViewById(R.id.etMessage);
        btnSendSms = (Button)findViewById(R.id.btnSendSms);

        int r = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (r == PackageManager.PERMISSION_GRANTED){

            btnSendSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String m = etMessage.getText().toString();
                    String p = etPhone.getText().toString();
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(p, null,m,null,null);
                    Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                }
            });
        }else{

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},789);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 789 & grantResults[0] == PackageManager.PERMISSION_GRANTED){

            btnSendSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String m = etMessage.getText().toString();
                    String p = etPhone.getText().toString();
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(p, null,m,null,null);
                    Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            finish();
        }
    }
}
