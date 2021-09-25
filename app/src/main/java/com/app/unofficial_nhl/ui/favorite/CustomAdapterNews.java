package com.app.unofficial_nhl.ui.favorite;

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

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapterNews extends RecyclerView.Adapter<CustomAdapterNews.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private ArrayList<ListRowNews> mDataSet;
    Context context;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView headline;
        TextView article;
        ImageView image;
        TextView copyright;
        TextView source;
        TextView pubDate;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            headline = (TextView) v.findViewById(R.id.headline);
            source = (TextView) v.findViewById(R.id.source);
            image = (ImageView) v.findViewById(R.id.imageurl);
            pubDate = (TextView) v.findViewById(R.id.pubDate);
        }

        public TextView getHeadline() {
            return headline;
        }

        public TextView getArticle() {
            return article;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getCopyright() {
            return copyright;
        }

        public TextView getSource() {
            return source;
        }

        public TextView getPubDate() {
            return pubDate;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapterNews(Context context, ArrayList<ListRowNews> dataSet) {
        mDataSet = dataSet;
        this.context = context;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_recycler_list, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        final ListRowNews myDataItem = mDataSet.get(position);

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getHeadline().setText(myDataItem.headline);
        viewHolder.getSource().setText(myDataItem.source);
        viewHolder.getPubDate().setText(myDataItem.date.substring(0, 10));
        DownloadImageTask downloadImageTask = new DownloadImageTask((ImageView) viewHolder.image);
        downloadImageTask.execute(myDataItem.image);

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