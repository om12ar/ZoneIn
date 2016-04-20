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

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.swe.zonein.zonein.Adapters.UserAdapter;

import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllUsersFragment extends android.support.v4.app.Fragment{

    List<User> users;
    ListView usersListView;
    UserAdapter userAdapter ;


    public static AllUsersFragment newInstance() {
        AllUsersFragment fragment = new AllUsersFragment();
        Bundle args = new Bundle();

        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        usersListView = (ListView) v.findViewById(R.id.list_view);
        users = new ArrayList<>() ;
        userAdapter = new UserAdapter(users ,getActivity());

        usersListView.setAdapter(userAdapter);



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


                        Log.e("AFff", users.size()+"");

                        userAdapter.notifyDataSetChanged();


                        userAdapter.notifyDataSetChanged();
                        Log.e("AF", users.toString());
                        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        });

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
        
        return v;
    }



}
