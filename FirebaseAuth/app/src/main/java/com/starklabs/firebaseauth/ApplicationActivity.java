package com.starklabs.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ApplicationActivity extends AppCompatActivity {

    TextView tvInfo;
    Button btnLogout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        tvInfo = (TextView)findViewById(R.id.tvInfo);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent i  = new Intent(ApplicationActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
