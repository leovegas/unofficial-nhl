package com.app.unofficial_nhl.ui.notifications;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ListRowNews {

    public String headline;
    public String article;
    public String source;
    public String date;
    public String image;

    public ListRowNews(String headline, String article, String source, String image, String date) {
        this.headline = headline;
        this.article = article;
        this.source = source;
        this.image = image;
        this.date = date;
    }
}
