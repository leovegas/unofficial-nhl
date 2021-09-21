package com.app.unofficial_nhl.ui.notifications;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.unofficial_nhl.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyCustomArrayAdapterNews extends ArrayAdapter<ListRowNews> {
    private final Activity context;
    private ArrayList<ListRowNews> listRowsNews = new ArrayList<>();

    public MyCustomArrayAdapterNews(Activity context, ArrayList<ListRowNews> listRowsNews) {
        super(context, R.layout.news_list, listRowsNews);
        this.context = context;
        this.listRowsNews = listRowsNews;
    }

    public static class ViewHolder {
        TextView headline;
        TextView article;
        ImageView image;
        TextView copyright;
        TextView source;


    }
    
    public Bitmap getImageFromUrl(String imageurl) {
        Bitmap b = null;
        try
        {
            URL url = new URL(imageurl);
            InputStream is = new BufferedInputStream(url.openStream());
            b = BitmapFactory.decodeStream(is);
        } catch(Exception e){}
        return b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.news_list, null);
            viewHolder = new ViewHolder();
            viewHolder.headline = (TextView) convertView.findViewById(R.id.headline);
            viewHolder.source = (TextView) convertView.findViewById(R.id.source);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageurl);


            convertView.setTag(viewHolder);
        }
            viewHolder = (ViewHolder) convertView.getTag();


        //you can use data.get(position) too
        final ListRowNews myDataItem = listRowsNews.get(position);
        viewHolder.headline.setText(myDataItem.headline);
       // viewHolder.article.setText(myDataItem.article);
        viewHolder.source.setText(myDataItem.source);
       // viewHolder.image.setImageBitmap(getImageFromUrl(myDataItem.image));

        // show The Image in a ImageView
        new DownloadImageTask((ImageView) viewHolder.image)
                .execute(myDataItem.image);


        return convertView;
    }
}



class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}


    //-------------------


