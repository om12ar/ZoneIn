package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.swe.zonein.zonein.R;

public class RespondToNotificationFragment extends  android.app.Fragment {
    EditText desc;
    RatingBar rate;
    Button checkIn;

    public static CheckInFragment newInstance() {
        CheckInFragment fragment = new CheckInFragment();
        Bundle args = new Bundle();
        return fragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.checkin_item, container, false);
        final int placeID = getArguments().getInt("placeID");
        String PlaceName = getArguments().getString("placeName");





        return v;
    }
}
