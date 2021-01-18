package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Away {

    @SerializedName("leagueRecord")
    @Expose
    private LeagueRecord leagueRecord;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("team")
    @Expose
    private Team team_;

    public LeagueRecord getLeagueRecord() {
        return leagueRecord;
    }

    public void setLeagueRecord(LeagueRecord leagueRecord) {
        this.leagueRecord = leagueRecord;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Team getTeam() {
        return team_;
    }

    public void setTeam(Team team) {
        this.team_ = team_;
    }

}