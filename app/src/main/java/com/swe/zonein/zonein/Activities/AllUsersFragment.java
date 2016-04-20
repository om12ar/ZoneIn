package com.swe.zonein.zonein.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.swe.zonein.zonein.Adapters.UserAdapter;
import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AllUsersFragment extends android.support.v4.app.Fragment{

    List<User> users;
    ListView usersListView;
    UserAdapter userAdapter ;
    public static AllUsersFragment newInstance() {
        AllUsersFragment fragment = new AllUsersFragment();
        Bundle args = new Bundle();

        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        usersListView = (ListView) v.findViewById(R.id.list_view);
        users = new ArrayList<>() ;
        userAdapter = new UserAdapter(users ,getActivity());

        usersListView.setAdapter(userAdapter);
        fillListView();

        userAdapter.notifyDataSetChanged();
        Log.e("AF", users.toString());
        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return v;
    }
    private void  fillListView() {

        try {
            new getUsersTask().execute("getAllUsers").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.e("AFff", users.size()+"");
        userAdapter.notifyDataSetChanged();
    }

    public class getUsersTask extends AsyncTask<String ,Void , JSONArray> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... args) {


            try {

                HashMap<String, String> params = new HashMap<>();
                String URL =args[0];
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        URL, "POST", params);

                if (json != null) {
                    JSONArray result = json.getJSONArray("userList");

                    Log.d("JSON userList", json.toString());

                    return result;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

            if(jsonArray!=null){

                for (int i=0 ;i < jsonArray.length() ;i++){
                    try {
                        User tempUser = new User(jsonArray.getJSONObject(i));
                        users.add(tempUser);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            Log.e("PE", users.size()+"");
            userAdapter.notifyDataSetChanged();
        }
    }

}
