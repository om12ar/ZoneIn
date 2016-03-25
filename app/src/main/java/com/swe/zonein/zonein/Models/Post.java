package com.swe.zonein.zonein.Models;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by omar on 12/17/2015.
 */
public abstract class Post {
    int ID;
    int likes;
    ArrayList<Comment> comments=new ArrayList<Comment>();
    String text;
    int creatorID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public abstract boolean like();

    public  abstract boolean comment();
}
