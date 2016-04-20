package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.swe.zonein.zonein.Adapters.CheckinAdapter;
import com.swe.zonein.zonein.Adapters.PlaceAdapter;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by om12ar on 4/20/16.
 */
public class PlaceFragment extends android.support.v4.app.Fragment{
    static Bundle args ;
    List<CheckIn> checkIns;
    ListView listview;
    CheckinAdapter adapter ;
    public PlaceFragment newInstance() {
        PlaceFragment fragment = new PlaceFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_place, container, false);

        String name = getArguments().getString("placeName");
        TextView nameTv = (TextView) v.findViewById(R.id.placeItemNameTV);
        nameTv.setText(name);

        listview = (ListView) v.findViewById(R.id.placeCheckinsList);
        checkIns = new ArrayList<>() ;
        adapter = new CheckinAdapter(checkIns ,getContext());
        listview.setAdapter(adapter);
        checkIns.add(new CheckIn("bla", "111", 0, 1, 2));
        checkIns.add(new  CheckIn("bla bla ","111", 2.5 , 1, 2));
        checkIns.add(new  CheckIn("bla bla bla","111", 5 , 1, 2));
        // TODO VOLLEY
       /* try {
            new getUsersTask().execute("getAllUsers").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        Log.e("AFff", checkIns.size() + "");
        adapter.notifyDataSetChanged();

        return v;
    }
}

