package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.NotificationAdapter;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.NotificationModel;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Noha on 4/21/2016.
 */
public class NotificationsFragment extends android.app.Fragment {


    final String TAG = "Notification Fragment";
    List<NotificationModel> notifications;
    ListView notificationsListView;
    NotificationAdapter notificationAdapter;

    public static NotificationsFragment newInstance() {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        notificationsListView = (ListView) v.findViewById(R.id.list_view);
        notifications = new ArrayList<>();

        notificationAdapter = new NotificationAdapter(notifications, getActivity());

        notificationsListView.setAdapter(notificationAdapter);


        final String url = VolleyController.baseURL + "getallnotification";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("notification");
                    Log.i(TAG, url + " " + response);
                    if (jsonArray != null) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                NotificationModel tempNotification = new NotificationModel(jsonArray.getJSONObject(i));
                                notifications.add(tempNotification);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        notificationAdapter.notifyDataSetChanged();

                        notificationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            }
                        });

                    } else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                    System.out.println("ERROR Exception!");
                }

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR!");
            }
        }) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("ID", "6");
                Log.i(TAG, params.toString());
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);

        return v;
    }


}


