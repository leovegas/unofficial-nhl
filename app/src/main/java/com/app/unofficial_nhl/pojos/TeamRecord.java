package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamRecord {

    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("leagueRecord")
    @Expose
    private LeagueRecord leagueRecord;
    @SerializedName("regulationWins")
    @Expose
    private int regulationWins;
    @SerializedName("goalsAgainst")
    @Expose
    private int goalsAgainst;
    @SerializedName("goalsScored")
    @Expose
    private int goalsScored;
    @SerializedName("points")
    @Expose
    private int points;
    @SerializedName("divisionRank")
    @Expose
    private String divisionRank;
    @SerializedName("divisionL10Rank")
    @Expose
    private String divisionL10Rank;
    @SerializedName("divisionRoadRank")
    @Expose
    private String divisionRoadRank;
    @SerializedName("divisionHomeRank")
    @Expose
    private String divisionHomeRank;
    @SerializedName("conferenceRank")
    @Expose
    private String conferenceRank;
    @SerializedName("conferenceL10Rank")
    @Expose
    private String conferenceL10Rank;
    @SerializedName("conferenceRoadRank")
    @Expose
    private String conferenceRoadRank;
    @SerializedName("conferenceHomeRank")
    @Expose
    private String conferenceHomeRank;
    @SerializedName("leagueRank")
    @Expose
    private String leagueRank;
    @SerializedName("leagueL10Rank")
    @Expose
    private String leagueL10Rank;
    @SerializedName("leagueRoadRank")
    @Expose
    private String leagueRoadRank;
    @SerializedName("leagueHomeRank")
    @Expose
    private String leagueHomeRank;
    @SerializedName("wildCardRank")
    @Expose
    private String wildCardRank;
    @SerializedName("row")
    @Expose
    private int row;
    @SerializedName("gamesPlayed")
    @Expose
    private int gamesPlayed;
    @SerializedName("pointsPercentage")
    @Expose
    private double pointsPercentage;
    @SerializedName("ppDivisionRank")
    @Expose
    private String ppDivisionRank;
    @SerializedName("ppConferenceRank")
    @Expose
    private String ppConferenceRank;
    @SerializedName("ppLeagueRank")
    @Expose
    private String ppLeagueRank;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public LeagueRecord getLeagueRecord() {
        return leagueRecord;
    }

    public void setLeagueRecord(LeagueRecord leagueRecord) {
        this.leagueRecord = leagueRecord;
    }

    public int getRegulationWins() {
        return regulationWins;
    }

    public void setRegulationWins(int regulationWins) {
        this.regulationWins = regulationWins;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDivisionRank() {
        return divisionRank;
    }

    public void setDivisionRank(String divisionRank) {
        this.divisionRank = divisionRank;
    }

    public String getDivisionL10Rank() {
        return divisionL10Rank;
    }

    public void setDivisionL10Rank(String divisionL10Rank) {
        this.divisionL10Rank = divisionL10Rank;
    }

    public String getDivisionRoadRank() {
        return divisionRoadRank;
    }

    public void setDivisionRoadRank(String divisionRoadRank) {
        this.divisionRoadRank = divisionRoadRank;
    }

    public String getDivisionHomeRank() {
        return divisionHomeRank;
    }

    public void setDivisionHomeRank(String divisionHomeRank) {
        this.divisionHomeRank = divisionHomeRank;
    }

    public String getConferenceRank() {
        return conferenceRank;
    }

    public void setConferenceRank(String conferenceRank) {
        this.conferenceRank = conferenceRank;
    }

    public String getConferenceL10Rank() {
        return conferenceL10Rank;
    }

    public void setConferenceL10Rank(String conferenceL10Rank) {
        this.conferenceL10Rank = conferenceL10Rank;
    }

    public String getConferenceRoadRank() {
        return conferenceRoadRank;
    }

    public void setConferenceRoadRank(String conferenceRoadRank) {
        this.conferenceRoadRank = conferenceRoadRank;
    }

    public String getConferenceHomeRank() {
        return conferenceHomeRank;
    }

    public void setConferenceHomeRank(String conferenceHomeRank) {
        this.conferenceHomeRank = conferenceHomeRank;
    }

    public String getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(String leagueRank) {
        this.leagueRank = leagueRank;
    }

    public String getLeagueL10Rank() {
        return leagueL10Rank;
    }

    public void setLeagueL10Rank(String leagueL10Rank) {
        this.leagueL10Rank = leagueL10Rank;
    }

    public String getLeagueRoadRank() {
        return leagueRoadRank;
    }

    public void setLeagueRoadRank(String leagueRoadRank) {
        this.leagueRoadRank = leagueRoadRank;
    }

    public String getLeagueHomeRank() {
        return leagueHomeRank;
    }

    public void setLeagueHomeRank(String leagueHomeRank) {
        this.leagueHomeRank = leagueHomeRank;
    }

    public String getWildCardRank() {
        return wildCardRank;
    }

    public void setWildCardRank(String wildCardRank) {
        this.wildCardRank = wildCardRank;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public double getPointsPercentage() {
        return pointsPercentage;
    }

    public void setPointsPercentage(double pointsPercentage) {
        this.pointsPercentage = pointsPercentage;
    }

    public String getPpDivisionRank() {
        return ppDivisionRank;
    }

    public void setPpDivisionRank(String ppDivisionRank) {
        this.ppDivisionRank = ppDivisionRank;
    }

    public String getPpConferenceRank() {
        return ppConferenceRank;
    }

    public void setPpConferenceRank(String ppConferenceRank) {
        this.ppConferenceRank = ppConferenceRank;
    }

    public String getPpLeagueRank() {
        return ppLeagueRank;
    }

    public void setPpLeagueRank(String ppLeagueRank) {
        this.ppLeagueRank = ppLeagueRank;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}