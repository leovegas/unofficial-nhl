package com.app.unofficial_nhl.pojos;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teams {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
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

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
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
}
