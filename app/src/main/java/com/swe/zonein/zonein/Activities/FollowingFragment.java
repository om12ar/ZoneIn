package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.UserAdapter;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Noha on 4/27/2016.
 */
public class FollowingFragment extends  android.app.Fragment {

    final String TAG = " FollowingActivity";
    List<User> following;
    ListView followingListView;
    UserAdapter followingAdapter ;

    public static FollowingFragment newInstance() {
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();

        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        followingListView = (ListView) v.findViewById(R.id.list_view);
        following = new ArrayList<>() ;
        followingAdapter = new UserAdapter(following ,getActivity());

        followingListView.setAdapter(followingAdapter);



        final String url = VolleyController.baseURL + "getFollowedBy";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("followedByUser");
                    Log.i(TAG, jsonArray.toString());
                    if(jsonArray!=null){

                        for (int i=0 ;i < jsonArray.length() ;i++){
                            try {
                                User tempU = new User(jsonArray.getJSONObject(i));
                                following.add(tempU);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        Log.e("followingList size", following.size() + "");

                        followingAdapter.notifyDataSetChanged();


                        followingAdapter.notifyDataSetChanged();

                        followingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                params.put("userID", MainController.user.getID() + "");
                Log.i(TAG, url + " " + params.toString());
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);

        return v;
    }
}
