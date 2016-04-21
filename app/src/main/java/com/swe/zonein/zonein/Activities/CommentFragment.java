package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swe.zonein.zonein.R;

/**
 * Created by om12ar on 4/21/16.
 */
public class CommentFragment extends  android.app.Fragment  {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_comment, container, false);
        return  v;
    }
}
