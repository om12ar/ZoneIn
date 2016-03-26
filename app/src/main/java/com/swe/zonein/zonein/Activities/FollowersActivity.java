package com.swe.zonein.zonein.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

/**
 * Created by Noha on 3/25/2016.
 */
public class FollowersActivity extends Activity {
    final String TAG = "FollowersActivity";
    ArrayList<String> followers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ListView followersListView = (ListView)findViewById(R.id.followersListView);


        //elmfrood a get el followers names list mn el data base hna w a3mlhom add fel arraylist b for loop
        getFollowersTask GFtask = new getFollowersTask();
        JSONArray followersJson = null;
        try {
            followersJson =  GFtask.execute("getFollowers" , ""+MainControlller.user.getID()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        followers.add("Number of Followers : "+followersJson.length());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, followers);

        followersListView.setAdapter(arrayAdapter);

        followersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // elmfrood hna nroo7 le e profile bta3 el follower elly hyb2a clicked on
                Toast.makeText(getApplicationContext(), "this is item number: " + followers.get(position), Toast.LENGTH_LONG).show();

            }
        });

    }

    public class getFollowersTask extends AsyncTask<String ,Void , JSONArray> {
        JSONParser jsonParser = new JSONParser();
//getFollowers
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
                        followers.add(jsonArray.getJSONObject(i).getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                followers.add("No followers");
            }
        }
    }
}
