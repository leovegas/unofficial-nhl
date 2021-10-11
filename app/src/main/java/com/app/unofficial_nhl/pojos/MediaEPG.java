package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaEPG {

    public List<Epg> getEpgs() {
        return epgs;
    }

    public void setEpgs(List<Epg> epgs) {
        this.epgs = epgs;
    }

    @SerializedName("epg")
    @Expose
    private List<Epg> epgs = null;




}