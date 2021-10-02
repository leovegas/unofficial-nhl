package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class About {

    @SerializedName("eventIdx")
    @Expose
    private Integer eventIdx;
    @SerializedName("eventId")
    @Expose
    private Integer eventId;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("periodType")
    @Expose
    private String periodType;
    @SerializedName("ordinalNum")
    @Expose
    private String ordinalNum;
    @SerializedName("periodTime")
    @Expose
    private String periodTime;
    @SerializedName("periodTimeRemaining")
    @Expose
    private String periodTimeRemaining;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("goals")
    @Expose
    private Goals goals;

    public Integer getEventIdx() {
        return eventIdx;
    }

    public void setEventIdx(Integer eventIdx) {
        this.eventIdx = eventIdx;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public String getOrdinalNum() {
        return ordinalNum;
    }

    public void setOrdinalNum(String ordinalNum) {
        this.ordinalNum = ordinalNum;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String periodTime) {
        this.periodTime = periodTime;
    }

    public String getPeriodTimeRemaining() {
        return periodTimeRemaining;
    }

    public void setPeriodTimeRemaining(String periodTimeRemaining) {
        this.periodTimeRemaining = periodTimeRemaining;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Goals getGoals() {
        return goals;
    }

    public void setGoals(Goals goals) {
        this.goals = goals;
    }

}
