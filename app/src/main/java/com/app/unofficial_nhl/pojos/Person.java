package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("primaryNumber")
    @Expose
    private String primaryNumber;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("birthCity")
    @Expose
    private String birthCity;
    @SerializedName("birthStateProvince")
    @Expose
    private String birthStateProvince;
    @SerializedName("birthCountry")
    @Expose
    private String birthCountry;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private int weight;
    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("rookie")
    @Expose
    private boolean rookie;
    @SerializedName("shootsCatches")
    @Expose
    private String shootsCatches;
    @SerializedName("rosterStatus")
    @Expose
    private String rosterStatus;
    @SerializedName("primaryPosition")
    @Expose
    private PrimaryPosition primaryPosition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryNumber() {
        return primaryNumber;
    }

    public void setPrimaryNumber(String primaryNumber) {
        this.primaryNumber = primaryNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthStateProvince() {
        return birthStateProvince;
    }

    public void setBirthStateProvince(String birthStateProvince) {
        this.birthStateProvince = birthStateProvince;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRookie() {
        return rookie;
    }

    public void setRookie(boolean rookie) {
        this.rookie = rookie;
    }

    public String getShootsCatches() {
        return shootsCatches;
    }

    public void setShootsCatches(String shootsCatches) {
        this.shootsCatches = shootsCatches;
    }

    public String getRosterStatus() {
        return rosterStatus;
    }

    public void setRosterStatus(String rosterStatus) {
        this.rosterStatus = rosterStatus;
    }

    public PrimaryPosition getPrimaryPosition() {
        return primaryPosition;
    }

    public void setPrimaryPosition(PrimaryPosition primaryPosition) {
        this.primaryPosition = primaryPosition;
    }

}