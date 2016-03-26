package com.swe.zonein.zonein.Activities;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swe.zonein.zonein.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AllUsersFragment extends ListFragment {

    public AllUsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_users, container, false);
    }
}
