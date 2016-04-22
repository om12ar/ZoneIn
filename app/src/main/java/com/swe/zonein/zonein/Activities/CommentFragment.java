package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Adapters.CommentAdapter;
import com.swe.zonein.zonein.Adapters.UserAdapter;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.Comment;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;
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
public class CommentFragment extends  android.app.Fragment  {
    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    List<Comment> comments;
    ListView otherComments ;
    EditText commentString ;
    Button submitComment;
    CommentAdapter adapter ;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_comment, container, false);
        final int checkinID = getArguments().getInt("checkinID");
        commentString = (EditText) v.findViewById(R.id.commentWriteET);
        submitComment = (Button) v.findViewById(R.id.CommentSubmitBtn);
        final ListView otherComments = (ListView) v.findViewById(R.id.commentsList);
        comments = new ArrayList<>();
        adapter = new CommentAdapter( comments,getActivity());
        otherComments.setAdapter(adapter);

//        comments.add(new Comment("User1", "comment "));
//        comments.add(new Comment("user name ","loooooooooooooooooooooooooooooooooooooooooooong cooooooooooooooooooooooooooooooooooooomment"));
//        comments.add(new Comment("user", "hi"));

        final String url = VolleyController.baseURL + "getComments";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    JSONArray jsonArray = jsnObject.getJSONArray("commentList");
                    if(jsonArray!=null) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                Comment tempComment = new Comment(jsonArray.getJSONObject(i));
                                comments.add(tempComment);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        Log.e("AFff", comments.size() + "");
                        adapter.notifyDataSetChanged();



                    } else {

                    }
                }catch(Exception e){
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
        }){
            @Override
            protected HashMap<String, String> getParams()
            {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("checkinID", "" + checkinID);
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);


        submitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = MainController.user.getName();
                final String comment = commentString.getText().toString();

                final String url = VolleyController.baseURL + "comment";


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsnObject = new JSONObject(response);

                            if (jsnObject != null) {

                            } else {

                            }
                        } catch (JSONException e) {
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
                        params.put("comment", comment);
                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request);
            }
        });


        return  v;
    }



}
