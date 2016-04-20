package com.swe.zonein.zonein.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/18/16.
 */
public class UserAdapter extends BaseAdapter {

    List<User> list;
    Context context;

    public UserAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    UserAdapter(Context c) {
        context = c;
        list = new ArrayList<User>();
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

  /*  public View getView(int position, View convertView, ViewGroup parent) {
        Holder viewHolder = new Holder();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.user_item, null);
            viewHolder.follow = (Button) convertView.findViewById(R.id.followersButton);
            viewHolder.username = (TextView) convertView.findViewById(R.id.userNameTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.follow.setText("follow");
        viewHolder.username.setText(list.get(position).getName());
        return convertView;
    }

*/

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.user_item, null);

        final Button p = (Button) convertView.findViewById(R.id.followButton);
        TextView u = (TextView) convertView.findViewById(R.id.userNameTextView);

        holder.username = u;
        holder.follow = p;
        int user = list.get(position).getID();
        boolean isFollower;
        isFollower = MainControlller.user.isFollowing(user);
        if (isFollower == true) {
            holder.follow.setText("UnFollow");
        }
        else{
            holder.follow.setText("Follow");
        }


        String username = list.get(position).getName();

        holder.username.setText(username);


        p.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int user = list.get(position).getID();
                boolean isFollower;
                isFollower = MainControlller.user.isFollowing(user);
                if (isFollower == true) {
                    p.setText("Unfollow");
                    p.refreshDrawableState();
                    new followTask().execute("unfollow", "" + MainControlller.user.getID(), "" + list.get(position).getID());
                    MainControlller.user.unfollow(list.get(position).getID());
                    p.setText("Follow");

                } else {
                    p.setText("Follow");
                    p.refreshDrawableState();
                    new followTask().execute("follow", "" + MainControlller.user.getID(), "" + list.get(position).getID());
                    MainControlller.user.follow(list.get(position).getID());
                    p.setText("unFollow");
                }

            }
        });
        return convertView;
    }
}


    class followTask extends AsyncTask<String, Void, JSONArray> {
        JSONParser jsonParser = new JSONParser();

        @Override
        protected JSONArray doInBackground(String... args) {
            try {

                HashMap<String, String> params = new HashMap<>();
                String URL = args[0];
                params.put("followerID", args[1]);
                params.put("followedID", args[2]);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        URL, "POST", params);

                if (json != null) {
                    JSONArray result = json.getJSONArray("status");

                    Log.d("JSON result", json.toString());

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

            if (jsonArray != null) {

                //followers.remove();

            } else {
                //followers.add("No followers");
            }
        }
    }

    class Holder {
        TextView username;
        Button follow;
    }


