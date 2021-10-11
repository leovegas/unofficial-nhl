package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playback {

@SerializedName("name")
@Expose
private String name;
@SerializedName("width")
@Expose
private Object width;
@SerializedName("height")
@Expose
private Object height;
@SerializedName("url")
@Expose
private String url;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Object getWidth() {
return width;
}

public void setWidth(Object width) {
this.width = width;
}

public Object getHeight() {
return height;
}

public void setHeight(Object height) {
this.height = height;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

}