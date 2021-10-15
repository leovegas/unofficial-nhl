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
    private Team team;

    public TeamStats getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(TeamStats teamStats) {
        this.teamStats = teamStats;
    }

    @SerializedName("teamStats")
    @Expose
    private TeamStats teamStats;


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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
