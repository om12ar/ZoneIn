package com.swe.zonein.zonein.Models;

public class Taste {
	String name;
	int ID;
	
	
	public String getName() {
		return name;
	}

	public Taste(Taste other){
		this.name=other.name;
		this.ID=other.ID;

	}
	public Taste(){
		name = "";
		ID= -1;
	}
	public void setName(String name) {
		this.name = name;
	}



	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}

	
	public Taste(String name, int iD) {
		super();
		this.name = name;
		ID = iD;
	}

}
