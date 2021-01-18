package com.app.unofficial_nhl.helper_classes;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class ListRow {

    public String team_1;
    public String team_2;
    public String venueName;
    public String gameTime;
    public String gameDate;
    public String datailedState;
    public String awayScore;
    public String homeScore;

    Drawable lt1;
    Drawable lt2;

    public ListRow(String team_1, String team_2, String venueName, String gameTime, String gameDate, String datailedState, String awayScore, String homeScore, Drawable lt1, Drawable lt2) {
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.venueName = venueName;
        this.gameTime = gameTime;
        this.gameDate = gameDate;
        this.datailedState = datailedState;
        this.awayScore = awayScore;
        this.homeScore = homeScore;
        this.lt1 = lt1;
        this.lt2 = lt2;
    }


    public String getTeam_1() {
        return team_1;
    }

    public void setTeam_1(String team_1) {
        this.team_1 = team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public void setTeam_2(String team_2) {
        this.team_2 = team_2;
    }


    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getDatailedState() {
        return datailedState;
    }

    public void setDatailedState(String datailedState) {
        this.datailedState = datailedState;
    }

    public Drawable getLt1() {
        return lt1;
    }

    public void setLt1(Drawable lt1) {
        this.lt1 = lt1;
    }

    public Drawable getLt2() {
        return lt2;
    }

    public void setLt2(Drawable lt2) {
        this.lt2 = lt2;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }
}
