package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerRank {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("stats")
    @Expose
    private List<Stat1> stats = null;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Stat1> getStats() {
        return stats;
    }

    public void setStats(List<Stat1> stats) {
        this.stats = stats;
    }

}