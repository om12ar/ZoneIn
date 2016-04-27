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
/**
     * Constructor that add data to the instaneous 
     * @param ID . unique number refer to the the data of the user.
     * @param name .String contain the user name of the user.
     * @param chats . ArraysList contain all the history of chats of the user.
     * @param notifications . ArrayList contain history of the notification of the user.
     * @param userTastes . ArrayList contain the user's taste.
     * @param userPosts . ArrayList contain the user's history of posts.
     * @param places . ArrayList contain the user's favorite places.
     *//*

    public User(int ID , String name , String userType ,ArrayList<Chat> chats ,ArrayList<NotificationModel> notifications
            , ArrayList<Taste> userTastes , ArrayList<Post> userPosts ,ArrayList<Place> places){
        this.ID = ID ;
        this.name =name ;
        this.userType = userType;
        this.chats= new ArrayList<>(chats);
        this.userTastes = new ArrayList<>(userTastes);
        this.userPosts  = new ArrayList<>(userPosts);
        this.notifications=new ArrayList<>(notifications);
        this.places = new ArrayList<>(places);

    }

    */
/**
     * Copy constructor copy user instaneous in other user instaneous.
     * @param other . user instaneous hold the data needed to be copied.
     *//*

    public User(User other) {
        this.ID = other.ID;
        this.name = other.name;
        this.userType = other.userType;
        this.chats = new ArrayList<>(other.chats);
        this.userTastes = new ArrayList<>(other.userTastes);
        this.userPosts = new ArrayList<>(other.userPosts);
        this.notifications = new ArrayList<>(other.notifications);
        this.places = new ArrayList<>(other.places);
    }
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
    /**
     * Return string inidcate user type whether it premium or ordinary
     * @return userType. string indicate the user type. 
     */

    /**
     * Set notification by one notification her it add on the current notifaction.
     * @param notifications . notificationModel.
     */
    public void setNotifications(NotificationModel notifications)
    {
        NotificationModel notification=new NotificationModel(notifications);
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

    public ArrayList<Integer> getSavedPlaces() {
        return savedPlaces;
    }

    /**
     * Getter an ArrayList of chat contain the history of chat
     * of the user.
     * @return chats. arrayList of chat.
     */
  /*  public ArrayList<Chat> getChats() {
        return chats;
    }
    *//**
     * Set the chat value by ArrayList of chat that 
     * help to copy multiple arraylist of chat at the same time.
     * @param chats . ArrayList of chat.
     *//*
    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
    *//**
     * Set chat by one chat her it add on the current chat.
     * @param chats . chat.
     *//*
    public void setChat(Chat chats)
    {
        this.chats.add(chats);
    }
    *//**
     * Getter an ArrayList of taste contain the preferable tastes 
     * of the user.
     * @return usertatse. arrayList of Taste.
     *//*
    public ArrayList<Taste> getUserTastes() {
        return userTastes;
    }
    *//**
     * Set the taste value by ArrayList of taste that
     * help to copy multiple arraylist of taste at the same time.
     * @param userTastes . ArrayList of taste.
     *//*
    public void setUserTastes(ArrayList<Taste> userTastes) {
        this.userTastes = userTastes;
    }
    *//**
     * Set taste by one taste her it add on the current taste.
     * @param userTastes . Taste.
     *//*
    public void setUserTastes(Taste userTastes)
    {
        this.userTastes.add(userTastes);
    }
    *//**
     * Getter an ArrayList of posts contain the history of posts
     * of the user.
     * @return posts. arrayList of posts.
     *//*
    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }
    *//**
     * Set the post value by ArrayList of post that
     * help to copy multiple arraylist of post at the same time.
     * @param userPosts . ArrayList of post.
     *//*
    public void setUserPosts(ArrayList<Post> userPosts) {
        this.userPosts = userPosts;
    }

    *//**
     * Getter an ArrayList of places contain the save or prefered places
     * of the user.
     * @return place. arrayList of place.
     *//*
    public ArrayList<Place> getPlaces() {
        return places;
    }

    *//**
     * Set the places value by ArrayList of place that
     * help to copy multiple arraylist of place at the same time.
     * @param places . ArrayList of places.
     *//*
    public void setPlaces(ArrayList<Place> places) {
        this.places = new ArrayList<>(places);
    }


    *//**
     * Retrun Number of taste that found to be matched between user taste and place taste .
     * Used in startegy class.
     * Return 0. if no matches between user tastes and place taste.
     * Only return positive integer.
     * @param taste
     * @return counter. number of times that found between the user taste and place taste.
     *//*
    public int check_taste(ArrayList<Taste> taste)
    {
        int counter=0;
        for(int i=0;i<userTastes.size();i++)
        {
            for(int j=0;j<taste.size();j++)
            {
                if(userTastes.get(i).getName().equals(taste.get(j).getName()))
                    counter++;
            }
        }
        return counter;
    }
*/

}

