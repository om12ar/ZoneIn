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

import com.swe.zonein.zonein.Controllers.DBController;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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
                loginTask task = new loginTask();
                String s = task.execute("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a").toString();
                Log.e(TAG, s);
                int id = db.login(nameET.getText().toString(), passET.getText().toString());
                if (id != -1) {
                    Log.e(TAG, s);
                    statusET.setText(s);
                    MainController.user = new User(db.getUser(id));
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    System.err.print("GOT USER FROM DB");
                    Log.e("LOGIN ACTIVITY", MainController.user.getName());
                    //startActivity(intent);
                    //finish();
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
    public class loginTask extends AsyncTask<String ,Void , String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            HttpURLConnection urlConnection = null ;
            URL url ;
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection = url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream() ;
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();
                while(data!=-1){
                    char current = (char) data;
                    result += current ;
                    data = inputStreamReader.read();
                }

                Log.e(TAG, result);
                return result;

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
