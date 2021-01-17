package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("abstractGameState")
    @Expose
    private String abstractGameState;
    @SerializedName("codedGameState")
    @Expose
    private String codedGameState;
    @SerializedName("detailedState")
    @Expose
    private String detailedState;
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("startTimeTBD")
    @Expose
    private boolean startTimeTBD;

    public String getAbstractGameState() {
        return abstractGameState;
    }

    public void setAbstractGameState(String abstractGameState) {
        this.abstractGameState = abstractGameState;
    }

    public String getCodedGameState() {
        return codedGameState;
    }

    public void setCodedGameState(String codedGameState) {
        this.codedGameState = codedGameState;
    }

    public String getDetailedState() {
        return detailedState;
    }

    public void setDetailedState(String detailedState) {
        this.detailedState = detailedState;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStartTimeTBD() {
        return startTimeTBD;
    }

    public void setStartTimeTBD(boolean startTimeTBD) {
        this.startTimeTBD = startTimeTBD;
    }

}
