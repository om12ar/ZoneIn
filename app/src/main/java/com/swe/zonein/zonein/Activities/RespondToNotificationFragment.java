package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.CheckinAdapter;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RespondToNotificationFragment extends  android.app.Fragment {


    final String TAG = " Respond to notification fragment";
    List<CheckIn> checkIns;
    ListView listView;
    CheckinAdapter adapter;

    public static CheckInFragment newInstance() {
        CheckInFragment fragment = new CheckInFragment();
        Bundle args = new Bundle();
        return fragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.list_view, container, false);

        final int checkinID = getArguments().getInt("checkinID");

        Toast.makeText(getActivity(), checkinID + "", Toast.LENGTH_LONG).show();

        listView = (ListView) v.findViewById(R.id.list_view);
        checkIns = new ArrayList<>();
        adapter = new CheckinAdapter(checkIns, getActivity());
        listView.setAdapter(adapter);


        final String url = VolleyController.baseURL + "getCheckinsByID";
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    System.out.println(TAG + " " + url + " " + response);
                    if (jsnObject != null) {

                        checkIns.add(new CheckIn(jsnObject));


                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getMessage();
                    System.out.println("ERROR Exception!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR!");
            }
        }) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("checkinID", "" + checkinID);
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);
        adapter.notifyDataSetChanged();

        return v;
    }
}
