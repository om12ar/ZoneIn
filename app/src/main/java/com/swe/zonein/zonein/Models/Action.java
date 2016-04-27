package com.swe.zonein.zonein.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Noha on 4/27/2016.
 */
public class Action {

    int actionID;
    int userID;
    String actionType;
    int actionParameterID;


    public Action(JSONObject jsonObject) {

        try {
            actionID = jsonObject.getInt("actionID");
            userID = jsonObject.getInt("userID");
            actionType = jsonObject.getString("actionType");
            actionParameterID = jsonObject.getInt("parameterID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

