package com.swe.zonein.zonein.Activities;

import android.content.Context;
import android.view.LayoutInflater;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/18/16.
 */
public class UserAdapter extends BaseAdapter {

    List<User> list;
    Context context;

    public UserAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    UserAdapter(Context c) {
        context = c;
        list = new ArrayList<User>();
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.user_item, null);

        final Button p = (Button) convertView.findViewById(R.id.followButton);
        TextView u = (TextView) convertView.findViewById(R.id.userNameTextView);

        holder.username = u;
        holder.follow = p;
        holder.follow.setText("Follow");



        String username = list.get(position).getName();

        holder.username.setText(username);


        p.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int user = list.get(position).getID();
                boolean isFollower = true; //TODO GET FROM DB
                if (isFollower == true) {
                    p.setText("Unfollow");
                    p.refreshDrawableState();
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
                            params.put("FollowedID", ""+ list.get(position).getID());
                            return params;
                        }

                    };


                    VolleyController.getInstance().addToRequestQueue(request);

                } else {
                    p.setText("Follow");
                    p.refreshDrawableState();

                    final String url = VolleyController.baseURL + "follow";


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
                            params.put("FollowedID", ""+ list.get(position).getID());
                            return params;
                        }

                    };


                    VolleyController.getInstance().addToRequestQueue(request);
                }

            }
        });
        return convertView;
    }
}


class Holder {
    TextView username;
    Button follow;
}

