package com.app.unofficial_nhl.pojos;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class StatPlayerStat {

    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("splits")
    @Expose
    private List<SplitPlayerStat> splits = null;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<SplitPlayerStat> getSplits() {
        return splits;
    }

    public void setSplits(List<SplitPlayerStat> splits) {
        this.splits = splits;
    }
}
