package com.starklabs.evenodd;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etNumber;
    Button btnFind;
    TextView tvResult;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int o = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(o);

        etNumber = (EditText)findViewById(R.id.etNumber);
        btnFind = (Button)findViewById(R.id.btnFind);
        tvResult = (TextView)findViewById(R.id.tvResult);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etNumber.getText().toString();
                if (s.length() == 0){
                    Toast.makeText(MainActivity.this,"number is empty",Toast.LENGTH_SHORT).show();
                    etNumber.requestFocus();
                    return;
                }

                int num = Integer.parseInt(s);
                String msg = "";
                if (num % 2 == 0){
                    msg = "Number is even";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    tvResult.setText(msg);
                    tts.speak(msg, TextToSpeech.QUEUE_FLUSH,null);
                }
                else{
                    msg = "Number is odd";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    tvResult.setText(msg);
                    tts.speak(msg, TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog a = builder.create();
        a.setTitle("Confirm Exit !");
        a.show();
    }
}
