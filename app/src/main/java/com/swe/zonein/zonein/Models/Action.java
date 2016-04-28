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
    String description;
    int actionParameterID;

    public int getActionID() {
        return actionID;
    }

    public void setActionID(int actionID) {
        this.actionID = actionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActionParameterID() {
        return actionParameterID;
    }

    public void setActionParameterID(int actionParameterID) {
        this.actionParameterID = actionParameterID;
    }


    public Action(JSONObject jsonObject) {

        try {
            actionID = jsonObject.getInt("actionID");
            userID = jsonObject.getInt("userID");
            actionType = jsonObject.getString("actionType");
            description = jsonObject.getString("description");
            actionParameterID = jsonObject.getInt("parameterID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

