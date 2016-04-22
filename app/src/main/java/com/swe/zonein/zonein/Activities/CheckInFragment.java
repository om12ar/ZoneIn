package com.swe.zonein.zonein.Activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by om12ar on 4/21/16.
 */
public class CheckInFragment extends  android.app.Fragment {
    public static AddPlaceFragment newInstance() {
        AddPlaceFragment fragment = new AddPlaceFragment();
        Bundle args = new Bundle();
        return fragment;


    }

    EditText desc ;
    RatingBar rate ;
    Button checkIn;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_checkin, container, false);

        desc = (EditText) v.findViewById(R.id.checkinReview);
        rate = (RatingBar) v.findViewById(R.id.checkinRate);

        checkIn = (Button) v.findViewById(R.id.checkinSubmitBtn);

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descString = desc.getText().toString();
               float pDesc = rate.getRating();

                final CheckIn newCheckin = new CheckIn();

                final String url = VolleyController.baseURL + "checkIn";


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsnObject = new JSONObject(response);
                             if(jsnObject!=null){

                                    Toast.makeText(getActivity(), "Checked In!", Toast.LENGTH_LONG).show();

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
                          params.put("userID", "" + newCheckin.getUserID());

                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request);

                Toast.makeText(getActivity(), descString,
                        Toast.LENGTH_LONG).show();

            }
        });


        return v;
    }
}
