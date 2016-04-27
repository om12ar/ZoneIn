package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.PlaceAdapter;
import com.swe.zonein.zonein.Controllers.MainController;
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
 * Created by om12ar on 4/21/16.
 */
public class SavedPlacesFragment extends android.app.Fragment {
    List<Place> places;
    ListView list;
    PlaceAdapter adapter;

    public static SavedPlacesFragment newInstance() {
        SavedPlacesFragment fragment = new SavedPlacesFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        list = (ListView) v.findViewById(R.id.list_view);
        places = new ArrayList<>() ;
        adapter = new PlaceAdapter(places ,getActivity());
        adapter.setButtons(false);
        list.setAdapter(adapter);

        final String url = VolleyController.baseURL + "getsavedplaces";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    System.out.print("UserSavedPlaces : " + jsnObject.toString());
                    JSONArray jsonArray = jsnObject.getJSONArray("SavedPlacesAre");
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
                        Log.e("AF", places.toString());
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                System.out.println("ERROR!");
            }
        }) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("userID", MainController.user.getID() + "");
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);
        Log.e("AFff", places.size() + "");
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("allplaces :" ,"ON CLICK ");
                Bundle bundle = new Bundle();
                TextView name = (TextView) view.findViewById(R.id.placeItemNameTV);
                bundle.putString("placeName", name.getText().toString());

                PlaceFragment nextFrag= new PlaceFragment();
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();

            }
        });


        return v;
    }


}
