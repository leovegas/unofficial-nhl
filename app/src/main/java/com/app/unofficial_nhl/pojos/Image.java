package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

@SerializedName("title")
@Expose
private String title;
@SerializedName("altText")
@Expose
private String altText;
@SerializedName("cuts")
@Expose
private Cuts cuts;

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getAltText() {
return altText;
}

public void setAltText(String altText) {
this.altText = altText;
}

public Cuts getCuts() {
return cuts;
}

public void setCuts(Cuts cuts) {
this.cuts = cuts;
}

}