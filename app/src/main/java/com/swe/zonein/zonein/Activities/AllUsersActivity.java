package com.swe.zonein.zonein.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity {



    ListView checkInsList;
    List<User> users = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_users);

        checkInsList = (ListView) findViewById(R.id.users_list);

        final Context myContext = this;

        final String url = VolleyController.baseURL + "getAllUsers";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try {

                        JSONObject jsnObject = new JSONObject(response);
                        JSONArray jsonArray = jsnObject.getJSONArray("userList");
                        if(jsonArray!=null){

                            for (int i=0 ;i < jsonArray.length() ;i++){
                                try {
                                    User tempUser = new User(jsonArray.getJSONObject(i));
                                    users.add(tempUser);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            UserAdapter useradapter = new UserAdapter(users, myContext);
                            checkInsList.setAdapter(useradapter);

                        } else {

                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        e.getMessage();
                        System.out.println("ERROR Exception!");
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR!");
            }
        }){
            @Override
            protected HashMap<String, String> getParams()
            {
                HashMap<String, String> params = new HashMap<String, String>();
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);


    }

}
