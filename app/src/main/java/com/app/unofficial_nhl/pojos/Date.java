package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("totalItems")
    @Expose
    private int totalItems;
    @SerializedName("totalEvents")
    @Expose
    private int totalEvents;
    @SerializedName("totalGames")
    @Expose
    private int totalGames;
    @SerializedName("totalMatches")
    @Expose
    private int totalMatches;
    @SerializedName("games")
    @Expose
    private List<Game> games = null;
    @SerializedName("events")
    @Expose
    private List<Object> events = null;
    @SerializedName("matches")
    @Expose
    private List<Object> matches = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Object> getEvents() {
        return events;
    }

    public void setEvents(List<Object> events) {
        this.events = events;
    }

    public List<Object> getMatches() {
        return matches;
    }

    public void setMatches(List<Object> matches) {
        this.matches = matches;
    }    }
