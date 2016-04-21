package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.swe.zonein.zonein.Adapters.CommentAdapter;
import com.swe.zonein.zonein.Adapters.UserAdapter;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Models.Comment;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;
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

        commentString = (EditText) v.findViewById(R.id.commentWriteET);
        submitComment = (Button) v.findViewById(R.id.CommentSubmitBtn);
        ListView otherComments = (ListView) v.findViewById(R.id.commentsList);
        comments = new ArrayList<>();
        adapter = new CommentAdapter( comments,getActivity());
        otherComments.setAdapter(adapter);

        comments.add(new Comment("User1", "comment "));
        comments.add(new Comment("user name ","loooooooooooooooooooooooooooooooooooooooooooong cooooooooooooooooooooooooooooooooooooomment"));
        comments.add(new Comment("user", "hi"));
        // TODO VOLLEY
       /* try {
            new getUsersTask().execute("getCommentsOnPost").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        Log.e("AFff", comments.size() + "");
        adapter.notifyDataSetChanged();

        submitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = MainController.user.getName();
                String comment = commentString.getText().toString();
                //TODO VOLLEY
            }
        });
        return  v;
    }
}
