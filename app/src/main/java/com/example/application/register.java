package com.example.application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class register  extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private Button b1;
    private EditText e1, e2, e3;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.pwd);
        e3 = (EditText) findViewById(R.id.cpwd);

        b1 = (Button) findViewById(R.id.reg);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = e1.getText().toString();
                String password = e2.getText().toString();
                String cpassword = e3.getText().toString();

                if (!validateEmail() | !validatePassword() | !validateCPassword()) {
                    return;
                }

                if (!password.equals(cpassword)) {
                    Toast.makeText(getApplicationContext(), "Password and Confirm password should be same", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Registering user...");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(username,password)
                        .addOnCompleteListener(register.this,   new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    firebaseAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getApplicationContext(), "User registered Successfully, please verify your email ", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(register.this, login.class);
                                                        startActivity(i);
                                                        finish();
                                                    }
                                                    else{
                                                        Toast.makeText(getApplicationContext(), "Failed to register, Please try again", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }

                                if (!task.isSuccessful()) {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException weakPassword) {
                                        Toast.makeText(getApplicationContext(), "Weak Password", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthUserCollisionException existEmail) {
                                        Toast.makeText(getApplicationContext(), "Email exists", Toast.LENGTH_SHORT).show();
                                    } catch (UnknownHostException nointernet) {
                                        Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_LONG).show();
                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                                    }

                                    progressDialog.cancel();
                                    return;

                                }
                            }
                        });
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
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

    private boolean validateCPassword() {
        String passwordInput = e3.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            e3.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            e3.setError("Confirm Password too weak");
            return false;
        } else {
            e3.setError(null);
            return true;
        }
    }

}

