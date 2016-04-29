package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    final String TAG = "Home page adapter";
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
        adapter = new CheckinAdapter(homePosts, getActivity());
        homePosts.clear();

        list = (ListView) v.findViewById(R.id.homePageList);
        list.setAdapter(adapter);

        final String url = VolleyController.baseURL + "getCheckinsByUser";
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("placeList");
                    Log.i(TAG, jsnObject.toString());
                    if (jsonArray != null) {
                        Log.i("CheckinsByUser", jsnObject.toString());
                        homePosts.clear();
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


        final String url2 = VolleyController.baseURL + "getHomePage";

        final StringRequest request2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("checkins");
                    if (jsonArray != null) {
                        homePosts.clear();
                        Log.i("getHomePage", jsnObject.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                CheckIn tempCheckin = new CheckIn(jsonArray.getJSONObject(i));
                                homePosts.add(tempCheckin);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        VolleyController.getInstance().addToRequestQueue(request);
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


        VolleyController.getInstance().addToRequestQueue(request2);


        Button sortByNearby = (Button) v.findViewById(R.id.sortByNearbyButton);
        sortByNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String url3 = VolleyController.baseURL + "sortHomePageByDistance";

                final StringRequest request3 = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsnObject = new JSONObject(response);
                            JSONArray jsonArray = jsnObject.getJSONArray("checkins");
                            if (jsonArray != null) {
                                homePosts.clear();
                                Log.i("sortHomePageByDistance", jsnObject.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        CheckIn tempCheckin = new CheckIn(jsonArray.getJSONObject(i));
                                        homePosts.add(tempCheckin);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                VolleyController.getInstance().addToRequestQueue(request);
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
                VolleyController.getInstance().addToRequestQueue(request3);
                adapter.notifyDataSetChanged();

            }

        });

        Button sortByNumberOfCkeckIns = (Button) v.findViewById(R.id.sortByNumberOfCkeckInsButton);
        sortByNumberOfCkeckIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url4 = VolleyController.baseURL + "sortHomePageByCheckins";

                final StringRequest request4 = new StringRequest(Request.Method.POST, url4, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsnObject = new JSONObject(response);
                            JSONArray jsonArray = jsnObject.getJSONArray("checkins");
                            if (jsonArray != null) {
                                homePosts.clear();
                                Log.i("sortHomePageByCheckins", jsnObject.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        CheckIn tempCheckin = new CheckIn(jsonArray.getJSONObject(i));
                                        homePosts.add(tempCheckin);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                VolleyController.getInstance().addToRequestQueue(request);
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
                VolleyController.getInstance().addToRequestQueue(request4);
                adapter.notifyDataSetChanged();

            }
        });

        Button sortByRatings = (Button) v.findViewById(R.id.sortByRatingsButton);
        sortByRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url5 = VolleyController.baseURL + "sortHomePageByRating";

                final StringRequest request5 = new StringRequest(Request.Method.POST, url5, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsnObject = new JSONObject(response);
                            JSONArray jsonArray = jsnObject.getJSONArray("checkins");
                            if (jsonArray != null) {
                                homePosts.clear();
                                Log.i("sortHomePageByRating", jsnObject.toString());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        CheckIn tempCheckin = new CheckIn(jsonArray.getJSONObject(i));
                                        homePosts.add(tempCheckin);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                VolleyController.getInstance().addToRequestQueue(request);
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
                VolleyController.getInstance().addToRequestQueue(request5);
                adapter.notifyDataSetChanged();

            }
        });


        return v;
    }

}
