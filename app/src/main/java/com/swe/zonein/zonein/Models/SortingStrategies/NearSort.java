package com.swe.zonein.zonein.Models.SortingStrategies;

import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;

import java.util.ArrayList;

/**
 * Class that implements @linkplain SortingStrategy that aplly strategy pattern
 * it take user , longitude and latidue as attribute .
 * it Sort the place data depend on user position and the
 * recommeded places to him.
 * @author Menna
 * @version 1.0
 * @since 2015-12-26
 */
public class NearSort implements SortingStrategy {
    User user;
    double longnitude;
    double latidue;
    /**
     * Construct that take user information
     * and longitude of the current position
     * of user and the latidue of it.
     */
   /* public NearSort(User u,double lng,double lat) {
        user=new User(u);
        longnitude=lng;
        lat=latidue;
    }*/
    /**
     * Return double represent the distance between two longitude and latidue.
     * @param lat1 .the latidue of the first place.
     * @param lon1 the longitude of the first place.
     * @param lat2 the latidue of the second place.
     * @param lon2 the longitude of the second place.
     * @return distance. double integer represent the distance in km.
     */
    public double distance(double lat1,double lon1,double lat2,double lon2) {
        double radlat1 = Math.PI * lat1/180;
        double radlat2 = Math.PI * lat2/180;
        double radlon1 = Math.PI * lon1/180;
        double radlon2 = Math.PI * lon2/180;
        double theta = lon1-lon2;
        double radtheta = Math.PI * theta/180;
        double dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
        dist = Math.acos(dist);
        dist = dist * 180/Math.PI;
        dist = dist * 60 * 1.1515;
        return dist * 1.609344;
    }

    /**
     * Override function function it arrange data depending
     * on the distance between the place the user is
     * in and all the recommended places around.
     * the nearer place the first place putted in array.
     * the sorting techinque use Bubble sort.
     * @param place an ArrayList of recommeded places.
     */
    @Override
    public void sort(ArrayList<Place> place) {
        Place temp;
        for (int x=0; x<place.size(); x++)
        {
            for (int i=0; i < place.size()- x -1 ; i++) {
                double value1=distance(latidue,longnitude,Double.parseDouble(place.get(i).getLat()),Double.parseDouble(place.get(i).getLng()));
                double value2=distance(latidue,longnitude,Double.parseDouble(place.get(i+1).getLat()),Double.parseDouble(place.get(i+1).getLng()));
                if (place.get(i + 1).getRating() >= place.get(i).getRating())
                {
                    temp = place.get(i);
                    place.set(i, place.get(i+1) );

                    place.set(i+1, temp);
                }
            }
        }
    }

}
