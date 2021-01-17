package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeagueRecord_ {

    @SerializedName("wins")
    @Expose
    private int wins;
    @SerializedName("losses")
    @Expose
    private int losses;
    @SerializedName("ot")
    @Expose
    private int ot;
    @SerializedName("type")
    @Expose
    private String type;

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getOt() {
        return ot;
    }

    public void setOt(int ot) {
        this.ot = ot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
