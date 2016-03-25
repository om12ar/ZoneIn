package com.swe.zonein.zonein.Models.SortingStrategies;


import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;

import java.util.ArrayList;

/**
 * Class that implements @linkplain SortingStrategy that apply strategy pattern
 * it take user , longitude and latidue as attribute .
 * it sort the place depending on user taste.
 * @author Menna
 * @version 1.0
 * @since 2015-12-26
 */
public class RatingSort implements SortingStrategy {

    User user;
    double longnitude;
    double latidue;

    /**
     * Constructor take user information and the current position of the user.
     * @param u information of the user.
     * @param lng . longitude of the user current position.
     * @param lat latidue of the user current position.
     */
    public RatingSort(User u,double lng,double lat) {
        user=new User(u);
        longnitude=lng;
        lat=latidue;
    }

    /**
     * sorting function takes the recommended places to the user and
     * sort them depend on the place rating the one with large rating become the first recommended 
     */
    @Override
    public void  sort(final ArrayList<Place> place) {
        Place temp;
        for (int x=0; x<place.size(); x++) // bubble sort outer loop
        {
            for (int i=0; i < place.size()- x -1 ; i++) {
                if (place.get(i + 1).getRating() <= place.get(i).getRating())
                {
                    temp = place.get(i);
                    place.set(i, place.get(i+1) );

                    place.set(i+1, temp);
                }
            }
        }
    }

}