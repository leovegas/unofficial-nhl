package com.app.unofficial_nhl.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {

@SerializedName("contributors")
@Expose
private List<Object> contributors = null;
@SerializedName("source")
@Expose
private String source;

public List<Object> getContributors() {
return contributors;
}

public void setContributors(List<Object> contributors) {
this.contributors = contributors;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

}