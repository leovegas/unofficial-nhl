package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _1136x640 {

@SerializedName("aspectRatio")
@Expose
private String aspectRatio;
@SerializedName("width")
@Expose
private Integer width;
@SerializedName("height")
@Expose
private Integer height;
@SerializedName("src")
@Expose
private String src;
@SerializedName("at2x")
@Expose
private String at2x;
@SerializedName("at3x")
@Expose
private String at3x;

public String getAspectRatio() {
return aspectRatio;
}

public void setAspectRatio(String aspectRatio) {
this.aspectRatio = aspectRatio;
}

public Integer getWidth() {
return width;
}

public void setWidth(Integer width) {
this.width = width;
}

public Integer getHeight() {
return height;
}

public void setHeight(Integer height) {
this.height = height;
}

public String getSrc() {
return src;
}

public void setSrc(String src) {
this.src = src;
}

public String getAt2x() {
return at2x;
}

public void setAt2x(String at2x) {
this.at2x = at2x;
}

public String getAt3x() {
return at3x;
}

public void setAt3x(String at3x) {
this.at3x = at3x;
}

}