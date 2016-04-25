package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.swe.zonein.zonein.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by om12ar on 4/21/16.
 */
public class CheckInFragment extends  android.app.Fragment {
    EditText desc;
    RatingBar rate;
    Button checkIn;

    public static CheckInFragment newInstance() {
        CheckInFragment fragment = new CheckInFragment();
        Bundle args = new Bundle();
        return fragment;


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_checkin, container, false);
        final int placeID = getArguments().getInt("placeID");
        String PlaceName = getArguments().getString("placeName");
        desc = (EditText) v.findViewById(R.id.checkinReview);
        rate = (RatingBar) v.findViewById(R.id.checkinRate);

        checkIn = (Button) v.findViewById(R.id.checkinSubmitBtn);

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descString = desc.getText().toString();
                float pRate = rate.getRating();

                final CheckIn newCheckin = new CheckIn(MainController.user.getID(), placeID, descString, pRate);

                final String url = VolleyController.baseURL + "checkIn";

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
                        params.put("review", "" + newCheckin.getText());
                        params.put("userID", "" + newCheckin.getUserID());
                        Log.e("CheckinFragment", url + " " + params.toString());
                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request);


            }
        });


        return v;
    }
}
