package com.app.unofficial_nhl.pojos;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("blurb")
    @Expose
    private String blurb;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("authFlow")
    @Expose
    private Boolean authFlow;
    @SerializedName("mediaPlaybackId")
    @Expose
    private String mediaPlaybackId;
    @SerializedName("mediaState")
    @Expose
    private String mediaState;
    @SerializedName("keywords")
    @Expose
    private List<Keyword> keywords = null;
    @SerializedName("epg")
    @Expose
    private List<Epg> epg = null;

    @SerializedName("playbacks")
    @Expose
    private List<Playback> playbacks = null;

    public List<Epg> getEpg() {
        return epg;
    }

    public void setEpg(List<Epg> epg) {
        this.epg = epg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Boolean getAuthFlow() {
        return authFlow;
    }

    public void setAuthFlow(Boolean authFlow) {
        this.authFlow = authFlow;
    }

    public String getMediaPlaybackId() {
        return mediaPlaybackId;
    }

    public void setMediaPlaybackId(String mediaPlaybackId) {
        this.mediaPlaybackId = mediaPlaybackId;
    }

    public String getMediaState() {
        return mediaState;
    }

    public void setMediaState(String mediaState) {
        this.mediaState = mediaState;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }


    public List<Playback> getPlaybacks() {
        return playbacks;
    }

    public void setPlaybacks(List<Playback> playbacks) {
        this.playbacks = playbacks;
    }

}