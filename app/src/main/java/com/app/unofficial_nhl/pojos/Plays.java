package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Plays {
    @SerializedName("allPlays")
    @Expose
    List<Play> allPlays = null;
    @SerializedName("scoringPlays")
    @Expose
    List<Integer> scoringPlays = null;

    public List<Play> getAllPlays() {
        return allPlays;
    }

    public void setAllPlays(List<Play> allPlays) {
        this.allPlays = allPlays;
    }

    public List<Integer> getScoringPlays() {
        return scoringPlays;
    }

    public void setScoringPlays(List<Integer> scoringPlays) {
        this.scoringPlays = scoringPlays;
    }


}
