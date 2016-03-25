package com.swe.zonein.zonein.Models;


/**
 * Message class set the message and the chat that the message belongs to. 
 *@author Menna
 *@since 24-12-2015 
 */
public class Message {
	int chatID;
	String text;
	private int ID;

	/**
	 * Return string of the String text return in the message.
	 * @return text.string that contain the user message.
	 */
	public String returnMessage() {
		return text;
	}

	/**
	 * set a string to message.
	 * @param text . contain the user message.
	 */
	public void addMessage(String text) {
		this.text = text;
	}

	/**
	 * Return a unique positive value describe the data that the user hold.
	 * @return ID. a unique positive value.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * set the ID.
	 * @param ID . a unique integer .
	 */
	public void setID(int ID)
	{
		this.ID=ID;
	}

}