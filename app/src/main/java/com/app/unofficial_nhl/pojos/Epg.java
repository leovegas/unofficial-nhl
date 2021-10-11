package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Epg {

@SerializedName("title")
@Expose
private String title;
@SerializedName("platform")
@Expose
private String platform;
@SerializedName("items")
@Expose
private List<Item> items = null;
@SerializedName("topicList")
@Expose
private String topicList;

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getPlatform() {
return platform;
}

public void setPlatform(String platform) {
this.platform = platform;
}

public List<Item> getItems() {
return items;
}

public void setItems(List<Item> items) {
this.items = items;
}

public String getTopicList() {
return topicList;
}

public void setTopicList(String topicList) {
this.topicList = topicList;
}

}