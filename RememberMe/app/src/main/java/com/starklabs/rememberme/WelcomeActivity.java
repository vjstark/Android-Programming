package com.starklabs.rememberme;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvData;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        tvData = (TextView)findViewById(R.id.tvData);
        sp = getSharedPreferences("p1",MODE_PRIVATE);
        String n = sp.getString("n","");
        tvData.setText("Welcome"+n);
}
}
