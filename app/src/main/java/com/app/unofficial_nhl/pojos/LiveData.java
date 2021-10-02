package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveData {
    @SerializedName("plays")
    @Expose
    Plays plays;
    @SerializedName("decisions")
    @Expose
    Decisions decisions;
    public Plays getPlays() {
        return plays;
    }

    public void setPlays(Plays plays) {
        this.plays = plays;
    }

    public Decisions getDecisions() {
        return decisions;
    }

    public void setDecisions(Decisions decisions) {
        this.decisions = decisions;
    }


}
