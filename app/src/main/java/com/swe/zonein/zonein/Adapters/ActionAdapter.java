package com.swe.zonein.zonein.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.swe.zonein.zonein.Controllers.MainController;
import com.swe.zonein.zonein.Controllers.RequestInvoker;
import com.swe.zonein.zonein.Controllers.VolleyController;
import com.swe.zonein.zonein.Models.Action;
import com.swe.zonein.zonein.Models.Command;
import com.swe.zonein.zonein.Models.RemoveActionCommand;
import com.swe.zonein.zonein.Models.UnCommentCommand;
import com.swe.zonein.zonein.Models.UnFollowCommand;
import com.swe.zonein.zonein.Models.UnLikeCommand;
import com.swe.zonein.zonein.Models.UnSavePlaceCommand;
import com.swe.zonein.zonein.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by om12ar on 4/18/16.
 */
public class ActionAdapter extends BaseAdapter {

    List<Action> list;
    Context context;

    public ActionAdapter(List<Action> list, Context context) {
        this.list = list;
        this.context = context;
    }

    ActionAdapter(Context c) {
        context = c;
        list = new ArrayList<Action>();
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
        ActionHolder holder = new ActionHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.action_item, null);

        final Button p = (Button) convertView.findViewById(R.id.undoButton);
        TextView u = (TextView) convertView.findViewById(R.id.actionTextView);

        holder.action = u;
        holder.undo = p;

        holder.undo.setText("Undo");
        holder.action.setText(list.get(position).getDescription());

        final String actionType= list.get(position).getActionType();




        p.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               if(actionType.equals("checkIn")){

               }
               else if(actionType.equals("comment")){

                    Command command = new UnCommentCommand();
                    RequestInvoker invoker = new RequestInvoker(command);
                    invoker.undo(list.get(position).getActionParameterID());
                }
                else if(actionType.equals("follow")){

                    Command command = new UnFollowCommand();
                    RequestInvoker invoker = new RequestInvoker(command);
                    invoker.undo(list.get(position).getActionParameterID());
                }
                else if(actionType.equals("like")){

                    Command command = new UnLikeCommand();
                    RequestInvoker invoker = new RequestInvoker(command);
                    invoker.undo(list.get(position).getActionParameterID());
                }
                else if(actionType.equals("saveplace")){

                    Command command = new UnSavePlaceCommand();
                    RequestInvoker invoker = new RequestInvoker(command);
                    invoker.undo(list.get(position).getActionParameterID());
                }

                Command command = new RemoveActionCommand();
                RequestInvoker invoker = new RequestInvoker(command);
                invoker.undo(list.get(position).getActionID());

                list.remove(position);

                notifyDataSetChanged();
            }


        });
        return convertView;
    }

}
class ActionHolder {
    TextView action;
    Button undo;
}

