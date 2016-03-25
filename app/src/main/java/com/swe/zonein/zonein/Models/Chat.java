package com.swe.zonein.zonein.Models;





import java.util.ArrayList;

/**
 *Chat class add a chat and add a message and set user to chat
 *@author Omar
 *@version 1.0
 *@since 25/12/2015 
 */
public class Chat {
    ArrayList<User> user=new ArrayList<User>();
    ArrayList <Message> message=new ArrayList<Message>();
    int ID;
    //int number=1000000;
    /**
     * Return an arrayList of user that are in this chat.
     * @return user. an ArrayList of user in current chat.
     * or null if no user yet in chat.
     */
    public  ArrayList<User> returnUser()
    {
        return user;
    }
//    /**
//     * Default Constructor
//     */
//    public Chat()
//    {
//        setID(number--);
//        //return operationDoneSuccessfully;
//    }
    /**
     * Copy constructor used to copy chat's data in other chat data.
     * @param chat . chat needed to be copied.
     */
    public Chat(Chat chat)
    {
        for(int i=0;i<user.size();i++)
        {
            user.add(chat.user.get(i));
        }
        for(int i=0;i<message.size();i++)
        {
            message.add(chat.message.get(i));
        }
        ID=chat.getID();
    }
    /**
     * Return boolean value indicate the sucess 
     * or the failure of adding user to the database.
     * @param user . the information data of user needed to be added.
     * @return true . iff the user is added to the chat in the database sucessfully
     * otherwise it return false.
     */
   /* public boolean addUser(User user)
    {
        boolean operationDoneSuccessfully=false;
        operationDoneSuccessfully= DBController.addUserinChat(this.getID(),user.getID());
        return  operationDoneSuccessfully;
    }

    *//**
     * Return boolean value that indicate the succession of the operation 
     * or failure.
     * it function is to send the message that needed to be added to the 
     * database.
     * @param message . it contain the data of message needed to be added.
     * @return true. iff the function is added successfully otherwise false.
     *//*

    public boolean addMessage(Message message)
    {
        boolean operationDoneSuccessfully= DBController.addMessageinChat(this.getID(), message.getID());
        return  operationDoneSuccessfully;
    }*/

    /**
     * Return the history of the message of the chat in ana arrayList 
     * of messages .
     * @return message. an arrayList of message.
     */
    public ArrayList<Message> returnMessage()
    {
        return  message;
    }

    /**
     * Return a boolean indicate the operation done successfully or not.
     * it add an id to message and creat a new chat in database.
     * @return true. iff the operation of adding a new added sucessfully.
     * otherwise return false.
     */
   /* public boolean setID(int ID)
    {
        this.ID=ID;
        boolean operationDoneSuccessfully= DBController.addChat(ID);
        return  operationDoneSuccessfully;
    }*/

    /**
     * Return a unique positive integer the represent the chat.
     * @return positve integer represent the data in chat
     */

    public int getID()
    {
        return ID;
    }




}