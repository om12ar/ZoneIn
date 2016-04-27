package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by om12ar on 4/20/16.
 */
public class AddPlaceFragment extends  android.app.Fragment {

    final String TAG = "Add place Fragment";
    EditText placeName ;
    EditText placeDescription ;
    CheckBox isCurrentLocation;
    Button addPlace;

    public static AddPlaceFragment newInstance() {
        AddPlaceFragment fragment = new AddPlaceFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.add_place, container, false);

        placeName = (EditText) v.findViewById(R.id.placeNameET);
        placeDescription = (EditText) v.findViewById(R.id.placeDescriptionET);
        isCurrentLocation = (CheckBox) v.findViewById(R.id.isCurrentLocation);
        addPlace = (Button) v.findViewById(R.id.addplaceBTN);

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String pName = placeName.getText().toString();
                final String pDesc = placeDescription.getText().toString();
                String lng ,lat ;
                if (isCurrentLocation.isChecked()){
                    //TODO maybe update location first
                    lng= MainController.user.getLng();
                    lat= MainController.user.getLat();
                }
                else{
                    EditText userLong = (EditText) v.findViewById(R.id.placelongET);
                    EditText userLat = (EditText) v.findViewById(R.id.placelatET);
                    lng = userLong.getText().toString();
                    lat = userLat.getText().toString();
                }
                Place newPlace = new Place(pName,pDesc,lng,lat);
                final String finalLong = lng ;
                final String finalLat = lat ;

                final String url = VolleyController.baseURL + "addplace";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj!=null){


                                Toast.makeText(getActivity(), "Place added", Toast.LENGTH_LONG).show();
                                Log.e(TAG,obj.toString());
                                getActivity().getFragmentManager().popBackStack();


                            } else {
                                Toast.makeText(getActivity(), "Creation failed ",
                                        Toast.LENGTH_LONG).show();


                            }
                        }catch (JSONException e){
                            e.printStackTrace();
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
                        params.put("name", pName);
                        params.put("description",pDesc);;
                        params.put("lon" , finalLong);
                        params.put("lat" , finalLat);
                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request, TAG);



            }
        });


        return v;
    }
}
