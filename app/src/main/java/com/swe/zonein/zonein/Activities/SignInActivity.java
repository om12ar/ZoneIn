package com.swe.zonein.zonein.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.swe.zonein.zonein.Controllers.DBController;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

public class SignInActivity extends AppCompatActivity {

    private static String TAG = "LoginActivity";
    DBController db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBController(this);

        setContentView(R.layout.activity_sign_in);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameET = (EditText) findViewById(R.id.nameET);
                final EditText passET = (EditText) findViewById(R.id.passET);
                final TextView statusET = (TextView) findViewById(R.id.statusET);
                Log.e(TAG, "INLOGIN");
                int id = db.login(nameET.getText().toString(), passET.getText().toString());
                if (id != -1) {
                    Log.e(TAG, "INLOGIN");
                    statusET.setText("SUCCESS");
                    MainController.user = new User(db.getUser(id));
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    System.err.print("GOT USER FROM DB");
                    Log.e("LOGIN ACTIVITY", MainController.user.getName());
                    startActivity(intent);
                    finish();
                } else {
                    statusET.setText("Wrong Email or Password ");
                }
            }
        });
        Button singup = (Button) findViewById(R.id.signUpbutton);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("BUUUT");
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);


            }
        });
        Button forgotPass = (Button) findViewById(R.id.forgotPasswordbutton);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, RestorePasswordActivity.class);
                startActivity(intent);

            }
        });


    }

}
