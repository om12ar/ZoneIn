package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swe.zonein.zonein.R;

/**
 * Created by om12ar on 4/20/16.
 */
public class PlaceFragment extends android.support.v4.app.Fragment{
    static Bundle args ;
    public PlaceFragment newInstance() {
        PlaceFragment fragment = new PlaceFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_place, container, false);

        String name = getArguments().getString("placeName");
        TextView nameTv = (TextView) v.findViewById(R.id.placeItemNameTV);
        nameTv.setText(name);
        return v;
    }
}
