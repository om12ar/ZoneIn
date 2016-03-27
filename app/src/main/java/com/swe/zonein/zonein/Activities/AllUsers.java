package com.swe.zonein.zonein.Activities;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class AllUsers extends ListActivity {

    final String TAG = "AllUSersActivity";
    ArrayList<User> users = new ArrayList<>();
    private followersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        getUsersTask GFtask = new getUsersTask();

        try {
            GFtask.execute("getAllUsers").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adapter = new followersAdapter();
        setListAdapter(adapter);

    }

    private class followersAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public String getItem(int position) {
            return users.get(position).getName();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView holder;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.fragment_all_users, parent, false);

                holder = (TextView) convertView.findViewById(R.id.userNameTextView);

                convertView.findViewById(R.id.unfollowButton).setOnClickListener(buttonListener);

                convertView.setTag(holder);
            } else {
                holder = (TextView) convertView.getTag();
            }

            Log.e(TAG,users.get(position).getName());
            holder.setText(users.get(position).getName());


            return convertView;
        }
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = getListView().getPositionForView(v);
            if (position != ListView.INVALID_POSITION) {
                Log.e(TAG , position+"");
                followTask followtask = new followTask();

                try {
                   // if()
                    followtask.execute("follow",""+users.get(position).getID() , ""+MainControlller.user.getID()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                TextView tv = (TextView) v.findViewById(R.id.unfollowButton);
                tv.setText("Unfollow");
                //users.remove(position);
                adapter.notifyDataSetChanged();

            }

        }
    };

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

    public class followTask extends AsyncTask<String ,Void , JSONArray> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... args) {
            try {

                HashMap<String, String> params = new HashMap<>();
                String URL =args[0];
                params.put("followerID", args[1]);
                params.put("followedID", args[2]);

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

                //followers.remove();

            } else {
                //followers.add("No followers");
            }
        }
    }
}
