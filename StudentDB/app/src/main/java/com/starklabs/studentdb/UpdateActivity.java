package com.starklabs.studentdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText etUpdateRno, etUpdateName;
    Button btnUpdateSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etUpdateRno = (EditText)findViewById(R.id.etUpdateRno);
        etUpdateName = (EditText)findViewById(R.id.etUpdateName);
        btnUpdateSave = (Button)findViewById(R.id.btnUpdateSave);

        btnUpdateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rno = etUpdateRno.getText().toString();
                String name = etUpdateName.getText().toString();
                MainActivity.db.updateStudent(Integer.parseInt(rno), name);
                etUpdateName.setText("");
                etUpdateRno.setText("");
                etUpdateRno.requestFocus();
            }
        });

    }
}
