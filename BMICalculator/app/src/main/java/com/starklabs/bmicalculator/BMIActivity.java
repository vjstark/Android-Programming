package com.starklabs.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    TextView tvInfoDisplay;
    TextView tvHeight;
    TextView tvFeet;
    Spinner spnFeet;
    TextView tvInches;
    Spinner spnInches;
    Button btnCalculate;
    Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        tvInfoDisplay = (TextView)findViewById(R.id.tvInfoDisplay);
        tvHeight = (TextView)findViewById(R.id.tvHeight);
        tvFeet = (TextView)findViewById(R.id.tvFeet);
        spnFeet = (Spinner)findViewById(R.id.spnFeet);
        tvInches = (TextView)findViewById(R.id.tvInches);
        spnInches = (Spinner)findViewById(R.id.spnInches);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnHistory = (Button)findViewById(R.id.btnHistory);
    }
}
