package com.swe.zonein.zonein.Models;
import com.swe.zonein.zonein.Controllers.DBController;

import java.util.ArrayList;
/**
 * Class Brand represent the data that the brand hold.
 * the premium user only who can can creat a new brand 
 * this function just take the parameter and set and get it 
 * and add a new place, follower to the brand.
 * @author Menna
 * @version 1.0
 * @since 2015-12-25
 */
public class Brand{
	int BrandID;
	String name;
	ArrayList<User> follower=new ArrayList<User>();
	int UserID;
	//Map<Place,Tips> placeAndTips=new HashMap<>();

	ArrayList<Integer> placeId=new ArrayList<Integer>();

//	static int incrementalIDNumber=100;
	/**
	 * Return unique List of place's ID integers.
	 * @return placeID. unique positive array list of integer represent 
	 * the id's of places that this brand is suponsor or.
	 */
	public ArrayList<Integer> getLocation()
	{
		return placeId;
	}

	/**
	 * Return a boolean value representation 
	 * the succession of adding the place to the database.
	 * this function call a function in database controller to add 
	 * a new place that is suspored by the brand .
	 * @param place the  place that needed to be added in database.
	 * @return true .iff the place is added successfully.
	 * otherwise return false. 
	 */
	public boolean addLocation(Place place)
	{
		boolean operationDoneSuccessfully= DBController.AddPlaceToBrand(place, BrandID);
		return operationDoneSuccessfully;
	}

	/**
	 * Return a boolean value represent the succession 
	 * or the failure of the operation.
	 * it add a new follower to the database and to brand.
	 * @param follower the user that follow the brand.
	 * @return true. iff the operation of adding a new follower to the database.
	 * otherwise return false.
	 */
	public boolean setFollower(User follower)
	{
		this.follower.add(follower);
		boolean operationDoneSuccessfully =DBController.addFollow(this.BrandID, follower.getID());
		return operationDoneSuccessfully;
	}

	/**
	 * Return a boolean value represent the succession or failure of the operation.
	 * it add a new brand to the database.the function take the brand name and 
	 * user id and add it to the database it handle adding the brand in database
	 * by calling function in database controller.
	 * @param brandName . the brand name needed to created.
	 * @param userID . the user that creat brand.
	 * @return true. iff the function of a new brand done successfully.
	 * false otherwise.
	 */
	public boolean addBrand(String brandName, int userID)
	{
		Brand brand=new Brand();
		//brand.setID();
		name=brandName;
		UserID=userID;
		boolean operationDoneSuccessfully=DBController.saveBrand(brand);
		return operationDoneSuccessfully;
	}

	/**
	 * Setter function used to set the value of id 
	 * @param ID . a positive integer.
	 */
	public void setID(int ID) {
		if(ID>=0)
			BrandID=ID;
	}


	/**
	 * getter function used to return the id of the brand.
	 * @return brand id .unique positive value.
	 */
	public int getID(){
		return BrandID;
	}

	/**
	 * getter function used to return the number of follower of a particular brand
	 * the function return only a zero or a positive value
	 * @return a number of follower of this brand.
	 */
	public int followerIncrementation()
	{
		return follower.size();
	}

	/**
	 * Return the follower that follow this brand.
	 * @return ArrayList of user. 
	 * represent the user that follow the brand.
	 */
	public ArrayList<User> getFollower()
	{
		return follower;
	}
	/**
	 * Return a positive value . unique positive value that
	 * Represent the id of the user that creat the brand.
	 * @return userID. a unique positive value represent the creator user.
	 */
	public int getUserID() {
		return UserID;
	}
	/**
	 * Setter function set the use id .
	 * @param userID positive integer.
	 */
	public void setUserID(int userID) {
		if(userID>0)
			UserID = userID;
	}
	/*
	public	boolean setTipsAndPlace(Tips tips,Place place)
	{

	}
	*/
}