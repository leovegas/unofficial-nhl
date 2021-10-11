package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

@SerializedName("@type")
@Expose
private String type;
@SerializedName("@value")
@Expose
private String value;
@SerializedName("@displayName")
@Expose
private String displayName;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

public String getDisplayName() {
return displayName;
}

public void setDisplayName(String displayName) {
this.displayName = displayName;
}

}