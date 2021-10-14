package com.app.unofficial_nhl.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class SplitPlayerStat {


        @SerializedName("season")
        @Expose
        private String season;
        @SerializedName("stat")
        @Expose
        private Stat__1 stat;

        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
        }

        public Stat__1 getStat() {
            return stat;
        }

        public void setStat(Stat__1 stat) {
            this.stat = stat;
        }

    }

