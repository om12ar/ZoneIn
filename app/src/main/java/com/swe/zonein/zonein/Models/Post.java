package com.swe.zonein.zonein.Models;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by omar on 12/17/2015.
 */
public class Post {
    int ID;
    int likes=0;
    int ratingNumber=0;
    int comments=0;
    String placeName;
    //ArrayList<Comment> comments=new ArrayList<Comment>();
    String description;
    int creatorID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void incrementLikes()
    {
        likes+=1;
    }
    public void decrementLikes()
    {
        likes-=1;
    }
    public void incrementComments()
    {
        comments+=1;
    }
    public void decrementComments()
    {
        comments-=1;
    }


 //   public abstract boolean like();

 //   public  abstract boolean comment();
}
