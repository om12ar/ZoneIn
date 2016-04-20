package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

/**
 * Created by om12ar on 4/21/16.
 */
public class CheckInFragment extends android.support.v4.app.Fragment {
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

                CheckIn newCheckin = new CheckIn();

                //TODO add to DB

                Toast.makeText(getActivity(), descString,
                        Toast.LENGTH_LONG).show();

            }
        });


        return v;
    }
}
