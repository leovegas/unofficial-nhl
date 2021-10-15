package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamSkaterStats {

@SerializedName("goals")
@Expose
private Integer goals;
@SerializedName("pim")
@Expose
private Integer pim;
@SerializedName("shots")
@Expose
private Integer shots;
@SerializedName("powerPlayPercentage")
@Expose
private String powerPlayPercentage;
@SerializedName("powerPlayGoals")
@Expose
private Double powerPlayGoals;
@SerializedName("powerPlayOpportunities")
@Expose
private Double powerPlayOpportunities;
@SerializedName("faceOffWinPercentage")
@Expose
private String faceOffWinPercentage;
@SerializedName("blocked")
@Expose
private Integer blocked;
@SerializedName("takeaways")
@Expose
private Integer takeaways;
@SerializedName("giveaways")
@Expose
private Integer giveaways;
@SerializedName("hits")
@Expose
private Integer hits;

public Integer getGoals() {
return goals;
}

public void setGoals(Integer goals) {
this.goals = goals;
}

public Integer getPim() {
return pim;
}

public void setPim(Integer pim) {
this.pim = pim;
}

public Integer getShots() {
return shots;
}

public void setShots(Integer shots) {
this.shots = shots;
}

public String getPowerPlayPercentage() {
return powerPlayPercentage;
}

public void setPowerPlayPercentage(String powerPlayPercentage) {
this.powerPlayPercentage = powerPlayPercentage;
}

public Double getPowerPlayGoals() {
return powerPlayGoals;
}

public void setPowerPlayGoals(Double powerPlayGoals) {
this.powerPlayGoals = powerPlayGoals;
}

public Double getPowerPlayOpportunities() {
return powerPlayOpportunities;
}

public void setPowerPlayOpportunities(Double powerPlayOpportunities) {
this.powerPlayOpportunities = powerPlayOpportunities;
}

public String getFaceOffWinPercentage() {
return faceOffWinPercentage;
}

public void setFaceOffWinPercentage(String faceOffWinPercentage) {
this.faceOffWinPercentage = faceOffWinPercentage;
}

public Integer getBlocked() {
return blocked;
}

public void setBlocked(Integer blocked) {
this.blocked = blocked;
}

public Integer getTakeaways() {
return takeaways;
}

public void setTakeaways(Integer takeaways) {
this.takeaways = takeaways;
}

public Integer getGiveaways() {
return giveaways;
}

public void setGiveaways(Integer giveaways) {
this.giveaways = giveaways;
}

public Integer getHits() {
return hits;
}

public void setHits(Integer hits) {
this.hits = hits;
}

}