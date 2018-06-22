package com.starklabs.tutorialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ChapterActivity extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        tvData = (TextView)findViewById(R.id.tvData);
        Intent intent = getIntent();
        int i = intent.getIntExtra("i",0);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "c1.txt");
        map.put(1, "c2.txt");
        map.put(2, "c3.txt");
        map.put(3, "c4.txt");

        String fn = map.get(i);
        tvData.setText(fn);

        StringBuffer sb = new StringBuffer();
        String line;

        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open(fn));
            BufferedReader br = new BufferedReader(isr);

            while((line = br.readLine()) != null){
                sb.append(line+ "\n");
            }
            tvData.setText(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
