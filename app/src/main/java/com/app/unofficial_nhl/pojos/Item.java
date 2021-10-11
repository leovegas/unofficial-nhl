package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

@SerializedName("type")
@Expose
private String type;
@SerializedName("id")
@Expose
private String id;
@SerializedName("date")
@Expose
private String date;
@SerializedName("title")
@Expose
private String title;
@SerializedName("blurb")
@Expose
private String blurb;
@SerializedName("description")
@Expose
private String description;
@SerializedName("duration")
@Expose
private String duration;
@SerializedName("authFlow")
@Expose
private Boolean authFlow;
@SerializedName("mediaPlaybackId")
@Expose
private String mediaPlaybackId;
@SerializedName("mediaState")
@Expose
private String mediaState;
@SerializedName("keywords")
@Expose
private List<Keyword> keywords = null;
@SerializedName("image")
@Expose
private Image image;
@SerializedName("playbacks")
@Expose
private List<Playback> playbacks = null;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("subhead")
    @Expose
    private String subhead;
    @SerializedName("seoTitle")
    @Expose
    private String seoTitle;
    @SerializedName("seoDescription")
    @Expose
    private String seoDescription;
    @SerializedName("seoKeywords")
    @Expose
    private String seoKeywords;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("commenting")
    @Expose
    private Boolean commenting;
    @SerializedName("tagline")
    @Expose
    private String tagline;

    @SerializedName("contributor")
    @Expose
    private Contributor contributor;


    @SerializedName("approval")
    @Expose
    private String approval;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("dataURI")
    @Expose
    private String dataURI;

    @SerializedName("shareImage")
    @Expose
    private String shareImage;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("preview")
    @Expose
    private String preview;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getCommenting() {
        return commenting;
    }

    public void setCommenting(Boolean commenting) {
        this.commenting = commenting;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }


    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataURI() {
        return dataURI;
    }

    public void setDataURI(String dataURI) {
        this.dataURI = dataURI;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }



public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getBlurb() {
return blurb;
}

public void setBlurb(String blurb) {
this.blurb = blurb;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getDuration() {
return duration;
}

public void setDuration(String duration) {
this.duration = duration;
}

public Boolean getAuthFlow() {
return authFlow;
}

public void setAuthFlow(Boolean authFlow) {
this.authFlow = authFlow;
}

public String getMediaPlaybackId() {
return mediaPlaybackId;
}

public void setMediaPlaybackId(String mediaPlaybackId) {
this.mediaPlaybackId = mediaPlaybackId;
}

public String getMediaState() {
return mediaState;
}

public void setMediaState(String mediaState) {
this.mediaState = mediaState;
}

public List<Keyword> getKeywords() {
return keywords;
}

public void setKeywords(List<Keyword> keywords) {
this.keywords = keywords;
}

public Image getImage() {
return image;
}

public void setImage(Image image) {
this.image = image;
}

public List<Playback> getPlaybacks() {
return playbacks;
}

public void setPlaybacks(List<Playback> playbacks) {
this.playbacks = playbacks;
}

}