package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Editorial {

@SerializedName("preview")
@Expose
private Preview preview;
@SerializedName("articles")
@Expose
private Articles articles;
@SerializedName("recap")
@Expose
private Recap recap;

public Preview getPreview() {
return preview;
}

public void setPreview(Preview preview) {
this.preview = preview;
}

public Articles getArticles() {
return articles;
}

public void setArticles(Articles articles) {
this.articles = articles;
}

public Recap getRecap() {
return recap;
}

public void setRecap(Recap recap) {
this.recap = recap;
}

}