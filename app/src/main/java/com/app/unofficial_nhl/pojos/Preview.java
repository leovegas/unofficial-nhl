package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preview {

@SerializedName("title")
@Expose
private String title;
@SerializedName("topicList")
@Expose
private String topicList;
@SerializedName("items")
@Expose
private List<Object> items = null;

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

public List<Object> getItems() {
return items;
}

public void setItems(List<Object> items) {
this.items = items;
}

}