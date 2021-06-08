package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {

            if (firebaseUser.getUid().equals("tXHOCdAAlUZxSmbBqAHoOWuIV1V2")) {

                Intent i = new Intent(MainActivity.this, adminactivity.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(MainActivity.this, usermenu.class);
                startActivity(i);
                finish();
            }
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    Intent i = new Intent(MainActivity.this, login.class);
                    startActivity(i);
                    finish();

                }
            }, 3000);
        }
    }
}



