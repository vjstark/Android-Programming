package com.starklabs.firebaseauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText etMaEmail,etMaPassword;
    Button btnSignIn,btnSignUp,btnForgotPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMaEmail = (EditText)findViewById(R.id.etMaEmail);
        etMaPassword = (EditText)findViewById(R.id.etMaPassword);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnForgotPassword = (Button)findViewById(R.id.btnForgotPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = etMaEmail.getText().toString().trim();
                String p = etMaPassword.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, ApplicationActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this, "Incorrect Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
