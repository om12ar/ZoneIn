package com.swe.zonein.zonein.Controllers;


import com.swe.zonein.zonein.Models.User;

/**
 * Created by Menna on 12/17/2015.
 */

public  class MainControlller {
    public static User user = null;
    public MainControlller(User user){
        this.user=new User(user);

    }

    public static User getUser() {
        return user;
    }

    public void homePage(){

    }
    
//    /**
//	 * Construct a UserController that carry the unique value of user.
//	 * @param iD unique value indicate whether user.
//	 */
//	public UserController(int iD) {
//		super();
//		ID = iD;
//	}
	
//	public boolean sendFriendRequest(int ID)
//	{
//		boolean operationDoneSuccessfully=DBController.sendFriendRequest(this.ID, ID);
//		return operationDoneSuccessfully;
//	}
/*
	public boolean acceptFriendRequest(int notifyNumber,boolean accept)
	{
		boolean operationDoneSuccessfully=false;
		operationDoneSuccessfully=DBController.acceptFriendRequest(accept, notifyNumber);
		return operationDoneSuccessfully;
	}
	*/
//	public boolean AddNewPlace(String lng,String lat, String name,String taste)
//	{
//		Place place=new Place(lng,lat,name,0,taste,User.NUMBER_OF_USER++,0);
//		boolean operationDoneSucessfully=DBController.savePlace(place);
//		return operationDoneSucessfully;
//	}
	
//	public boolean checkInPlace(String text,String time,int ID,String lng,String lat)
//	{
//		boolean operationDoneSuccessfully=false;
//		//Place place=new Place(lng,lat);
//		int placeID=DBController.searchPlaceUsingLngLat(lng, lat);
//		Place place=DBController.getPlace(placeID);
//		//String text, String time, long iD, long userID, long placeID
//		//CheckIn checkIn=new CheckIn(text,time,User.NUMBER_OF_USER++,this.ID,placeID);
//		//operationDoneSuccessfully=DBController.checkIn(checkIn);
//		place.incrementNumberofCheckIn();
//		return operationDoneSuccessfully;
//	}
	
	
}
