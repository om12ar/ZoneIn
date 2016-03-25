package com.swe.zonein.zonein.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.swe.zonein.zonein.Controllers.DBController;
import com.swe.zonein.zonein.R;

public class SignUpActivity extends AppCompatActivity {


    DBController db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            db = new DBController(this);
        }
        catch (Exception e){
            Toast.makeText(SignUpActivity.this, "ERROR1 " + e.toString(), Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_sign_up);
        createUser();

    }
    public void createUser(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameET = (EditText) findViewById(R.id.nameET);
                final EditText passET = (EditText) findViewById(R.id.passET);
                final EditText emailET = (EditText) findViewById(R.id.emailET);

                boolean isInserted = db.addUser(nameET.getText().toString(), emailET.getText().toString(), passET.getText().toString());
                Log.i("SIGNUP", "" + isInserted);
                if (isInserted) {
                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignUpActivity.this, "This email is already registered", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
