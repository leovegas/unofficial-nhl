package com.app.unofficial_nhl.ui.home;

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapterGames extends RecyclerView.Adapter<CustomAdapterGames.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private ArrayList<ListRow> mDataSet;
    Context context;


    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            team1 = (TextView) v.findViewById(R.id.team_1);
            team2 = (TextView) v.findViewById(R.id.team_2);
            logo1 = (ImageView) v.findViewById(R.id.logo_team_1);
            logo2 = (ImageView) v.findViewById(R.id.logo_team_2);
            venueName = (TextView) v.findViewById(R.id.venueName);
            detailedState = (TextView) v.findViewById(R.id.detailedState);
            gameDate = (TextView) v.findViewById(R.id.gameDate);
            gameTime = (TextView) v.findViewById(R.id.gameTime);
            homeScore = (TextView) v.findViewById(R.id.home_score);
            awayScore = (TextView) v.findViewById(R.id.away_score);
        }

        public TextView getTeam1() {
            return team1;
        }
        public TextView getTeam2() {
            return team2;
        }
        public ImageView getLogo1() {
            return logo1;
        }
        public ImageView getLogo2() {
            return logo2;
        }
        public TextView getVenueName() {
            return venueName;
        }
        public TextView getDetailedState() {
            return detailedState;
        }
        public TextView getGameDate() {
            return gameDate;
        }
        public TextView getGameTime() {
            return gameTime;
        }
        public TextView getHomeScore() {
            return homeScore;
        }
        public TextView getAwayScore() {
            return awayScore;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     * @param listener
     */
    public CustomAdapterGames(Context context, ArrayList<ListRow> dataSet) {
        mDataSet = dataSet;
        this.context = context;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.closest_games_list, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        final ListRow myDataItem = mDataSet.get(position);
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
//        DownloadImageTask downloadImageTask = new DownloadImageTask((ImageView) viewHolder.image);
//        downloadImageTask.execute(myDataItem.image);

    }


    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    ImageView bmImage;
    long startTime;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        Bitmap mIcon11 = null;
        if (((System.currentTimeMillis() - startTime) / 1000) > 5) {
            cancel(true);
        }
        if (!isCancelled()) {
            String urldisplay = urls[0];
            mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;
        } else return mIcon11;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        startTime = System.currentTimeMillis();
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);

    }
}