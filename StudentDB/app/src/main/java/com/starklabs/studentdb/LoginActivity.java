package com.starklabs.studentdb;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText etUserName;
    EditText etPassword;
    Button btnLogin;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        sp = getSharedPreferences("logindb",MODE_PRIVATE);

        final String name = sp.getString("username","");
        final String  pass = sp.getString("password","");

        if(name.length() != 0){
            etUserName.setHint("Enter UserName");
            etPassword.setHint("Enter password");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
            }
        });
    }
}
