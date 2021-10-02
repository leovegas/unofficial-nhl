package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Decisions {

    @SerializedName("firstStar")
    @Expose
    private FirstStar firstStar;
    @SerializedName("secondStar")
    @Expose
    private SecondStar secondStar;
    @SerializedName("thirdStar")
    @Expose
    private ThirdStar thirdStar;

    public FirstStar getFirstStar() {
        return firstStar;
    }

    public void setFirstStar(FirstStar firstStar) {
        this.firstStar = firstStar;
    }

    public SecondStar getSecondStar() {
        return secondStar;
    }

    public void setSecondStar(SecondStar secondStar) {
        this.secondStar = secondStar;
    }

    public ThirdStar getThirdStar() {
        return thirdStar;
    }

    public void setThirdStar(ThirdStar thirdStar) {
        this.thirdStar = thirdStar;
    }

}