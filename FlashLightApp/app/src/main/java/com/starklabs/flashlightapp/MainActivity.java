package com.starklabs.flashlightapp;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    Camera c;
    Camera.Parameters p;
    boolean isFlashOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart =(Button)findViewById(R.id.btnStart);
        btnStop=(Button)findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(! isFlashOn){
                    p = c.getParameters();
                    p.setFlashMode(p.FLASH_MODE_TORCH);
                    c.setParameters(p);
                    c.startPreview();
                    isFlashOn = true;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isFlashOn){
                    p =c.getParameters();
                    p.setFlashMode(p.FLASH_MODE_OFF);
                    c.setParameters(p);
                    c.stopPreview();
                    isFlashOn = false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        c = Camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        c.release();
        c = null;
        isFlashOn = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.about){
            Toast.makeText(this, "app by starklabsv", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.contact){
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:"+"9920215800"));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
