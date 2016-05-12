package com.swe.zonein.zonein.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Place {
	String lng;
	String lat;
	String name;
    String description;
	int numberOfCheckIn=0;
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
			ID=place.getID();
			rating=place.getRating();
		}

	public Place(String name, String description, String lng, String lat) {
			this.name = name;
            this.description= description;
            this.lng =lng;
            this.lat =lat;
            numberOfCheckIn = 0 ;
            rating=0;
		}
	public Place(JSONObject jsonObject) {

		try {
			//TODO UPDATE
			ID = jsonObject.getInt("id");
			name = jsonObject.getString("name");
			description = jsonObject.getString("description");
			lng = jsonObject.getString("long");
			lat = jsonObject.getString("lat");
			rating = Float.parseFloat(jsonObject.getString("rating"));


		}catch (JSONException e){
			e.printStackTrace();
		}


	}

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
