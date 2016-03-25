package com.swe.zonein.zonein.Models;



import com.swe.zonein.zonein.Models.SortingStrategies.SortingStrategy;

import java.util.ArrayList;

/**
 * class List have the place recommended 
 * to the user and the techinque the user what
 * to sort the places .
 * @author Menna
 * @version 1.0
 * @since 2015-12-26
 */
public class List {

    ArrayList <Place> place=new ArrayList<Place>();
    SortingStrategy sortingStrategy;
    //=============================//

    /**
     * the strategy of the sorting methods.
     * @param sortingStrategy the strategy that user want sort upon it.
     * sorting Strategy upon the distance, rating or taste. 
     */
    public List(SortingStrategy sortingStrategy)
    {
        this.sortingStrategy=sortingStrategy;
    }

    /**
     * sort function call the techique sort that user choose.
     */
    public void sort(){
        sortingStrategy.sort(place);
    }


}