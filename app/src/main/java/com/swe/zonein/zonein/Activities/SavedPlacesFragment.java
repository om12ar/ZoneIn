package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.PlaceAdapter;

import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/21/16.
 */
public class SavedPlacesFragment extends android.app.Fragment {
    List<Place> places;
    ListView list;
    PlaceAdapter adapter;

    public static SavedPlacesFragment newInstance() {
        SavedPlacesFragment fragment = new SavedPlacesFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        list = (ListView) v.findViewById(R.id.list_view);
        places = new ArrayList<>() ;
        adapter = new PlaceAdapter(places ,getActivity());
        adapter.setButtons(false);
        list.setAdapter(adapter);
        places.add(new Place("1", "desc", "-1", "-1.6"));

        places.add(new Place("3","desc", "-1","-1.6"));

    //TODO Volley


        Log.e("AFff", places.size() + "");
        adapter.notifyDataSetChanged();

        Log.e("AF", places.toString());

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("allplaces :" ,"ON CLICK ");
                Bundle bundle = new Bundle();
                TextView name = (TextView) view.findViewById(R.id.placeItemNameTV);
                bundle.putString("placeName", name.getText().toString());

                PlaceFragment nextFrag= new PlaceFragment();
                nextFrag.setArguments(bundle);
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();

            }
        });


        return v;
    }


}
