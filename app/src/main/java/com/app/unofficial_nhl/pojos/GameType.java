package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameType {

@SerializedName("id")
@Expose
private String id;
@SerializedName("description")
@Expose
private String description;
@SerializedName("postseason")
@Expose
private Boolean postseason;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Boolean getPostseason() {
return postseason;
}

public void setPostseason(Boolean postseason) {
this.postseason = postseason;
}

}