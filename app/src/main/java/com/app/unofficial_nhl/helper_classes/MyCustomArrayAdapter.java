package com.app.unofficial_nhl.helper_classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.unofficial_nhl.R;

import java.util.ArrayList;

public class MyCustomArrayAdapter extends ArrayAdapter<ListRow> {
    private final Activity context;
    private ArrayList<ListRow> listRows = new ArrayList<>();

    public MyCustomArrayAdapter(Activity context, ArrayList<ListRow> listRows) {
        super(context, R.layout.closest_games_list, listRows);
        this.context = context;
        this.listRows = listRows;
    }

    public static class ViewHolder {
        TextView team1;
        TextView team2;
        ImageView logo1;
        ImageView logo2;
        TextView venueName;
        TextView detailedState;
        TextView gameDate;
        TextView gameTime;
        TextView homeScore;
        TextView awayScore;

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
            viewHolder.logo1 = (ImageView) convertView.findViewById(R.id.logo_team_1);
            viewHolder.logo2 = (ImageView) convertView.findViewById(R.id.logo_team_2);
            viewHolder.venueName = (TextView) convertView.findViewById(R.id.venueName);
            viewHolder.detailedState = (TextView) convertView.findViewById(R.id.detailedState);
            viewHolder.gameDate = (TextView) convertView.findViewById(R.id.gameDate);
            viewHolder.gameTime = (TextView) convertView.findViewById(R.id.gameTime);
            viewHolder.homeScore = (TextView) convertView.findViewById(R.id.home_score);
            viewHolder.awayScore = (TextView) convertView.findViewById(R.id.away_score);

            convertView.setTag(viewHolder);
        }
            viewHolder = (ViewHolder) convertView.getTag();


        //you can use data.get(position) too
        final ListRow myDataItem = listRows.get(position);
        viewHolder.homeScore.setText(myDataItem.homeScore);
        viewHolder.awayScore.setText(myDataItem.awayScore);
        viewHolder.team1.setText(myDataItem.team_1);
        viewHolder.team2.setText(myDataItem.team_2);
        viewHolder.logo1.setImageDrawable(myDataItem.lt1);
        viewHolder.logo2.setImageDrawable(myDataItem.lt2);
        viewHolder.venueName.setText(myDataItem.venueName);
        viewHolder.gameTime.setText(myDataItem.gameTime);
        viewHolder.gameDate.setText(myDataItem.gameDate);
        viewHolder.detailedState.setText(myDataItem.datailedState);


        return convertView;
    }

}

    //-------------------


