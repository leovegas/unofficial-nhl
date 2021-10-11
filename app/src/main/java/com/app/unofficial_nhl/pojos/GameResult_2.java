package com.app.unofficial_nhl.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GameResult_2 {




        @SerializedName("editorial")
        @Expose
        private Editorial editorial;

        public Editorial getEditorial() {
            return editorial;
        }

        public void setEditorial(Editorial editorial) {
            this.editorial = editorial;
        }

    }
