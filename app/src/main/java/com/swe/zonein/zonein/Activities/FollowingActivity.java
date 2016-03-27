package com.swe.zonein.zonein.Activities;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by ois2h on 3/27/2016.
 */
public class FollowingActivity extends ListActivity{
    final String TAG = "FollowersActivity";
    ArrayList<User> followers = new ArrayList<>();
    private followersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        getFollowersTask GFtask = new getFollowersTask();
        try {
            GFtask.execute("getFollowedBy",""+ MainControlller.user.getID()).get();
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
            return followers.size();
        }

        @Override
        public String getItem(int position) {
            return followers.get(position).getName();
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

                convertView.findViewById(R.id.followButton).setOnClickListener(mBuyButtonClickListener);

                convertView.setTag(holder);
            } else {
                holder = (TextView) convertView.getTag();
            }

            Log.e(TAG, followers.get(position).getName());
            holder.setText(followers.get(position).getName());

            return convertView;
        }
    }

    private View.OnClickListener mBuyButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = getListView().getPositionForView(v);
            if (position != ListView.INVALID_POSITION) {
                Log.e(TAG , position+"");
                unfollowTask unfollowtask = new unfollowTask();

                try {
                    unfollowtask.execute("unfollow",""+MainControlller.user.getID() , ""+followers.get(position).getID() ).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(position>followers.size()){
                    MainControlller.user.unfollow(followers.get(position).getID());
                }
                followers.remove(position);
                adapter.notifyDataSetChanged();

            }

        }
    };

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
                    JSONArray result = json.getJSONArray("followedByUser");

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
                        User tempuser = new User(jsonArray.getJSONObject(i));
                        followers.add(tempuser);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
            }
        }
    }

    public class unfollowTask extends AsyncTask<String ,Void , JSONArray> {
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
                    JSONArray result = json.getJSONArray("status");

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

