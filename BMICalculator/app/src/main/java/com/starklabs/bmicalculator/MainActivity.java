package com.starklabs.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // First Page: Personal Details
    TextView tvPersonalDetails;
    EditText etName;
    EditText etAge;
    EditText etPhoneNumber;
    RadioGroup rgGender;
    RadioButton rbMale;
    RadioButton rbFemale;
    Button btnRegister;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first Page: Personal Details
        tvPersonalDetails = (TextView)findViewById(R.id.tvPersonalDetails);
        etName = (EditText)findViewById(R.id.etName);
        etAge = (EditText)findViewById(R.id.etAge);
        etPhoneNumber = (EditText)findViewById(R.id.etPhoneNumber);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        sp = getSharedPreferences("n1",MODE_PRIVATE);

        rgGender = (RadioGroup)findViewById(R.id.rgGender);
        rbMale = (RadioButton)findViewById(R.id.rbMale);
        rbFemale = (RadioButton)findViewById(R.id.rbFemale);

        //first Page: Personal Details
        String n = sp.getString("n","");
        if(n.length() != 0){
            Intent i = new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(i);
            finish();
        }else{
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //validations
                    String n = etName.getText().toString();
                    String a = etAge.getText().toString();
                    String pn = etPhoneNumber.getText().toString();
                    if(n.length() == 0){
                        etName.setError("name is empty");
                        etName.requestFocus();
                        return;
                    }else if(a.length() == 0){
                        etAge.setError("age is empty");
                        etAge.requestFocus();
                        return;
                    }else if(pn.length() == 0){
                        etPhoneNumber.setError("phone number is required");
                        etPhoneNumber.requestFocus();
                        return;
                    }

                    //take radio button data
                    int id = rgGender.getCheckedRadioButtonId();
                    RadioButton rb = rgGender.findViewById(id);



                    //send name to Welcome "name"
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("n",n);
                    Intent i = new Intent(MainActivity.this,BMIActivity.class);
                    editor.commit();
                    startActivity(i);
                    finish();
                }


            });

//            btnSubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int id = rgAirline.getCheckedRadioButtonId();
//                    RadioButton rb = rgAirline.findViewById(id);
//                    String airline =  rb.getText().toString();
//
//                    String rating = String.valueOf(rabRating.getRating());
//
//                    String msg = "Airline" + airline + "\n" + "Rating" + rating;
//
//                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//                    Intent i = new Intent(MainActivity.this, SendActivity.class);
//                    i.putExtra("msg",msg);
//                    startActivity(i);
//                }
//            });
        }



    }
}
