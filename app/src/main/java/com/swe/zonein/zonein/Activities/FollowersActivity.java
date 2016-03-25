package com.swe.zonein.zonein.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.swe.zonein.zonein.R;

import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * Created by Noha on 3/25/2016.
 */
public class FollowersActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ListView followersListView = (ListView)findViewById(R.id.followersListView);

        final ArrayList<String> followers = new ArrayList<String>();
        //elmfrood a get el followers names list mn el data base hna w a3mlhom add fel arraylist b for loop
        followers.add("folano");
        followers.add("folana");
        followers.add("folano");
        followers.add("folana");
        followers.add("folano");
        followers.add("folana");
        followers.add("folano");
        followers.add("folana");
        followers.add("folana");
        followers.add("folana");
        followers.add("folana");
        followers.add("folana");
        followers.add("folana");
        //
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, followers);

        followersListView.setAdapter(arrayAdapter);

        followersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // elmfrood hna nroo7 le e profile bta3 el follower elly hyb2a clicked on
                Toast.makeText(getApplicationContext(), "this is item number: " + followers.get(position), Toast.LENGTH_LONG).show();

            }
        });

    }
}
