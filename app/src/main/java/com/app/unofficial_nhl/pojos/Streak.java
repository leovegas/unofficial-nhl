package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Streak {

    @SerializedName("streakType")
    @Expose
    private String streakType;
    @SerializedName("streakNumber")
    @Expose
    private int streakNumber;
    @SerializedName("streakCode")
    @Expose
    private String streakCode;

    public String getStreakType() {
        return streakType;
    }

    public void setStreakType(String streakType) {
        this.streakType = streakType;
    }

    public int getStreakNumber() {
        return streakNumber;
    }

    public void setStreakNumber(int streakNumber) {
        this.streakNumber = streakNumber;
    }

    public String getStreakCode() {
        return streakCode;
    }

    public void setStreakCode(String streakCode) {
        this.streakCode = streakCode;
    }

}
