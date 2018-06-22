package com.starklabs.studentdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etAddRno, etAddName;
    Button btnAddSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etAddRno = (EditText)findViewById(R.id.etAddRno);
        etAddName = (EditText)findViewById(R.id.etAddName);
        btnAddSave = (Button)findViewById(R.id.btnAddSave);

        btnAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno = etAddRno.getText().toString();
                String name = etAddName.getText().toString();
                MainActivity.db.addStudent(Integer.parseInt(rno),name);
                etAddRno.setText("");
                etAddName.setText("");
                etAddRno.requestFocus();
            }
        });
    }
}
