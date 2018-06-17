package com.starklabs.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNumber;
    Button btnSquare, btnSquareRoot, btnCube, btnEO;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = (EditText)findViewById(R.id.etNumber);
        btnSquare = (Button)findViewById(R.id.btnSquare);
        btnSquareRoot = (Button)findViewById(R.id.btnSquareRoot);
        btnCube = (Button)findViewById(R.id.btnCube);
        btnEO = (Button)findViewById(R.id.btnEO);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String s = etNumber.getText().toString();
                    double n = Double.parseDouble(s);
                    double r = n * n;
                    Toast.makeText(MainActivity.this, "Square: " + r, Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Enter a number : ", Toast.LENGTH_SHORT).show();
                }
                //tvResult.setText(r);

            }
        });

        btnSquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String s = etNumber.getText().toString();
                    double n = Double.parseDouble(s);
                    double r = Math.sqrt(n);
                    Toast.makeText(MainActivity.this, "Square Root: " + r, Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Enter a number : ", Toast.LENGTH_SHORT).show();
                }
                //tvResult.setText(r);

            }
        });

        btnCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String s = etNumber.getText().toString();
                    double n = Double.parseDouble(s);
                    double r = n*n*n;
                    Toast.makeText(MainActivity.this, "Cube: " + r, Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Enter a number : ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnEO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double r = 0;
                try {
                    String s = etNumber.getText().toString();
                    int n = Integer.parseInt(s);
                    if (n%2 == 0)
                        tvResult.setText("Number is even");
                    else
                        tvResult.setText("Number is odd");
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Enter a number : ", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(MainActivity.this, "Cube: " + r, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
