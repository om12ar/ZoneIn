package com.swe.zonein.zonein.Controllers;

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
                    JSONArray jsonArray = jsnObject.getJSONArray("status");
                    if(jsonArray!=null){


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

    public void unCheckIn(final int checkInID){

        //TODO in backend then volley

    }

    public void unComment(final int commentID){

        HashMap<String, String> params = new HashMap<>();
        params.put("commentID","" + commentID);
        request("uncomment", params);
    }


    public void unFollow(final int followedID){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("FollowerID", "" + MainController.user.getID());
        params.put("FollowedID", ""+ followedID);
        request("unfollow", params);

    }

    public void unLike(int checkInID){

        HashMap<String, String> params = new HashMap<>();
        params.put("checkInID","" + checkInID);
        params.put("userID", "" + MainController.user.getID());
        request("unlike", params);

    }

    public void unSavePlace(int placeID){


        HashMap<String, String> params = new HashMap<>();
        params.put("placeID","" + placeID);
        params.put("userID", "" + MainController.user.getID());
        request("unsaveplace", params);
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
        request("addaction", params);


    }
}
