package com.swe.zonein.zonein.Models;

/**
 * Created by om12ar on 4/21/16.
 */
public class Comment {
    String userName ;
    String commentString;
    int ID ;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public Comment(String userName, String commentString) {
        this.userName = userName;
        this.commentString = commentString;
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



}
