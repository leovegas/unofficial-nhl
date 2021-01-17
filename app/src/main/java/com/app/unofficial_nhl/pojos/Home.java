package com.app.unofficial_nhl.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home {

    @SerializedName("leagueRecord")
    @Expose
    private LeagueRecord_ leagueRecord;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("team")
    @Expose
    private Team_ team;

    public LeagueRecord_ getLeagueRecord() {
        return leagueRecord;
    }

    public void setLeagueRecord(LeagueRecord_ leagueRecord) {
        this.leagueRecord = leagueRecord;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team_ getTeam() {
        return team;
    }

    public void setTeam(Team_ team) {
        this.team = team;
    }

}
