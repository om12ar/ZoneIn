package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.swe.zonein.zonein.Adapters.UserAdapter;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by om12ar on 4/20/16.
 */
public class AddPlaceFragment extends android.support.v4.app.Fragment{

    public static AddPlaceFragment newInstance() {
        AddPlaceFragment fragment = new AddPlaceFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    EditText placeName ;
    EditText placeDescription ;
    CheckBox isCurrentLocation;

    Button addPlace;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.add_place, container, false);

        placeName = (EditText) v.findViewById(R.id.placeNameET);
        placeDescription = (EditText) v.findViewById(R.id.placeDescriptionET);
        isCurrentLocation = (CheckBox) v.findViewById(R.id.isCurrentLocation);
        addPlace = (Button) v.findViewById(R.id.addplaceBTN);

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = placeName.getText().toString();
                String pDesc = placeDescription.getText().toString();
                String lng ,lat ;
                if (isCurrentLocation.isChecked()){
                    //TODO maybe update location first
                    lng= MainControlller.user.getLng();
                    lat= MainControlller.user.getLat();
                }
                else{
                    EditText userLong = (EditText) v.findViewById(R.id.placelongET);
                    EditText userLat = (EditText) v.findViewById(R.id.placelatET);
                    lng = userLong.getText().toString();
                    lat = userLat.getText().toString();
                }
                Place newPlace = new Place(pName,pDesc,lng,lat);

                //TODO add to DB

                Toast.makeText(getActivity(), pName,
                        Toast.LENGTH_LONG).show();

            }
        });


        return v;
    }
}
