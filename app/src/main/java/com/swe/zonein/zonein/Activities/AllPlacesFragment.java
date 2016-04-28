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
import com.swe.zonein.zonein.Adapters.PlaceAdapter;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/20/16.
 */
public class AllPlacesFragment extends android.app.Fragment {
    List<Place> places;
    ListView listview;
    PlaceAdapter adapter ;
    public static AllPlacesFragment newInstance() {
        AllPlacesFragment fragment = new AllPlacesFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        listview = (ListView) v.findViewById(R.id.list_view);
        places = new ArrayList<>() ;
        adapter = new PlaceAdapter(places ,getActivity());

        listview.setAdapter(adapter);

        final String url = VolleyController.baseURL + "getAllPlaces";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("placeList");
                    Log.e("AF", jsnObject.toString());
                    if (jsonArray != null) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                Place tempPlace = new Place(jsonArray.getJSONObject(i));
                                places.add(tempPlace);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Log.e("AFff", places.size() + "");

                        adapter.notifyDataSetChanged();


                        adapter.notifyDataSetChanged();

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        });

                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                    System.out.println("ERROR Exception!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                System.out.println("ERROR!");
            }
        }){
            @Override
            protected HashMap<String, String> getParams()
            {
                HashMap<String, String> params = new HashMap<String, String>();
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);
        Log.e("AFff", places.size() + "");
        adapter.notifyDataSetChanged();

        Log.e("AF", places.toString());

        return v;
    }

}
