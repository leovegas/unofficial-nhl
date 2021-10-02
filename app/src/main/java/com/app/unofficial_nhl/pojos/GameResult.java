package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameResult {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("gamePk")
    @Expose
    private Integer gamePk;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("liveData")
    @Expose
    private LiveData liveData;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getGamePk() {
        return gamePk;
    }

    public void setGamePk(Integer gamePk) {
        this.gamePk = gamePk;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LiveData getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveData liveData) {
        this.liveData = liveData;
    }

}