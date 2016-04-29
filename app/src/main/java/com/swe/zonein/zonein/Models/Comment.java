package com.swe.zonein.zonein.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by om12ar on 4/21/16.
 */
public class Comment {
    String userName ;
    String commentString;
    int commentID;
    int checkinID;


    public Comment(String userName, String commentString) {
        this.userName = userName;
        this.commentString = commentString;
    }

    public Comment(JSONObject jsonObject) {

        try {
            commentID = jsonObject.getInt("commentID");
            userName = jsonObject.getString("userName");
            commentString = jsonObject.getString("comment");
            checkinID = jsonObject.getInt("checkinID");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public int getID() {
        return commentID;
    }

    public void setID(int ID) {
        this.commentID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public int getCheckinID() {
        return checkinID;
    }

    public void setCheckinID(int checkinID) {
        this.checkinID = checkinID;
    }

}
