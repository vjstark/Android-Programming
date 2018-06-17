package com.starklabs.flightreview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {

    TextView tvMessage;
    Button btnSend, btnWhatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        tvMessage = (TextView)findViewById(R.id.tvMessage);
        btnSend = (Button)findViewById(R.id.btnSend);
        btnWhatsapp = (Button)findViewById(R.id.btnWhatsapp);

        Intent i = getIntent();
        final String msg = i.getStringExtra("msg");
        tvMessage.setText(msg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,msg);
                startActivity(i);
            }
        });

        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.setPackage("com.whatsapp");
                i.putExtra(Intent.EXTRA_TEXT,msg);
                try{
                    startActivity(i);
                }
                catch (Exception e){
                    Toast.makeText(SendActivity.this, "Please install whatsapp", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
