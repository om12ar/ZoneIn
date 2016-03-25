package com.swe.zonein.zonein.Models;
import java.util.ArrayList;

public class Place {
	String lng;
	String lat;
	String name;
    String description;
	int numberOfCheckIn=0;
	ArrayList<Taste> taste= new ArrayList<Taste>();
	int ID;
	float rating;
	public Place(){

	}

		public Place(Place place)
		{
			lng=place.getLng();
			lat=place.getLat();
			name=place.getName();
			numberOfCheckIn=place.getNumberOfCheckIn();
			for(int i=0;i<place.taste.size();i++)
			{
				taste.add(place.taste.get(i));
			}
			ID=place.getID();
			rating=place.getRating();
		}

		public Place(String lng, String lat, String name, int numberOfCheckIn,ArrayList<Taste> taste, int iD,
		float rating) {
			super();
			this.lng = lng;
			this.lat = lat;
			this.name = name;
			this.numberOfCheckIn = numberOfCheckIn;
			//this.taste.add(taste);
			for(int i=0;i<taste.size();i++)
			{
				this.taste.add(taste.get(i));
			}
			ID = iD;
			this.rating=rating;
		}

		// public Place(String lng, String lat, String name) {
		//   this.ID = ID;
//    }

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfCheckIn() {
		return numberOfCheckIn;
	}

	public void setNumberOfCheckIn(int numberOfCheckIn) {
		this.numberOfCheckIn = numberOfCheckIn;
	}

	public ArrayList<Taste> getTaste() {
		return taste;
	}

	public void setTaste(ArrayList<Taste> taste) {
		this.taste = taste;
	}

	public void setTaste(Taste taste) {
		this.taste .add(taste);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}


	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int incrementNumberofCheckIn()
	{
		numberOfCheckIn++;
		return numberOfCheckIn;
	}


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
