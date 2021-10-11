package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recap {

@SerializedName("title")
@Expose
private String title;
@SerializedName("topicList")
@Expose
private String topicList;
@SerializedName("items")
@Expose
private List<Item> items = null;

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getTopicList() {
return topicList;
}

public void setTopicList(String topicList) {
this.topicList = topicList;
}

public List<Item> getItems() {
return items;
}

public void setItems(List<Item> items) {
this.items = items;
}

}