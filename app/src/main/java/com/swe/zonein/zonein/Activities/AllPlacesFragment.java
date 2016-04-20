package com.swe.zonein.zonein.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.swe.zonein.zonein.Adapters.PlaceAdapter;
import com.swe.zonein.zonein.Adapters.UserAdapter;
import com.swe.zonein.zonein.Models.Place;
import com.swe.zonein.zonein.Models.User;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by om12ar on 4/20/16.
 */
public class AllPlacesFragment extends android.support.v4.app.Fragment{
    List<Place> places;
    ListView listview;
    PlaceAdapter adapter ;
    public static AllPlacesFragment newInstance() {
        AllPlacesFragment fragment = new AllPlacesFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view, container, false);

        listview = (ListView) v.findViewById(R.id.list_view);
        places = new ArrayList<>() ;
        adapter = new PlaceAdapter(places ,getActivity());

        listview.setAdapter(adapter);
        places.add(new Place("1", "desc", "-1", "-1.6"));
        places.add(new Place("2","desc", "-1","-1.6"));
        places.add(new Place("3","desc", "-1","-1.6"));
    // TODO VOLLEY
       /* try {
            new getUsersTask().execute("getAllUsers").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        Log.e("AFff", places.size() + "");
        adapter.notifyDataSetChanged();

        Log.e("AF", places.toString());
      /*  listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });*/
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("allplaces :" ,"ON CLICK ");
                Bundle bundle = new Bundle();
                TextView name = (TextView) view.findViewById(R.id.placeItemNameTV);
                bundle.putString("placeName", name.getText().toString());

                PlaceFragment nextFrag= new PlaceFragment();
                nextFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return v;
    }

}
