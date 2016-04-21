package com.swe.zonein.zonein.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {


    final String TAG = "Signup Activity";
    final String url = VolleyController.baseURL + "signup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj != null) {
                                Log.e(TAG,obj.toString());
                                Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Email or Password!", Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Please Try again", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected HashMap<String, String> getParams() {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("name", nameET.getText().toString());
                        params.put("email", emailET.getText().toString());
                        params.put("pass", passET.getText().toString());

                        return params;
                    }

                };

                VolleyController.getInstance().addToRequestQueue(request, TAG);
            }
        });


    }

}

