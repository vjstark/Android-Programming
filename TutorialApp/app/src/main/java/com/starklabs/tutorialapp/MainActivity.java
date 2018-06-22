package com.starklabs.tutorialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvData = (ListView)findViewById(R.id.lvData);

        ArrayList<String> chap = new ArrayList<>();
        chap.add("c1 introduction");
        chap.add("c2 fundamentals");
        chap.add("c3 i/o");
        chap.add("c4 operators");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chap);

        lvData.setAdapter(adapter);

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, ChapterActivity.class);
                intent.putExtra("i",i);
                startActivity(intent);

            }
        });
    }
}
