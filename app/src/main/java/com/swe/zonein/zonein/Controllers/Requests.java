package com.swe.zonein.zonein.Controllers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by malsa on 28/4/2016.
 */
public class Requests{


    public void request(String addedurl, final HashMap<String,String> params){

        final String url = VolleyController.baseURL + addedurl;


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsnObject = new JSONObject(response);
                    if(jsnObject!=null){

                        Log.e("Requests Status is:", response.toString());
                    } else {

                    }
                }catch(Exception e){
                    e.printStackTrace();
                    e.getMessage();
                    System.out.println("ERROR Exception!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR!");
            }
        }){
            @Override
            protected HashMap<String, String> getParams()
            {
                return params;
            }

        };


        VolleyController.getInstance().addToRequestQueue(request);

    }

    public void unCheckIn(int placeID){

        HashMap<String, String> params = new HashMap<>();
        params.put("placeID", "" + placeID);
        params.put("userID", "" + MainController.user.getID());
        request("removeCheckin", params);

    }

    public void unComment(int checkinID){

        HashMap<String, String> params = new HashMap<>();
        params.put("checkinID", "" + checkinID);
        params.put("userID", "" + MainController.user.getID());
        request("uncomment", params);

    }


    public void unFollow(int followedID){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("FollowerID", "" + MainController.user.getID());
        params.put("FollowedID", ""+ followedID);
        request("unfollow", params);

        MainController.user.unfollow(followedID);

    }

    public void unLike(int checkInID){

        HashMap<String, String> params = new HashMap<>();
        params.put("checkinID","" + checkInID);
        params.put("userID", "" + MainController.user.getID());
        request("unlike", params);

        MainController.user.unlikePost(checkInID);

    }

    public void unSavePlace(int placeID){


        HashMap<String, String> params = new HashMap<>();
        params.put("placeID","" + placeID);
        params.put("userID", "" + MainController.user.getID());
        request("unsavePlace", params);

        MainController.user.unSavePlace(placeID);
    }


    public void removePlace(int placeID){

        HashMap<String, String> params = new HashMap<>();
        params.put("placeID","" + placeID);
        request("removePlace", params);

    }

    public void removeAction(int actionID){

        HashMap<String, String> params = new HashMap<>();
        params.put("actionID","" + actionID);
        request("removeaction", params);

    }

    public void addAction(String actionType, String description, int parameterID){

        HashMap<String, String> params = new HashMap<>();
        params.put("userID","" +  MainController.user.getID());
        params.put("actionType", actionType);
        params.put("description", description);
        params.put("parameterID", "" + parameterID);
        request("addaction", params);


    }
}
