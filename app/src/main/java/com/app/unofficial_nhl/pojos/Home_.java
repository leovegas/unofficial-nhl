package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("triCode")
    @Expose
    private String triCode;
    @SerializedName("teamName")
    @Expose
    private String teamName;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("firstYearOfPlay")
    @Expose
    private String firstYearOfPlay;

    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("officialSiteUrl")
    @Expose
    private String officialSiteUrl;
    @SerializedName("franchiseId")
    @Expose
    private Integer franchiseId;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getTriCode() {
        return triCode;
    }

    public void setTriCode(String triCode) {
        this.triCode = triCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getFirstYearOfPlay() {
        return firstYearOfPlay;
    }

    public void setFirstYearOfPlay(String firstYearOfPlay) {
        this.firstYearOfPlay = firstYearOfPlay;
    }
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getOfficialSiteUrl() {
        return officialSiteUrl;
    }

    public void setOfficialSiteUrl(String officialSiteUrl) {
        this.officialSiteUrl = officialSiteUrl;
    }

    public Integer getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Integer franchiseId) {
        this.franchiseId = franchiseId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}