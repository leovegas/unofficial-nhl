package com.app.unofficial_nhl.helper_classes;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;

public class ListRow {

    public String team_1;
    public String team_2;
    public @DrawableRes
    int lt1;
    public @DrawableRes
    int lt2;


    public ListRow(String team_1, String team_2, int logo_team_1, int logo_team_2) {
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.lt1 = logo_team_1;
        this.lt2 = logo_team_2;
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

    public int getLt1() {
        return lt1;
    }

    public void setLt1(int lt1) {
        this.lt1 = lt1;
    }

    public int getLt2() {
        return lt2;
    }

    public void setLt2(int lt2) {
        this.lt2 = lt2;
    }
}
