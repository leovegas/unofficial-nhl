package com.app.unofficial_nhl.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("x")
    @Expose
    String x;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @SerializedName("y")
    @Expose
    String y;


}
