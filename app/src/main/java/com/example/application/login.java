package com.example.application;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private Button b1,b2;
    private EditText e1,e2;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.pwd);
        b1 = (Button) findViewById(R.id.login);
        b2 = (Button) findViewById(R.id.reg);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r = new Intent(login.this, register.class);
                startActivity(r);
                return;
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = e1.getText().toString();
                String password = e2.getText().toString();

                if (!validateEmail() | !validatePassword()) {
                    return;
                }

                progressDialog.setMessage("logging you in...");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.cancel();
                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                                        if (username.equals("koredhanraj431@gmail.com")) {
                                            Toast.makeText(getApplicationContext(), "Admin Logged in successfully", Toast.LENGTH_SHORT).show();
                                            Intent r = new Intent(login.this, adminactivity.class);
                                            startActivity(r);
                                            finish();
                                        }

                                       else{
                                            Toast.makeText(getApplicationContext(), "User Logged in successfully", Toast.LENGTH_SHORT).show();
                                            Intent r = new Intent(login.this, usermenu.class);
                                            startActivity(r);
                                            finish();
                                       }

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Please Verify your email Address", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "email or password is incorrect", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                        });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.about:
                Intent i = new Intent(this,aboutus.class);
                startActivity(i);
                break;
            case R.id.share:
                Toast.makeText(this,"coming soon ",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
        }
    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
        builder.setMessage("Do you want to exit ??");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
             finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean validateEmail() {
        String emailInput = e1.getText().toString().trim();

        if (emailInput.isEmpty()) {
            e1.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            e1.setError("Please enter a valid email address");
            return false;
        } else {
            e1.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = e2.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            e2.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            e2.setError("Password too weak");
            return false;
        } else {
            e2.setError(null);
            return true;
        }
    }
}

