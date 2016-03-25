package com.swe.zonein.zonein.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    boolean loggedIn = false;
    //TextView statusET ;
    private static String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_in);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameET = (EditText) findViewById(R.id.nameET);
                final EditText passET = (EditText) findViewById(R.id.passET);
                //statusET = (TextView) findViewById(R.id.statusET);
                Log.e(TAG, "INLOGIN");
                loginTask task = new loginTask();
                task.execute("login" , nameET.getText().toString(), passET.getText().toString());
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

    public class loginTask extends AsyncTask<String ,Void , JSONObject> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONObject doInBackground(String... args) {

            HttpURLConnection urlConnection = null ;
            URL url ;

            try {

                HashMap<String, String> params = new HashMap<>();
                String URL =args[0];
                params.put("email", args[1]);
                params.put("pass", args[2]);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            if(jsonObject!=null){
                String s = "hi";
                    Log.e(TAG, s);


                MainControlller.user = new User(jsonObject);

                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    System.err.print("GOT USER FROM DB");
                    Log.e("LOGIN ACTIVITY", MainControlller.user.getName());
                    //statusET.setText(MainControlller.user.getName());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password!", Toast.LENGTH_LONG).show();
                   // statusET.setText("Wrong Email or Password ");
                }
            }
        }
}


