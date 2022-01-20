package com.app.unofficial_nhl.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Split1 {

    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("stat")
    @Expose
    private Ranks stat;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Ranks getStat() {
        return stat;
    }

    public void setStat(Ranks stat) {
        this.stat = stat;
    }

}