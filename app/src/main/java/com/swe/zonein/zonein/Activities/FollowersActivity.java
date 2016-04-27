package com.swe.zonein.zonein.Activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
 * Created by Noha on 3/25/2016.
 */
public class FollowersActivity extends ListActivity {
    final String TAG = "FollowersActivity";
    ArrayList<User> followers = new ArrayList<>();
    private followersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final Context myContext = this;

        final String url = VolleyController.baseURL + "getFollowers";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("followersList");
                    if(jsonArray!=null){

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
            Button btn ;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.user_item, parent, false);


                holder = (TextView) convertView.findViewById(R.id.userNameTextView);
                btn = (Button) convertView.findViewById(R.id.followButton);
                btn.setVisibility(View.INVISIBLE);
            //    convertView.findViewById(R.id.unfollowButton).setOnClickListener(mBuyButtonClickListener);

                convertView.setTag(holder);
            } else {
                holder = (TextView) convertView.getTag();
            }

            Log.e(TAG,followers.get(position).getName());
            holder.setText(followers.get(position).getName());

            return convertView;
        }
    }

}
