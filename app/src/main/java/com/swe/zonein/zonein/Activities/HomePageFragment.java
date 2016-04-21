package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.CheckinAdapter;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by om12ar on 4/21/16.
 */
public class HomePageFragment extends android.app.Fragment {

    ArrayList<CheckIn> homePosts;
    ListView list;
    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.content_main, container, false);
        homePosts = new ArrayList<>();
        CheckinAdapter adapter = new CheckinAdapter(homePosts,getActivity());

        homePosts.add(new CheckIn("bla", "111", 0, 1, 2));
        homePosts.add(new CheckIn("bla bla ", "111", 2.5, 1, 2));
        homePosts.add(new CheckIn("bla bla bla", "111", 5, 1, 2));
        homePosts.add(new CheckIn("bla bla bla bla", "111", 5, 1, 2));
        adapter.notifyDataSetChanged();
        list = (ListView) v.findViewById(R.id.homePageList);
        list.setAdapter(adapter);
        return v;
    }
}
