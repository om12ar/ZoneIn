package com.swe.zonein.zonein.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Activities.CommentFragment;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.Requests;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/20/16.
 */
public class CheckinAdapter extends BaseAdapter {
    final String TAG = "CheckinAdapter";
    List<CheckIn> list;
    Context context;
    public CheckinAdapter(List<CheckIn> list, Context context) {
        this.list = list;
        this.context = context;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.checkin_item, null);

        final TextView pName = (TextView) convertView.findViewById(R.id.checkinPlaceNameTv);
        TextView uName = (TextView) convertView.findViewById(R.id.checkinUserNameTV);
        TextView desc = (TextView) convertView.findViewById(R.id.checkinDescTV);
        RatingBar pRating = (RatingBar) convertView.findViewById(R.id.checkinRatingbar);
        final Button likeBtn = (Button) convertView.findViewById(R.id.checkinLikeBtn);
        final Button cmntBtn = (Button) convertView.findViewById(R.id.checkinCommentBtn);

        holder.placeName = pName;
        holder.userName = uName;
        holder.desc = desc;
        holder.like = likeBtn;
        holder.comment = cmntBtn;
        holder.rating = pRating;
        holder.rating.setEnabled(false);
        final int checkin = list.get(position).getID();


        //TODO CHANGE TO NAMES
        holder.placeName.setText(list.get(position).getPlaceName()+"");
        holder.userName.setText(list.get(position).getUserName()+"");
        holder.desc.setText(list.get(position).getText() +"");
        holder.rating.setRating((float)list.get(position).getRate());
        //TODO GET NNUMBER OF LIKES
        //holder.like.setText(list.get(position).getLikes()+" Likes");
        holder.like.setText(list.get(position).getLikes() + " Likes");
        holder.comment.setText("Comment");

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = "";

                final boolean isLiked = MainController.user.isLiked(checkin);
                if (isLiked == true) {
                    fn = "unlike";
                } else {
                    fn = "like";
                }


                final String url = VolleyController.baseURL + fn;


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsnObject = new JSONObject(response);
                            Log.i(TAG, url + " " + jsnObject.toString());
                            Log.i(TAG, url + " " + jsnObject.toString());
                            if (jsnObject != null) {

                                if (isLiked) {
                                    likeBtn.setText(list.get(position).getLikes() + " Likes");
                                    MainController.user.unlikePost(list.get(position).getID());

                                } else {
                                    likeBtn.setText(list.get(position).getLikes() + " unLike");
                                    MainController.user.likePost(list.get(position).getID());
                                }

                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                        params.put("checkinID", "" + checkin);
                        params.put("userID", "" + MainController.user.getID());
                        Log.e(TAG + "params ", params.toString());
                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request, TAG);

                if(isLiked == false){

                    String actionType = "like";
                    String description = "You liked " + list.get(position).getUserName() + " Check In";

                    Requests addaction = new Requests();
                    addaction.addAction(actionType, description, checkin);

                }

            }
        });

        cmntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentFragment nextFrag = new CommentFragment();
                int checkinID = list.get(position).getID() ;
                Bundle bundle = new Bundle();
                bundle.putInt("checkinID", checkinID);
                bundle.putString("userName", list.get(position).getUserName());
                bundle.putString("placeName", list.get(position).getPlaceName());
                nextFrag.setArguments(bundle);
                ((Activity) context).getFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return convertView;
    }


    class Holder {
        TextView userName;
        TextView desc;
        TextView placeName;
        RatingBar rating ;
        Button like;
        Button comment;
    }

}
