package com.app.unofficial_nhl.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Highlights {

@SerializedName("gameCenter")
@Expose
private GameCenter gameCenter;

public GameCenter getGameCenter() {
return gameCenter;
}

public void setGameCenter(GameCenter gameCenter) {
this.gameCenter = gameCenter;
}

}