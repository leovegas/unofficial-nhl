package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("gameType")
    @Expose
    private Object gameType;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Object getGameType() {
        return gameType;
    }

    public void setGameType(Object gameType) {
        this.gameType = gameType;
    }

}