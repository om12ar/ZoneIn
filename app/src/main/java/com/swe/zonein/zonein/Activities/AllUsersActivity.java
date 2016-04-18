package com.swe.zonein.zonein.Activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AllUsersActivity extends AppCompatActivity {



    ListView checkInsList;
    List<User> users = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_users);

        checkInsList = (ListView) findViewById(R.id.users_list);


        try {
            new getUsersTask().execute("getAllUsers").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        UserAdapter useradapter = new UserAdapter(users,this);
        checkInsList.setAdapter(useradapter);

    }

    public class getUsersTask extends AsyncTask<String ,Void , JSONArray> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... args) {


            try {

                HashMap<String, String> params = new HashMap<>();
                String URL =args[0];
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        URL, "POST", params);

                if (json != null) {
                    JSONArray result = json.getJSONArray("userList");

                    Log.d("JSON userList", json.toString());

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
                        User tempUser = new User(jsonArray.getJSONObject(i));
                        users.add(tempUser);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
            }
        }
    }

}
