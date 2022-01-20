package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranks {

    @SerializedName("rankPowerPlayGoals")
    @Expose
    private String rankPowerPlayGoals;
    @SerializedName("rankBlockedShots")
    @Expose
    private String rankBlockedShots;
    @SerializedName("rankAssists")
    @Expose
    private String rankAssists;
    @SerializedName("rankShotPct")
    @Expose
    private String rankShotPct;
    @SerializedName("rankGoals")
    @Expose
    private String rankGoals;
    @SerializedName("rankHits")
    @Expose
    private String rankHits;
    @SerializedName("rankPenaltyMinutes")
    @Expose
    private String rankPenaltyMinutes;
    @SerializedName("rankShortHandedGoals")
    @Expose
    private String rankShortHandedGoals;
    @SerializedName("rankPlusMinus")
    @Expose
    private String rankPlusMinus;
    @SerializedName("rankShots")
    @Expose
    private String rankShots;
    @SerializedName("rankPoints")
    @Expose
    private String rankPoints;
    @SerializedName("rankOvertimeGoals")
    @Expose
    private String rankOvertimeGoals;
    @SerializedName("rankGamesPlayed")
    @Expose
    private String rankGamesPlayed;
    @SerializedName("timeOnIce")
    @Expose
    private String timeOnIce;

    public String getShotsAgainst() {
        return shotsAgainst;
    }

    public void setShotsAgainst(String shotsAgainst) {
        this.shotsAgainst = shotsAgainst;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getPenaltyMinutes() {
        return penaltyMinutes;
    }

    public void setPenaltyMinutes(String penaltyMinutes) {
        this.penaltyMinutes = penaltyMinutes;
    }

    public String getTimeOnIce() {
        return timeOnIce;
    }

    public void setTimeOnIce(String timeOnIce) {
        this.timeOnIce = timeOnIce;
    }

    public String getShutOuts() {
        return shutOuts;
    }

    public void setShutOuts(String shutOuts) {
        this.shutOuts = shutOuts;
    }

    public String getSaves() {
        return saves;
    }

    public void setSaves(String saves) {
        this.saves = saves;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getSavePercentage() {
        return savePercentage;
    }

    public void setSavePercentage(String savePercentage) {
        this.savePercentage = savePercentage;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getGoalsAgainstAverage() {
        return goalsAgainstAverage;
    }

    public void setGoalsAgainstAverage(String goalsAgainstAverage) {
        this.goalsAgainstAverage = goalsAgainstAverage;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    @SerializedName("shotsAgainst")
    @Expose
    private String shotsAgainst;
    @SerializedName("ot")
    @Expose
    private String ot;
    @SerializedName("penaltyMinutes")
    @Expose
    private String penaltyMinutes;
    @SerializedName("shutOuts")
    @Expose
    private String shutOuts;
    @SerializedName("saves")
    @Expose
    private String saves;
    @SerializedName("losses")
    @Expose
    private String losses;
    @SerializedName("goalsAgainst")
    @Expose
    private String goalsAgainst;
    @SerializedName("savePercentage")
    @Expose
    private String savePercentage;
    @SerializedName("games")
    @Expose
    private String games;
    @SerializedName("goalsAgainstAverage")
    @Expose
    private String goalsAgainstAverage;
    @SerializedName("wins")
    @Expose
    private String wins;

    public String getRankPowerPlayGoals() {
        return rankPowerPlayGoals;
    }

    public void setRankPowerPlayGoals(String rankPowerPlayGoals) {
        this.rankPowerPlayGoals = rankPowerPlayGoals;
    }

    public String getRankBlockedShots() {
        return rankBlockedShots;
    }

    public void setRankBlockedShots(String rankBlockedShots) {
        this.rankBlockedShots = rankBlockedShots;
    }

    public String getRankAssists() {
        return rankAssists;
    }

    public void setRankAssists(String rankAssists) {
        this.rankAssists = rankAssists;
    }

    public String getRankShotPct() {
        return rankShotPct;
    }

    public void setRankShotPct(String rankShotPct) {
        this.rankShotPct = rankShotPct;
    }

    public String getRankGoals() {
        return rankGoals;
    }

    public void setRankGoals(String rankGoals) {
        this.rankGoals = rankGoals;
    }

    public String getRankHits() {
        return rankHits;
    }

    public void setRankHits(String rankHits) {
        this.rankHits = rankHits;
    }

    public String getRankPenaltyMinutes() {
        return rankPenaltyMinutes;
    }

    public void setRankPenaltyMinutes(String rankPenaltyMinutes) {
        this.rankPenaltyMinutes = rankPenaltyMinutes;
    }

    public String getRankShortHandedGoals() {
        return rankShortHandedGoals;
    }

    public void setRankShortHandedGoals(String rankShortHandedGoals) {
        this.rankShortHandedGoals = rankShortHandedGoals;
    }

    public String getRankPlusMinus() {
        return rankPlusMinus;
    }

    public void setRankPlusMinus(String rankPlusMinus) {
        this.rankPlusMinus = rankPlusMinus;
    }

    public String getRankShots() {
        return rankShots;
    }

    public void setRankShots(String rankShots) {
        this.rankShots = rankShots;
    }

    public String getRankPoints() {
        return rankPoints;
    }

    public void setRankPoints(String rankPoints) {
        this.rankPoints = rankPoints;
    }

    public String getRankOvertimeGoals() {
        return rankOvertimeGoals;
    }

    public void setRankOvertimeGoals(String rankOvertimeGoals) {
        this.rankOvertimeGoals = rankOvertimeGoals;
    }

    public String getRankGamesPlayed() {
        return rankGamesPlayed;
    }

    public void setRankGamesPlayed(String rankGamesPlayed) {
        this.rankGamesPlayed = rankGamesPlayed;
    }

}
