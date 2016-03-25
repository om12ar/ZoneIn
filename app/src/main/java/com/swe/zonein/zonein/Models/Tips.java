package com.swe.zonein.zonein.Models;

/**
 * Class tip hold all the information needed for the tips 
 * it can change or modify the instaneous data. 
 */

public class Tips {
    Brand brandID;
    Place placeID;
    /**
     * Set the Brand ID . value refer to sender id.
     * @param brandID . unique number that represent or
     *  refer to the data holded in the specific instaneous.
     */
    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }
    /**
     * it is value that hold the data of sender.
     * @return brandID. sender ID a unique positive value.
     */
    public Brand getBrandID() {
        return brandID;
    }
    /**
     * it is value that hold the data of reciever.
     * @return brandID. reciever ID a unique positive value.
     */
    public Place getPlaceID() {
        return placeID;
    }

    /**
     * Set the PlaceID . value refer to the place sent to.
     * @param placeID . unique number that represent or
     *  refer to the data the instaneous data tip is sent to.
     */
    public void setPlaceID(Place placeID) {
        this.placeID = placeID;
    }
}
