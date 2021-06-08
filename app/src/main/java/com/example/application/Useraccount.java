package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Useraccount extends AppCompatActivity {

    private EditText e1,e2,e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        e1=(EditText)findViewById(R.id.accno);
        e2=(EditText)findViewById(R.id.custname);
        e3=(EditText)findViewById(R.id.bcity);
        e4=(EditText)findViewById(R.id.branch);


    }
}
