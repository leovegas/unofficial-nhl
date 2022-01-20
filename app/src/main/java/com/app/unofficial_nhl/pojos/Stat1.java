package com.app.unofficial_nhl.pojos;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat1 {

    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("splits")
    @Expose
    private List<Split1> splits = null;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Split1> getSplits() {
        return splits;
    }

    public void setSplits(List<Split1> splits) {
        this.splits = splits;
    }

}