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

public class TasteSorting implements SortingStrategy{
    User user;
    double longnitude;
    double latidue;

    /**
     * Constructor takes user data 
     * and the current positin of the user.
     */
    public TasteSorting(User u,double lng,double lat) {
        user=new User(u);
        longnitude=lng;
        lat=latidue;
    }

    /**
     * Function take recommend places and 
     * sort them depending on user data of taste.
     * int implemented depending bubble sort.
     * @param place arraylist of recommended places.
     */
    @Override
    public void sort(ArrayList<Place> place) {
        Place temp;
        for (int x=0; x<place.size(); x++)         {
            for (int i=0; i < place.size()- x -1 ; i++) {
                int value1=user.check_taste(place.get(x).getTaste());
                int value2=user.check_taste(place.get(x+1).getTaste());
                if (value1 >= value2)
                {
                    temp = place.get(i);
                    place.set(i, place.get(i+1) );

                    place.set(i+1, temp);
                }
            }
        }
    }

}