package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userprofile extends AppCompatActivity {
    private EditText e1,e2,e3,e4,e5,e6,e7,e8;
    private TextView t1;
    private Button b1,b2;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        t1=(TextView)findViewById(R.id.useremailid);
        e1=(EditText)findViewById(R.id.fname);
        e2=(EditText)findViewById(R.id.faname);
        e3=(EditText)findViewById(R.id.mname);
        e4=(EditText)findViewById(R.id.dob);
        e5=(EditText)findViewById(R.id.city);
        e6=(EditText)findViewById(R.id.citycode);
        e7=(EditText)findViewById(R.id.address);
        e8=(EditText)findViewById(R.id.mobno);
        b1=(Button)findViewById(R.id.save);
        b2=(Button)findViewById(R.id.update);

        firebaseAuth =FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            String email = firebaseUser.getEmail();
            t1.setText(email);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("profile");

        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      final String emid = t1.getText().toString();
                                      final String fullname = e1.getText().toString();
                                      final String fatname = e2.getText().toString();
                                      final String motname = e3.getText().toString();
                                      final String datofbir = e4.getText().toString();
                                      final String cit = e5.getText().toString();
                                      final String pincc = e6.getText().toString();
                                      final String addrs = e7.getText().toString();
                                      final String mobno = e8.getText().toString();

                                      final AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);
                                      builder.setMessage("Cannot be changed later, Do you want to Save ??");
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

                                              if(!TextUtils.isEmpty(emid) && !TextUtils.isEmpty(fatname) && !TextUtils.isEmpty(motname) && !TextUtils.isEmpty(datofbir) && !TextUtils.isEmpty(cit) && !TextUtils.isEmpty(pincc) && !TextUtils.isEmpty(addrs) && !TextUtils.isEmpty(mobno) ){

                                                  profiledatabase profiledatabase = new profiledatabase();
                                                  profiledatabase.setEid(emid);
                                                  profiledatabase.setFullname(fullname);
                                                  profiledatabase.setFathername(fatname);
                                                  profiledatabase.setMothername(motname);
                                                  profiledatabase.setDateofbirth(datofbir);
                                                  profiledatabase.setCity(cit);
                                                  profiledatabase.setPincode(pincc);
                                                  profiledatabase.setAddres(addrs);
                                                  profiledatabase.setMobno(mobno);
                                                  databaseReference.push().setValue(profiledatabase);
                                                  Toast.makeText(userprofile.this,"Profile Saved successfully",Toast.LENGTH_SHORT).show();
                                                  e1.setFocusable(false);
                                                  e2.setFocusable(false);
                                                  e3.setFocusable(false);
                                                  e4.setFocusable(false);
                                                  e5.setFocusable(false);
                                                  e6.setFocusable(false);
                                                  e7.setFocusable(false);
                                                  e8.setFocusable(false);
                                              }
                                              else {
                                                  Toast.makeText(getApplicationContext(), "Please Enter all the details" , Toast.LENGTH_SHORT).show();
                                              }

                                          }

                                      });
                                      AlertDialog alertDialog = builder.create();
                                      alertDialog.show();
                                  }
                              });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Coming soon..." , Toast.LENGTH_SHORT).show();
                /*final String emid = t1.getText().toString();
                final String fullname = e1.getText().toString();
                final String fatname = e2.getText().toString();
                final String motname = e3.getText().toString();
                final String datofbir = e4.getText().toString();
                final String cit = e5.getText().toString();
                final String pincc = e6.getText().toString();
                final String addrs = e7.getText().toString();
                final String mobno = e8.getText().toString();

                final AlertDialog.Builder builder = new AlertDialog.Builder(userprofile.this);
                builder.setMessage("Do you want to Update ??");
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

                        if(!TextUtils.isEmpty(emid) && !TextUtils.isEmpty(fatname) && !TextUtils.isEmpty(motname) && !TextUtils.isEmpty(datofbir) && !TextUtils.isEmpty(cit) && !TextUtils.isEmpty(pincc) && !TextUtils.isEmpty(addrs) && !TextUtils.isEmpty(mobno) ) {

                            profiledatabase profiledatabase = new profiledatabase();
                            profiledatabase.setEid(emid);
                            profiledatabase.setFullname(fullname);
                            profiledatabase.setFathername(fatname);
                            profiledatabase.setMothername(motname);
                            profiledatabase.setDateofbirth(datofbir);
                            profiledatabase.setCity(cit);
                            profiledatabase.setPincode(pincc);
                            profiledatabase.setAddres(addrs);
                            profiledatabase.setMobno(mobno);
                            databaseReference.push().setValue(profiledatabase);

                        }

            }
        });
            AlertDialog alertDialog = builder.create();
                                      alertDialog.show();*/
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
}
