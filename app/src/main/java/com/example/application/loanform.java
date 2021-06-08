package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loanform extends AppCompatActivity {
    private EditText e1, e2, e3, e4, e5, e6, e7, e8, e9, e10;
    private Button next;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loanform);

        e1 = (EditText) findViewById(R.id.loan_purpose);
        e2 = (EditText) findViewById(R.id.inc_details);
        e3 = (EditText) findViewById(R.id.land_details);
        e4 = (EditText) findViewById(R.id.area);
        e5 = (EditText) findViewById(R.id.fulladdress);
        e6 = (EditText) findViewById(R.id.district);
        e7 = (EditText) findViewById(R.id.village);
        e8 = (EditText) findViewById(R.id.pin);
        e9 = (EditText) findViewById(R.id.accnt);
        e10 = (EditText) findViewById(R.id.loan_amt);
        next = (Button) findViewById(R.id.next);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("loanforms");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final String loanpurpose = e1.getText().toString();
                    final String income = e2.getText().toString();
                    final String land = e3.getText().toString();
                    final String areadetails = e4.getText().toString();
                    final String addr = e5.getText().toString();
                    final String dist = e6.getText().toString();
                    final String villge = e7.getText().toString();
                    final String pincode = e8.getText().toString();
                    final String account = e9.getText().toString();
                    final String loanamt = e10.getText().toString();

                if (!TextUtils.isEmpty((loanpurpose)) && !TextUtils.isEmpty((income)) && !TextUtils.isEmpty((land)) && !TextUtils.isEmpty((areadetails)) && !TextUtils.isEmpty((addr)) && !TextUtils.isEmpty((dist)) && !TextUtils.isEmpty((villge)) && !TextUtils.isEmpty((pincode)) && !TextUtils.isEmpty((account)) && !TextUtils.isEmpty((loanamt))) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(loanform.this);
                    builder.setMessage("Do you want to Proceed ??");
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

                    lfdatabase lfdatabase = new lfdatabase();

                            lfdatabase.setLoanp(loanpurpose);
                            lfdatabase.setInc(income);
                            lfdatabase.setLd(land);
                            lfdatabase.setAr(areadetails);
                            lfdatabase.setAdd(addr);
                            lfdatabase.setDistr(dist);
                            lfdatabase.setVill(villge);
                            lfdatabase.setPinc(pincode);
                            lfdatabase.setAccnt(account);
                            lfdatabase.setLamt(loanamt);
                            databaseReference.push().setValue(lfdatabase);

                            Toast.makeText(loanform.this,"success",Toast.LENGTH_SHORT).show();
                            Intent r = new Intent(loanform.this, uploadingdocs.class);
                            startActivity(r);
                            finish();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Enter all the details" , Toast.LENGTH_SHORT).show();
                }
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
