package com.swe.zonein.zonein.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swe.zonein.zonein.R;

/**
 * Created by Noha on 3/26/2016.
 */
public class PlacePostFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_post_fragment, container, false);

        return view;
    }
}
