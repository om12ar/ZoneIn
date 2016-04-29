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
import android.widget.TextView;

import com.swe.zonein.zonein.Activities.RespondToNotificationFragment;
import com.swe.zonein.zonein.Models.NotificationModel;
import com.swe.zonein.zonein.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by om12ar on 4/18/16.
 */
public class NotificationAdapter extends BaseAdapter {

    final String TAG = "Notification ADapter";
    List<NotificationModel> list;
    Context context;

    public NotificationAdapter(List<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    NotificationAdapter(Context c) {
        context = c;
        list = new ArrayList<NotificationModel>();
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
        NotificationHolder holder = new NotificationHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.notification_item, null);

        final Button p = (Button) convertView.findViewById(R.id.notificationButton);
        TextView u = (TextView) convertView.findViewById(R.id.notificationTextView);

        holder.notificationText = u;
        holder.show = p;

        String notificationText = list.get(position).getNotification();

        holder.notificationText.setText(notificationText);
        Log.i(TAG, notificationText);

        p.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                bundle.putInt("checkinID", list.get(position).getPostID());

                Log.e("Notification ADAPTER", bundle.toString());
                RespondToNotificationFragment nextFrag = new RespondToNotificationFragment();
                nextFrag.setArguments(bundle);
                ((Activity) context).getFragmentManager().beginTransaction()
                        .replace(R.id.view_content, nextFrag)
                        .addToBackStack(null)
                        .commit();

            }


        });
        return convertView;
    }

}
class NotificationHolder {
    TextView notificationText;
    Button show;
}

