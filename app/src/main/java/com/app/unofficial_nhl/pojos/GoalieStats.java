package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoalieStats {

    @SerializedName("timeOnIce")
    @Expose
    private String timeOnIce;
    @SerializedName("assists")
    @Expose
    private Integer assists;
    @SerializedName("goals")
    @Expose
    private Integer goals;
    @SerializedName("pim")
    @Expose
    private Integer pim;
    @SerializedName("shots")
    @Expose
    private Integer shots;
    @SerializedName("saves")
    @Expose
    private Integer saves;
    @SerializedName("powerPlaySaves")
    @Expose
    private Integer powerPlaySaves;
    @SerializedName("shortHandedSaves")
    @Expose
    private Integer shortHandedSaves;
    @SerializedName("evenSaves")
    @Expose
    private Integer evenSaves;
    @SerializedName("shortHandedShotsAgainst")
    @Expose
    private Integer shortHandedShotsAgainst;
    @SerializedName("evenShotsAgainst")
    @Expose
    private Integer evenShotsAgainst;
    @SerializedName("powerPlayShotsAgainst")
    @Expose
    private Integer powerPlayShotsAgainst;
    @SerializedName("savePercentage")
    @Expose
    private Double savePercentage;
    @SerializedName("powerPlaySavePercentage")
    @Expose
    private Double powerPlaySavePercentage;
    @SerializedName("shortHandedSavePercentage")
    @Expose
    private Double shortHandedSavePercentage;
    @SerializedName("evenStrengthSavePercentage")
    @Expose
    private Double evenStrengthSavePercentage;

    public String getTimeOnIce() {
        return timeOnIce;
    }

    public void setTimeOnIce(String timeOnIce) {
        this.timeOnIce = timeOnIce;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getPim() {
        return pim;
    }

    public void setPim(Integer pim) {
        this.pim = pim;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
    }

    public Integer getPowerPlaySaves() {
        return powerPlaySaves;
    }

    public void setPowerPlaySaves(Integer powerPlaySaves) {
        this.powerPlaySaves = powerPlaySaves;
    }

    public Integer getShortHandedSaves() {
        return shortHandedSaves;
    }

    public void setShortHandedSaves(Integer shortHandedSaves) {
        this.shortHandedSaves = shortHandedSaves;
    }

    public Integer getEvenSaves() {
        return evenSaves;
    }

    public void setEvenSaves(Integer evenSaves) {
        this.evenSaves = evenSaves;
    }

    public Integer getShortHandedShotsAgainst() {
        return shortHandedShotsAgainst;
    }

    public void setShortHandedShotsAgainst(Integer shortHandedShotsAgainst) {
        this.shortHandedShotsAgainst = shortHandedShotsAgainst;
    }

    public Integer getEvenShotsAgainst() {
        return evenShotsAgainst;
    }

    public void setEvenShotsAgainst(Integer evenShotsAgainst) {
        this.evenShotsAgainst = evenShotsAgainst;
    }

    public Integer getPowerPlayShotsAgainst() {
        return powerPlayShotsAgainst;
    }

    public void setPowerPlayShotsAgainst(Integer powerPlayShotsAgainst) {
        this.powerPlayShotsAgainst = powerPlayShotsAgainst;
    }

    public Double getSavePercentage() {
        return savePercentage;
    }

    public void setSavePercentage(Double savePercentage) {
        this.savePercentage = savePercentage;
    }

    public Double getPowerPlaySavePercentage() {
        return powerPlaySavePercentage;
    }

    public void setPowerPlaySavePercentage(Double powerPlaySavePercentage) {
        this.powerPlaySavePercentage = powerPlaySavePercentage;
    }

    public Double getShortHandedSavePercentage() {
        return shortHandedSavePercentage;
    }

    public void setShortHandedSavePercentage(Double shortHandedSavePercentage) {
        this.shortHandedSavePercentage = shortHandedSavePercentage;
    }

    public Double getEvenStrengthSavePercentage() {
        return evenStrengthSavePercentage;
    }

    public void setEvenStrengthSavePercentage(Double evenStrengthSavePercentage) {
        this.evenStrengthSavePercentage = evenStrengthSavePercentage;
    }

}
