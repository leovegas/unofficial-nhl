package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerStat {

@SerializedName("copyright")
@Expose
private String copyright;
@SerializedName("stats")
@Expose
private List<StatPlayerStat> stats = null;

public String getCopyright() {
return copyright;
}

public void setCopyright(String copyright) {
this.copyright = copyright;
}

public List<StatPlayerStat> getStats() {
return stats;
}

public void setStats(List<StatPlayerStat> stats) {
this.stats = stats;
}

}