package com.app.unofficial_nhl.helper_classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.unofficial_nhl.R;

import java.util.ArrayList;
import java.util.Collections;

public class MyCustomArrayAdapter extends ArrayAdapter<ListRow> {
    private final Activity context;
    private ArrayList<ListRow> listRows = null;

    public MyCustomArrayAdapter(Activity context, ArrayList<ListRow> listRows) {
        super(context, R.layout.closest_games_list, listRows);
        this.context = context;
        this.listRows = listRows;
    }

    public static class ViewHolder {
        TextView team1;
        TextView team2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.closest_games_list, null);
            viewHolder = new ViewHolder();
            viewHolder.team1 = (TextView) convertView.findViewById(R.id.team_1);
            viewHolder.team2 = (TextView) convertView.findViewById(R.id.team_2);
            convertView.setTag(viewHolder);
        }
            viewHolder = (ViewHolder) convertView.getTag();


        //you can use data.get(position) too
        final ListRow myDataItem = listRows.get(position);

        viewHolder.team1.setText(myDataItem.team_1);
        viewHolder.team2.setText(myDataItem.team_2);


        return convertView;
    }
}

    //-------------------


