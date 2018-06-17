package com.starklabs.weightreporter;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    Button btnShare;
    FloatingActionButton fabCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight =(EditText)findViewById(R.id.etWeight);
        btnShare = (Button)findViewById(R.id.btnShare);
        fabCall = (FloatingActionButton)findViewById(R.id.fabCall);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w  = etWeight.getText().toString();
                if(w.length() == 0){
                    Snackbar.make(view, "weight is empty", Snackbar.LENGTH_SHORT).show();
                    etWeight.requestFocus();
                    return;
                }

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,"my weight is"+w);
                startActivity(i);
            }
        });

        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(Intent.ACTION_DIAL);
                d.setData(Uri.parse("tel:"+"9930215800"));
                startActivity(d);
            }
        });
    } //end of onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.website){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://"+"www.google.com"));
            startActivity(i);
        }
        if (item.getItemId() == R.id.about){
            Toast.makeText(this, "App developed by starklabsv", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
