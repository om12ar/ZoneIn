package com.swe.zonein.zonein.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class USer add infromation to each instaneous and the data needed
 * it set the type of user whether he is a primum user or normal user.
 * @author Menna
 * @since 25-10-2015
 * @version 1.0
 */
public class User {
    int ID ;
    String name ;
    String email ;
    String lng ;


    String lat ;
    String pass ;
    ArrayList<Integer> peopleIFollow;
    ArrayList<Integer> peopleFolowingMe;
    ArrayList<Integer> savedPlaces;
    ArrayList<Integer> likedPosts;


    public User() {
        this.name = "";
        ID = -1;

    }

    public User(User other) {
        this.ID = other.ID;
        this.name = other.name;
        this.lat = other.lat;
        this.lng = other.lng;
        this.pass = other.pass;
        this.email = other.email;
        this.peopleIFollow = new ArrayList<>(other.peopleIFollow);
        this.peopleFolowingMe = new ArrayList<>(other.peopleFolowingMe);
        this.savedPlaces = new ArrayList<>(other.savedPlaces);

    }

    /**
     * Constructor thta fill data of the user instaneous.
     *
     * @param ID       . a unique positive integer refer to the data
     *                 that the instaneous hold.
     * @param name     . String hold the username of this instaneous.
     * @param userType .String hold two value premium or ordinary user.
     */
    public User(int ID, String name, String userType) {
        this.ID = ID;
        this.name = name;
    }

    public User(int id) {
    }

    public User(JSONObject jsonObject) {
        try {
            ID = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            email = jsonObject.getString("email");
            pass = jsonObject.getString("pass");
            lat = jsonObject.getString("lat");
            lng = jsonObject.getString("long");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getPeopleFolowingMe() {
        return peopleFolowingMe;
    }

    public void setPeopleFolowingMe(ArrayList<Integer> peopleFolowingMe) {
        this.peopleFolowingMe = peopleFolowingMe;
    }

    public ArrayList<Integer> getPeopleIFollow() {
        return peopleIFollow;
    }

    public void setPeopleIFollow(ArrayList<Integer> peopleIFollow) {
        this.peopleIFollow = peopleIFollow;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

/*

    //////////////////////////////////////////
    */

public void setLng(String lng) {
    this.lng = lng;
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //public User(String name,String user)

    /**
     * Return string of the instaneous username.
     * @return name. username 
     */
    public String getName(){
        return name;
    }

    /**
     * Set the username name of current insateous as the give username.
     * @param name . refer to username.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return unique positive integer.refer to the instaneous information.
     * @return ID. positive value.
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the value of id by unique positive integer.
     * @param ID . a unique positive integer that refer to the data the instaneous hold.
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    public void Addfollower(int temp_id) {
        if(peopleFolowingMe ==null){
            peopleFolowingMe = new ArrayList<>();
        }
        peopleFolowingMe.add(temp_id);

    }

    public void follow(int temp_id) {
        if(peopleIFollow ==null){
            peopleIFollow = new ArrayList<>();
        }
        peopleIFollow.add(temp_id);

    }
    public void unfollow(int temp_id) {
        if(peopleIFollow ==null){
            peopleIFollow = new ArrayList<>();
        }
        int index = peopleIFollow.indexOf(temp_id);
        peopleIFollow.remove(index);
    }
    public boolean isFollower(int temp_id) {
        if(peopleFolowingMe ==null){
            return false;
        }
        int index = peopleFolowingMe.indexOf(temp_id);
        if(index==-1)
            return false;
        return true ;
    }
    public boolean isFollowing(int temp_id) {
        if(peopleIFollow ==null){
            return false;
        }
        int index = peopleIFollow.indexOf(temp_id);
        if(index==-1)
            return false;
        return true ;
    }

    public boolean isPlaceSaved(int placeId) {
        if(savedPlaces ==null){
            return false;
        }
        int index = savedPlaces.indexOf(placeId);
        if(index==-1)
            return false;
        return true ;
    }

    public void unSavePlace(int id) {
        if(savedPlaces ==null){
            savedPlaces = new ArrayList<>();
        }
        int index = savedPlaces.indexOf(id);
        savedPlaces.remove(index);
    }

    public void SavePlace(int id) {
        if(savedPlaces ==null){
            savedPlaces = new ArrayList<>();
        }
        savedPlaces.add(id);
    }

    public boolean isLiked(int checkin) {
        if (likedPosts == null) {
            return false;
        }
        int index = savedPlaces.indexOf(checkin);
        if (index == -1)
            return false;
        return true;
    }

    public void likePost(int checkin) {
        if (likedPosts == null) {
            likedPosts = new ArrayList<>();
        }
        likedPosts.add(checkin);
    }

    public void unlikePost(int checkin) {
        if (likedPosts == null) {
            likedPosts = new ArrayList<>();
        }
        int index = likedPosts.indexOf(checkin);
        likedPosts.remove(index);
    }

    public ArrayList<Integer> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(ArrayList<Integer> likedPosts) {
        this.likedPosts = likedPosts;
    }
}

