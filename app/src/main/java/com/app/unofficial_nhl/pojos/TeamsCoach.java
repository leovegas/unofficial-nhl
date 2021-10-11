package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsCoach {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("teams")
    @Expose
    private List<TeamWithCoach> teams = null;
    @SerializedName("records")
    @Expose
    private List<Record> records = null;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @SerializedName("people")
    @Expose
    private List<Person> people = null;

    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;

    @SerializedName("totalItems")
    @Expose
    private int totalItems;

    @SerializedName("away")
    @Expose
    private Away away;
    @SerializedName("home")
    @Expose
    private Home home;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(int totalEvents) {
        this.totalEvents = totalEvents;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    @SerializedName("totalEvents")
    @Expose
    private int totalEvents;
    @SerializedName("totalGames")
    @Expose
    private int totalGames;
    @SerializedName("totalMatches")
    @Expose
    private int totalMatches;
    @SerializedName("wait")
    @Expose
    private int wait;
    @SerializedName("dates")
    @Expose
    private List<Date> dates = null;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<TeamWithCoach> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamWithCoach> teams) {
        this.teams = teams;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
