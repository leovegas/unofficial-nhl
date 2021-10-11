package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventContent {
    @SerializedName("link")
    @Expose
    String link;
    @SerializedName("editorial")
    @Expose
    Editorial editorial;


    @SerializedName("media")
    @Expose
    MediaEPG mediaEPG;
    @SerializedName("highlights")
    @Expose
    Highlights highlights;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }


    public Highlights getHighlights() {
        return highlights;
    }

    public MediaEPG getMediaEPG() {
        return mediaEPG;
    }

    public void setMediaEPG(MediaEPG mediaEPG) {
        this.mediaEPG = mediaEPG;
    }
    public void setHighlights(Highlights highlights) {
        this.highlights = highlights;
    }
}
