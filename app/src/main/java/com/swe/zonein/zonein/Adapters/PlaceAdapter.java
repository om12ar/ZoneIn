package com.swe.zonein.zonein.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Activities.PlaceFragment;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/20/16.
 */
public class PlaceAdapter extends BaseAdapter{
    List<Place> list;
    Context context;
    boolean buttonStatus = true;
    public PlaceAdapter(List<Place> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.place_item, null);

        TextView pName = (TextView) convertView.findViewById(R.id.placeItemNameTV);
        TextView pDesc = (TextView) convertView.findViewById(R.id.placeDescTV);
        RatingBar pRating = (RatingBar) convertView.findViewById(R.id.placeRatingBar);
        final Button pSave = (Button) convertView.findViewById(R.id.placeSaveBtn);

        holder.placeName = pName;
        holder.placeDesc = pDesc;
        holder.rating = pRating;
        holder.save=pSave ;
        holder.rating.setEnabled(false);
        int place = list.get(position).getID();
        boolean isSaved  ;
        isSaved = MainController.user.isPlaceSaved(place);
        if (isSaved == true) {
            holder.save.setText("UnSave");
        }
        else{
            holder.save.setText("Save");
        }


        holder.placeName.setText(list.get(position).getName());
        holder.placeDesc.setText(list.get(position).getDescription());

        pSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.e("PLACE Fragment ", "hi");
                final int place = list.get(position).getID();
                boolean isSaved;
                isSaved = MainController.user.isPlaceSaved(place);
                String fn = "";
                if (isSaved == true) {
                    fn = "Unsave";
                    pSave.refreshDrawableState();
                    //TODO VOLLEY

                    MainController.user.unSavePlace(list.get(position).getID());


                } else {
                    fn = "saveplaces";
                    pSave.setActivated(false);
                    pSave.refreshDrawableState();


                }
                final String url = VolleyController.baseURL + fn;
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsnObject = new JSONObject(response);

                            if (jsnObject != null) {
                                System.out.print("PlaceADapter save " + jsnObject + " " + url);

                                MainController.user.SavePlace(list.get(position).getID());
                                pSave.setText("UnSave");

                            } else {

                            }
                        } catch (Exception e) {
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
                }) {
                    @Override
                    protected HashMap<String, String> getParams() {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("userID", "" + MainController.user.getID());
                        params.put("placeID", "" + place);
                        System.out.print(params.toString());
                        return params;
                    }

                };


                VolleyController.getInstance().addToRequestQueue(request);


            }
        });

        pName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("allplaces :", "ON CLICK ");
                Bundle bundle = new Bundle();
                TextView name = (TextView) v.findViewById(R.id.placeItemNameTV);
                bundle.putString("placeName", name.getText().toString());
                bundle.putInt("placeID", list.get(position).getID());
                bundle.putFloat("rating", list.get(position).getRating());
                Log.e("PLACEE ADAPTER", bundle.toString());
                PlaceFragment nextFrag = new PlaceFragment();
                nextFrag.setArguments(bundle);
                ((Activity) context).getFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return convertView;
    }


    public void setButtons(boolean b) {
        buttonStatus = b ;
    }


    class Holder {
        TextView placeName;
        TextView placeDesc;
        RatingBar rating ;
        Button save;
    }

}
