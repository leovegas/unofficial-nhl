package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameData {

    @SerializedName("game")
    @Expose
    private Game game;
    @SerializedName("datetime")
    @Expose
    private Datetime datetime;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("teams")
    @Expose
    private Teams teams;

    @SerializedName("goalies")
    @Expose
    private List<Integer> goalies = null;
    @SerializedName("skaters")
    @Expose
    private List<Integer> skaters = null;
    @SerializedName("onIce")
    @Expose
    private List<Integer> onIce = null;

    @SerializedName("scratches")
    @Expose
    private List<Integer> scratches = null;
    @SerializedName("penaltyBox")
    @Expose
    private List<Object> penaltyBox = null;
    @SerializedName("coaches")
    @Expose
    private List<Coach> coaches = null;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Datetime getDatetime() {
        return datetime;
    }

    public void setDatetime(Datetime datetime) {
        this.datetime = datetime;
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

    public List<Integer> getGoalies() {
        return goalies;
    }

    public void setGoalies(List<Integer> goalies) {
        this.goalies = goalies;
    }

    public List<Integer> getSkaters() {
        return skaters;
    }

    public void setSkaters(List<Integer> skaters) {
        this.skaters = skaters;
    }

    public List<Integer> getOnIce() {
        return onIce;
    }

    public void setOnIce(List<Integer> onIce) {
        this.onIce = onIce;
    }


    public List<Integer> getScratches() {
        return scratches;
    }

    public void setScratches(List<Integer> scratches) {
        this.scratches = scratches;
    }

    public List<Object> getPenaltyBox() {
        return penaltyBox;
    }

    public void setPenaltyBox(List<Object> penaltyBox) {
        this.penaltyBox = penaltyBox;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }
}
