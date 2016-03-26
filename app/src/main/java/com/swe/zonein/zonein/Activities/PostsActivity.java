package com.swe.zonein.zonein.Activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.swe.zonein.zonein.Controllers.JSONParser;
import com.swe.zonein.zonein.Controllers.MainControlller;
import com.swe.zonein.zonein.Models.Post;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

/**
 * Created by Noha on 3/25/2016.
 */

// Not Final
public class PostsActivity extends ListActivity {
    final String TAG = "PostsActivity";
    ArrayList<Post> posts = new ArrayList<>();
    User user;
    private postsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        adapter = new postsAdapter();
        setListAdapter(adapter);

    }

    private class postsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return posts.size();
        }

        @Override
        public String getItem (int position) {
            return posts.get(position).getPlaceName();
        }

        public String getName(int position) {
            return posts.get(position).getPlaceName();
        }

        public long getRatingNumber(int position) {
            return posts.get(position).getRatingNumber();
        }

        public String getDescription(int position) {
            return posts.get(position).getDescription();
        }

        public long getLikes(int position) {
            return posts.get(position).getLikes();
        }
        public long getComments(int position) {
            return posts.get(position).getComments();
        }

        public long getId(int position) {
            return posts.get(position).getID();
        }
        public long getCreatorId(int position) {
            return posts.get(position).getCreatorID();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView holder1 = null;
            TextView holder2 = null;
            RatingBar holder3 = null;
            Button holder4 = null;
            Button holder5 = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.post_item, parent, false);


                holder1 = (TextView) convertView.findViewById(R.id.placeNameTextView);
                holder2 = (TextView) convertView.findViewById(R.id.placeDescription);
                holder3 = (RatingBar) convertView.findViewById(R.id.placeRatingBar);
                holder4 = (Button) convertView.findViewById(R.id.commentButton);
                holder5 = (Button) convertView.findViewById(R.id.likeButton);

                convertView.findViewById(R.id.commentButton).setOnClickListener(commentsButtonClickListener);
                convertView.findViewById(R.id.likeButton).setOnClickListener(likesButtonClickListener);

                convertView.setTag(holder1);
            } else {
                holder1 = (TextView) convertView.getTag();
                holder2 = (TextView) convertView.getTag();
                holder3 = (RatingBar) convertView.getTag();
                holder4 = (Button) convertView.getTag();
                holder5 = (Button) convertView.getTag();
            }

            Log.e(TAG, posts.get(position).getPlaceName());
            holder1.setText(posts.get(position).getPlaceName());
            holder2.setText(posts.get(position).getDescription());
            holder3.setNumStars(posts.get(position).getRatingNumber());
            holder4.setText(posts.get(position).getComments());
            holder5.setText(posts.get(position).getLikes());

            return convertView;
        }
    }

    private View.OnClickListener likesButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = getListView().getPositionForView(v);
            if (position != ListView.INVALID_POSITION) {
                posts.get(position).incrementLikes();

            }
        }
    };



        private View.OnClickListener commentsButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = getListView().getPositionForView(v);
                if (position != ListView.INVALID_POSITION) {
                    Log.e(TAG, position + "");
                    posts.get(position).incrementComments();
                }

            }
        };
}
