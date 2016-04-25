package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.swe.zonein.zonein.Adapters.CheckinAdapter;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by om12ar on 4/20/16.
 */
public class PlaceFragment extends  android.app.Fragment {
    static Bundle args ;
    List<CheckIn> checkIns;
    ListView listview;
    CheckinAdapter adapter ;

    public PlaceFragment newInstance() {
        PlaceFragment fragment = new PlaceFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_place, container, false);

        final String name = getArguments().getString("placeName");
        final int placeID = getArguments().getInt("placeID");
        final float rating = getArguments().getFloat("rating");
        TextView nameTv = (TextView) v.findViewById(R.id.placeItemNameTV);
        RatingBar rateingbar = (RatingBar) v.findViewById(R.id.placeRatingBar);
        rateingbar.setRating(rating);
        nameTv.setText(name);

        listview = (ListView) v.findViewById(R.id.placeCheckinsList);
        checkIns = new ArrayList<>() ;
        adapter = new CheckinAdapter(checkIns ,getActivity());
        listview.setAdapter(adapter);


        checkIns.add(new CheckIn("bla", "111", 0, 1, 2));
        checkIns.add(new CheckIn("bla bla ", "111", 2.5, 1, 2));
        checkIns.add(new CheckIn("bla bla bla", "111", 5, 1, 2));
        // TODO VOLLEY

        Log.e("AFff", checkIns.size() + "");
        adapter.notifyDataSetChanged();

        Button checkin = (Button) v.findViewById(R.id.plcChechInBtn);
        checkin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("placeID", placeID);
                CheckInFragment nextFrag = new CheckInFragment();
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });



/*      TODO getPlaceCheckins
        final String url = VolleyController.baseURL + "getPlaceCheckins";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    if(jsnObject!=null){
                        Log.e("CheckinFragment", jsnObject.toString());
                        Toast.makeText(getActivity(), "Checked In!", Toast.LENGTH_LONG).show();
                        getActivity().getFragmentManager().popBackStack();

                    }
                    else{
                        Toast.makeText(getActivity(), "Check In failed!", Toast.LENGTH_LONG).show();
                    }


                }

                catch(JSONException e){
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
                params.put("placeID", "" + newCheckin.getPlaceID());
                Log.e("Place Fragment ", url + " " + params.toString());
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);*/

        return v;
    }
}

