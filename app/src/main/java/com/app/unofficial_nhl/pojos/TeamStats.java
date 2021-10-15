package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamStats {

@SerializedName("teamSkaterStats")
@Expose
private TeamSkaterStats teamSkaterStats;

public TeamSkaterStats getTeamSkaterStats() {
return teamSkaterStats;
}

public void setTeamSkaterStats(TeamSkaterStats teamSkaterStats) {
this.teamSkaterStats = teamSkaterStats;
}

}