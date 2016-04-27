package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.CheckinAdapter;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by om12ar on 4/21/16.
 */
public class HomePageFragment extends android.app.Fragment {

    ArrayList<CheckIn> homePosts;
    ListView list;
    CheckinAdapter adapter;

 //   String url;
  //  ArrayList<Integer> usersID = new ArrayList<>();
 //   int userID;

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.content_main, container, false);
        homePosts = new ArrayList<>();
        adapter = new CheckinAdapter(homePosts,getActivity());


        list = (ListView) v.findViewById(R.id.homePageList);
        list.setAdapter(adapter);

        //homePosts.add(new CheckIn("bla", "111", 0, 1, 2));
        // homePosts.add(new CheckIn("bla bla ", "111", 2.5, 1, 2));
        //homePosts.add(new CheckIn("bla bla bla", "111", 5, 1, 2));
        //homePosts.add(new CheckIn("bla bla bla bla", "111", 5, 1, 2));



     final String url = VolleyController.baseURL + "getCheckinsByUser";

          final StringRequest  request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsnObject = new JSONObject(response);
                        JSONArray jsonArray = jsnObject.getJSONArray("placeList");
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    CheckIn tempCheckin = new CheckIn(jsonArray.getJSONObject(i));
                                    homePosts.add(tempCheckin);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                            Log.e("AFff", homePosts.size() + "");

                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "getting Check-Ins failed!", Toast.LENGTH_LONG).show();
                        }


                    } catch (JSONException e) {
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
            }) {
                @Override
                protected HashMap<String, String> getParams() {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("userID", "" + MainController.user.getID());
                    Log.e("Place Fragment ", url + " " + params.toString());
                    return params;
                }

            };


            VolleyController.getInstance().addToRequestQueue(request);

        Button sortByNearby = (Button) v.findViewById(R.id.sortByNearbyButton);
        sortByNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        Button sortByNumberOfCkeckIns = (Button) v.findViewById(R.id.sortByNumberOfCkeckInsButton);
        sortByNumberOfCkeckIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        Button sortByRatings = (Button) v.findViewById(R.id.sortByRatingsButton);
        sortByRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        return v;
    }

    /*public void buttonTapped(View view){
        int id = view.getId();

        Log.e("buttonTapped: ", id +"");
    }*/
}
