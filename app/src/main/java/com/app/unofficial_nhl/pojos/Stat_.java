package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat_ {

    @SerializedName("gamesPlayed")
    @Expose
    private int gamesPlayed;
    @SerializedName("wins")
    @Expose
    private String wins;
    @SerializedName("losses")
    @Expose
    private String losses;
    @SerializedName("ot")
    @Expose
    private String ot;
    @SerializedName("pts")
    @Expose
    private String pts;
    @SerializedName("ptPctg")
    @Expose
    private String ptPctg;
    @SerializedName("goalsPerGame")
    @Expose
    private String goalsPerGame;
    @SerializedName("goalsAgainstPerGame")
    @Expose
    private String goalsAgainstPerGame;
    @SerializedName("evGGARatio")
    @Expose
    private String evGGARatio;
    @SerializedName("powerPlayPercentage")
    @Expose
    private String powerPlayPercentage;
    @SerializedName("powerPlayGoals")
    @Expose
    private String powerPlayGoals;
    @SerializedName("powerPlayGoalsAgainst")
    @Expose
    private String powerPlayGoalsAgainst;
    @SerializedName("powerPlayOpportunities")
    @Expose
    private String powerPlayOpportunities;
    @SerializedName("penaltyKillPercentage")
    @Expose
    private String penaltyKillPercentage;
    @SerializedName("shotsPerGame")
    @Expose
    private String shotsPerGame;
    @SerializedName("shotsAllowed")
    @Expose
    private String shotsAllowed;
    @SerializedName("winScoreFirst")
    @Expose
    private String winScoreFirst;
    @SerializedName("winOppScoreFirst")
    @Expose
    private String winOppScoreFirst;
    @SerializedName("winLeadFirstPer")
    @Expose
    private String winLeadFirstPer;
    @SerializedName("winLeadSecondPer")
    @Expose
    private String winLeadSecondPer;
    @SerializedName("winOutshootOpp")
    @Expose
    private String winOutshootOpp;
    @SerializedName("winOutshotByOpp")
    @Expose
    private String winOutshotByOpp;
    @SerializedName("faceOffsTaken")
    @Expose
    private String faceOffsTaken;
    @SerializedName("faceOffsWon")
    @Expose
    private String faceOffsWon;
    @SerializedName("faceOffsLost")
    @Expose
    private String faceOffsLost;
    @SerializedName("faceOffWinPercentage")
    @Expose
    private String faceOffWinPercentage;
    @SerializedName("shootingPctg")
    @Expose
    private double shootingPctg;
    @SerializedName("savePctg")
    @Expose
    private double savePctg;
    @SerializedName("penaltyKillOpportunities")
    @Expose
    private String penaltyKillOpportunities;
    @SerializedName("savePctRank")
    @Expose
    private String savePctRank;
    @SerializedName("shootingPctRank")
    @Expose
    private String shootingPctRank;

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getPtPctg() {
        return ptPctg;
    }

    public void setPtPctg(String ptPctg) {
        this.ptPctg = ptPctg;
    }

    public String getGoalsPerGame() {
        return goalsPerGame;
    }

    public void setGoalsPerGame(String goalsPerGame) {
        this.goalsPerGame = goalsPerGame;
    }

    public String getGoalsAgainstPerGame() {
        return goalsAgainstPerGame;
    }

    public void setGoalsAgainstPerGame(String goalsAgainstPerGame) {
        this.goalsAgainstPerGame = goalsAgainstPerGame;
    }

    public String getEvGGARatio() {
        return evGGARatio;
    }

    public void setEvGGARatio(String evGGARatio) {
        this.evGGARatio = evGGARatio;
    }

    public String getPowerPlayPercentage() {
        return powerPlayPercentage;
    }

    public void setPowerPlayPercentage(String powerPlayPercentage) {
        this.powerPlayPercentage = powerPlayPercentage;
    }

    public String getPowerPlayGoals() {
        return powerPlayGoals;
    }

    public void setPowerPlayGoals(String powerPlayGoals) {
        this.powerPlayGoals = powerPlayGoals;
    }

    public String getPowerPlayGoalsAgainst() {
        return powerPlayGoalsAgainst;
    }

    public void setPowerPlayGoalsAgainst(String powerPlayGoalsAgainst) {
        this.powerPlayGoalsAgainst = powerPlayGoalsAgainst;
    }

    public String getPowerPlayOpportunities() {
        return powerPlayOpportunities;
    }

    public void setPowerPlayOpportunities(String powerPlayOpportunities) {
        this.powerPlayOpportunities = powerPlayOpportunities;
    }

    public String getPenaltyKillPercentage() {
        return penaltyKillPercentage;
    }

    public void setPenaltyKillPercentage(String penaltyKillPercentage) {
        this.penaltyKillPercentage = penaltyKillPercentage;
    }

    public String getShotsPerGame() {
        return shotsPerGame;
    }

    public void setShotsPerGame(String shotsPerGame) {
        this.shotsPerGame = shotsPerGame;
    }

    public String getShotsAllowed() {
        return shotsAllowed;
    }

    public void setShotsAllowed(String shotsAllowed) {
        this.shotsAllowed = shotsAllowed;
    }

    public String getWinScoreFirst() {
        return winScoreFirst;
    }

    public void setWinScoreFirst(String winScoreFirst) {
        this.winScoreFirst = winScoreFirst;
    }

    public String getWinOppScoreFirst() {
        return winOppScoreFirst;
    }

    public void setWinOppScoreFirst(String winOppScoreFirst) {
        this.winOppScoreFirst = winOppScoreFirst;
    }

    public String getWinLeadFirstPer() {
        return winLeadFirstPer;
    }

    public void setWinLeadFirstPer(String winLeadFirstPer) {
        this.winLeadFirstPer = winLeadFirstPer;
    }

    public String getWinLeadSecondPer() {
        return winLeadSecondPer;
    }

    public void setWinLeadSecondPer(String winLeadSecondPer) {
        this.winLeadSecondPer = winLeadSecondPer;
    }

    public String getWinOutshootOpp() {
        return winOutshootOpp;
    }

    public void setWinOutshootOpp(String winOutshootOpp) {
        this.winOutshootOpp = winOutshootOpp;
    }

    public String getWinOutshotByOpp() {
        return winOutshotByOpp;
    }

    public void setWinOutshotByOpp(String winOutshotByOpp) {
        this.winOutshotByOpp = winOutshotByOpp;
    }

    public String getFaceOffsTaken() {
        return faceOffsTaken;
    }

    public void setFaceOffsTaken(String faceOffsTaken) {
        this.faceOffsTaken = faceOffsTaken;
    }

    public String getFaceOffsWon() {
        return faceOffsWon;
    }

    public void setFaceOffsWon(String faceOffsWon) {
        this.faceOffsWon = faceOffsWon;
    }

    public String getFaceOffsLost() {
        return faceOffsLost;
    }

    public void setFaceOffsLost(String faceOffsLost) {
        this.faceOffsLost = faceOffsLost;
    }

    public String getFaceOffWinPercentage() {
        return faceOffWinPercentage;
    }

    public void setFaceOffWinPercentage(String faceOffWinPercentage) {
        this.faceOffWinPercentage = faceOffWinPercentage;
    }

    public double getShootingPctg() {
        return shootingPctg;
    }

    public void setShootingPctg(double shootingPctg) {
        this.shootingPctg = shootingPctg;
    }

    public double getSavePctg() {
        return savePctg;
    }

    public void setSavePctg(double savePctg) {
        this.savePctg = savePctg;
    }

    public String getPenaltyKillOpportunities() {
        return penaltyKillOpportunities;
    }

    public void setPenaltyKillOpportunities(String penaltyKillOpportunities) {
        this.penaltyKillOpportunities = penaltyKillOpportunities;
    }

    public String getSavePctRank() {
        return savePctRank;
    }

    public void setSavePctRank(String savePctRank) {
        this.savePctRank = savePctRank;
    }

    public String getShootingPctRank() {
        return shootingPctRank;
    }

    public void setShootingPctRank(String shootingPctRank) {
        this.shootingPctRank = shootingPctRank;
    }

}