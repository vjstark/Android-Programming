package com.starklabs.rememberme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btnSave;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        btnSave = (Button)findViewById(R.id.btnSave);
        sp = getSharedPreferences("pe1",MODE_PRIVATE);

        String n = sp.getString("n", "");
        if(n.length() != 0){
            Intent i = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(i);
            finish();
        }
        else{
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String n = etName.getText().toString();
                    if(n.length() == 0){
                        etName.setError("name is empty");
                        etName.requestFocus();
                        return;
                    }

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("n",n);
                    editor.commit();
                    Intent i = new Intent(MainActivity.this,WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }
}
