package com.swe.zonein.zonein.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.R;

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
                int place = list.get(position).getID();
                boolean isSaved;
                isSaved = MainController.user.isPlaceSaved(place);
                if (isSaved == true) {
                    pSave.setText("Unsave");
                    pSave.refreshDrawableState();
                    //TODO VOLLEY
                    //new followTask().execute("unfollow", "" + MainControlller.user.getID(), "" + list.get(position).getID());
                    MainController.user.unSavePlace(list.get(position).getID());
                    pSave.setText("save");

                } else {
                    pSave.setText("Save");
                    pSave.refreshDrawableState();
                    //TODO VOLLEY
                    //    new followTask().execute("follow", "" + MainControlller.user.getID(), "" + list.get(position).getID());
                    MainController.user.SavePlace(list.get(position).getID());
                    pSave.setText("UnSave");
                }

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
