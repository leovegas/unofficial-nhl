package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("standingsType")
    @Expose
    private String standingsType;
    @SerializedName("league")
    @Expose
    private League league;
    @SerializedName("division")
    @Expose
    private Division division;
    @SerializedName("conference")
    @Expose
    private Conference conference;
    @SerializedName("teamRecords")
    @Expose
    private List<TeamRecord> teamRecords = null;

    public String getStandingsType() {
        return standingsType;
    }

    public void setStandingsType(String standingsType) {
        this.standingsType = standingsType;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public List<TeamRecord> getTeamRecords() {
        return teamRecords;
    }

    public void setTeamRecords(List<TeamRecord> teamRecords) {
        this.teamRecords = teamRecords;
    }

}
