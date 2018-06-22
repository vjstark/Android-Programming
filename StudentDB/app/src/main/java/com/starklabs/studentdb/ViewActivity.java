package com.starklabs.studentdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvData = (TextView)findViewById(R.id.tvData);
        String data = MainActivity.db.viewStudent();
        if(data.length() == 0)
            tvData.setText("no records to show");
        else
            tvData.setText(data);
    }
}
