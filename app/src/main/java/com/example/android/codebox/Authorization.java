package com.example.android.codebox;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class Authorization extends AppCompatActivity {


    TextInputEditText pp1, pp2, pp3, pp4;
    int _pin;
    String s;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        pp1 = (TextInputEditText) findViewById(R.id.p1);
        pp2 = (TextInputEditText) findViewById(R.id.p2);
        pp3 = (TextInputEditText) findViewById(R.id.p3);
        pp4 = (TextInputEditText) findViewById(R.id.p4);

        pp1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = pp1.getText().length();

                if (textlength1 >= 1) {
                    pp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        pp2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = pp2.getText().length();

                if (textlength1 >= 1) {
                    pp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });
        pp3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                Integer textlength1 = pp1.getText().length();

                if (textlength1 >= 1) {
                    pp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });

        pp4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence ss, int start, int before,
                                      int count) {
                Integer textlength1 = pp4.getText().length();

                if (textlength1 >= 1) {

                    s = pp1.getText().toString() + pp2.getText().toString()
                            + pp3.getText().toString() +pp4.getText().toString()+" ";

                    _pin = Integer.parseInt(s.trim());

                    if(_pin == 1234){

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else {
                        //refresh all the edittext views
                        pp1.setText("");
                        pp2.setText("");
                        pp3.setText("");
                        pp4.setText("");
                        Toast.makeText(getApplicationContext(), "Wrong Pin! Try Again", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void setPin(View view){
        Intent i = new Intent(getApplicationContext(), SetAuthorization.class);
        startActivity(i);

    }
}


