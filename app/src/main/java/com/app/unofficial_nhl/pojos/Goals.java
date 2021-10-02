package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Goals {

    @SerializedName("away")
    @Expose
    private Integer away;
    @SerializedName("home")
    @Expose
    private Integer home;

    public Integer getAway() {
        return away;
    }

    public void setAway(Integer away) {
        this.away = away;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

}
