package com.swe.zonein.zonein.Models.SortingStrategies;



import com.swe.zonein.zonein.Models.Place;

import java.util.ArrayList;

/**
 * interface class implemnted by different sorting techinques.
 * it apply Strategy design.
 * @author Menna
 * @version 1.0
 * @since 2015-12-24
 */
public interface  SortingStrategy {
    ArrayList<Place> place=new ArrayList<Place>();

    public void sort(ArrayList<Place> place);
}