package com.swe.zonein.zonein.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.swe.zonein.zonein.Models.Comment;
import com.swe.zonein.zonein.R;

import java.util.List;

/**
 * Created by om12ar on 4/21/16.
 */
public class CommentAdapter extends BaseAdapter {
    List<Comment> list;
    Context context;
    public CommentAdapter(List<Comment> list, Context context) {
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
        convertView = inflater.inflate(R.layout.comment_item, null);


        TextView uName = (TextView) convertView.findViewById(R.id.commentUserNameTV);
        TextView desc = (TextView) convertView.findViewById(R.id.commentTextTV);

        holder.userName = uName;
        holder.commentString = desc;

        int place = list.get(position).getID();


        //TODO CHANGE TO NAMES
        //holder.userName.setText(list.get(position).getUserName()+"");
        uName.setText(list.get(position).getID() + "");
        desc.setText(list.get(position).getCommentString()+"");


        return convertView;
    }


    class Holder {
        TextView userName;
        TextView commentString;

    }
}
