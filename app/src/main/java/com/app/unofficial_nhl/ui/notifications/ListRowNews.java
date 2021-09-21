package com.app.unofficial_nhl.ui.notifications;

import android.graphics.drawable.Drawable;

public class ListRowNews {

    public String headline;
    public String article;
    public String source;

    String image;

    public ListRowNews(String headline, String article, String source, String image) {
        this.headline = headline;
        this.article = article;
        this.source = source;
        this.image = image;
    }
}
