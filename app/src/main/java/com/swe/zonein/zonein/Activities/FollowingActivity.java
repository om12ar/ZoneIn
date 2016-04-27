package com.swe.zonein.zonein.Activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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

        final Context myContext = this;

        final String url = VolleyController.baseURL + "getFollowedBy";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("followedByUser");
                    if(jsonArray!=null){
                        Log.e(TAG ,  jsonArray.toString());

                        for (int i=0 ;i < jsonArray.length() ;i++){
                            try {
                                User tempUser = new User(jsonArray.getJSONObject(i));
                                followers.add(tempUser);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter = new followersAdapter();
                        setListAdapter(adapter);

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
                params.put("userID", "" + MainController.user.getID());
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request, TAG);

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
                convertView = getLayoutInflater().inflate(R.layout.user_item, parent, false);


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


                final String url = VolleyController.baseURL + "unfollow";


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsnObject = new JSONObject(response);
                            JSONArray jsonArray = jsnObject.getJSONArray("status");
                            if(jsonArray!=null){


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
                        params.put("FollowerID", "" + MainController.user.getID());
                        params.put("FollowedID", ""+ followers.get(position).getID());
                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request, TAG);

                if(position>followers.size()){
                    MainController.user.unfollow(followers.get(position).getID());
                }
                followers.remove(position);
                adapter.notifyDataSetChanged();

            }

        }
    };


}

