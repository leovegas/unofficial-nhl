package com.app.unofficial_nhl.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person__45 {

    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("link")
    @Expose
    private String link;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
