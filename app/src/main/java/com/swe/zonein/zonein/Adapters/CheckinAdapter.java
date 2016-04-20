package com.swe.zonein.zonein.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Models.CheckIn;
import com.swe.zonein.zonein.R;

import java.util.List;

/**
 * Created by om12ar on 4/20/16.
 */
public class CheckinAdapter extends BaseAdapter {
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

        TextView pName = (TextView) convertView.findViewById(R.id.checkinPlaceNameTv);
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
        int place = list.get(position).getID();


        //TODO CHANGE TO NAMES
        holder.placeName.setText(list.get(position).getPlaceID()+"");
        holder.userName.setText(list.get(position).getUserID()+"");
        holder.desc.setText(list.get(position).getText()+"");
        holder.rating.setRating((float)list.get(position).getRate());
        holder.like.setText(list.get(position).getLikes()+"");
        holder.comment.setText(list.get(position).getComments().size()+"");

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isLiked = false;
                if (isLiked == true) {
                    likeBtn.setText("unlike");
                    likeBtn.refreshDrawableState();
                    //TODO LIKE
                    //new followTask().execute("unfollow", "" + MainControlller.user.getID(), "" + list.get(position).getID());
                    MainController.user.unSavePlace(list.get(position).getID());
                    likeBtn.setText("like");
                    isLiked = false;
                } else {
                    likeBtn.setText("like");
                    likeBtn.refreshDrawableState();
                    //TODO UNLIKE
                    //    new followTask().execute("follow", "" + MainControlller.user.getID(), "" + list.get(position).getID());
                    list.get(position).like();
                    likeBtn.setText("unlike");
                    isLiked = true;
                }

            }
        });
        cmntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO IMPLEMENT COMMENT

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
