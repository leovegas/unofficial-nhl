package com.app.unofficial_nhl.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameResult_ {


        @SerializedName("highlights")
        @Expose
        private Highlights highlights;

        public Highlights getHighlights() {
            return highlights;
        }

        public void setHighlights(Highlights highlights) {
            this.highlights = highlights;
        }

    }

