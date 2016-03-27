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
import android.widget.Toast;


import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class SignInActivity extends AppCompatActivity {


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
                try {
                    task.execute("login" , nameET.getText().toString(), passET.getText().toString()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        Button singup = (Button) findViewById(R.id.signUpbutton);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("BUUUT");
               // Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
              //  startActivity(intent);


            }
        });
        Button forgotPass = (Button) findViewById(R.id.forgotPasswordbutton);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Intent intent = new Intent(SignInActivity.this, RestorePasswordActivity.class);
             //   startActivity(intent);

            }
        });



    }

    public class loginTask extends AsyncTask<String ,Void , JSONObject> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONObject doInBackground(String... args) {
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

                MainControlller.user = new User(jsonObject);
                Log.e(TAG , "USER NAME " + MainControlller.user.getName());

                getFollowersTask getfollowerstask = new getFollowersTask();
                getfollowerstask.execute("getFollowers" , ""+MainControlller.user.getID());

                getFollowingTask getfollowedbytask= new getFollowingTask();
                getfollowedbytask.execute("getFollowedBy" , ""+MainControlller.user.getID());

                Intent intent = new Intent(SignInActivity.this, MainActivity.class);

                    Log.e("LOGIN ACTIVITY", MainControlller.user.getName());
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password!", Toast.LENGTH_LONG).show();

                }
            }
        }


    public class getFollowersTask extends AsyncTask<String ,Void , JSONArray> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... args) {


            try {

                HashMap<String, String> params = new HashMap<>();
                String URL =args[0];
                params.put("userID", args[1]);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        URL, "POST", params);

                if (json != null) {
                    JSONArray result = json.getJSONArray("followersList");

                    Log.d("JSON result", json.toString());

                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            if(jsonArray!=null){
                for (int i=0 ;i < jsonArray.length() ;i++){
                    try {
                        int temp_id = jsonArray.getJSONObject(i).getInt("id");
                        MainControlller.user.Addfollower(temp_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
            }
        }
    }

    public class getFollowingTask extends AsyncTask<String ,Void , JSONArray> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... args) {


            try {

                HashMap<String, String> params = new HashMap<>();
                String URL =args[0];
                params.put("userID", args[1]);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        URL, "POST", params);

                if (json != null) {
                    JSONArray result = json.getJSONArray("followedByUser");

                Log.d("JSON followedByUser", json.toString());

                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            if(jsonArray!=null){
                for (int i=0 ;i < jsonArray.length() ;i++){
                    System.err.print("IN followedByUser");
                    try {
                        int temp_id = jsonArray.getJSONObject(i).getInt("id");
                        MainControlller.user.follow(temp_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
            }
        }
    }


}


