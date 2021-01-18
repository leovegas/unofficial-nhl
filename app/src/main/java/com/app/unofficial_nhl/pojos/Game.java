package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("gamePk")
    @Expose
    private int gamePk;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("gameType")
    @Expose
    private String gameType;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("gameDate")
    @Expose
    private String gameDate;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("teams")
    @Expose
    private Teams teams;
    @SerializedName("venue")
    @Expose
    private Venue venue;

    public int getGamePk() {
        return gamePk;
    }

    public void setGamePk(int gamePk) {
        this.gamePk = gamePk;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


}
