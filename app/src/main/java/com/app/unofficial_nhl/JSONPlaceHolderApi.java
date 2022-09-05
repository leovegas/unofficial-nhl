package com.app.unofficial_nhl;

import com.app.unofficial_nhl.pojos.*;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/api/v1/teams")
    Call<Teams> getAllTeams();

    @GET("/api/v1/teams/{id}")
    Call<Teams> getTeamInfo(@Path("id") int id);

    @GET("/api/v1/teams/{id}?expand=team.roster")
    Call<Teams> getRosterOfTeam(@Path("id") int id);

    @GET("/api/v1/teams/{teamid}?hydrate=coaches")
    Call<TeamsCoach> getCoach(@Path("teamid") int id);

    @GET("api/v1/standings")
    Call<Teams> getStandings();

    @GET("/api/v1/game/{gameid}/boxscore")
    Call<GameStats> getGameStats(@Path("gameid") int gameid);

    @GET("/api/v1/people/{playerid}/stats?stats=regularSeasonStatRankings&season=20222023")
    Call<PlayerRank> getPlayerRanks(@Path("playerid") int playerid);

    @GET("api/v1/people/{playerid}/stats?stats=statsSingleSeason&season=20222023")
    Call<PlayerStat> getPlayerStats(@Path("playerid") int playerid);

    @GET("/api/v1/schedule?season=20222023")
    Call<Teams> getAllGamesForTeam(@Query("teamId") int teamid);

    @GET("/api/v1/teams/{id}/stats")
    Call<Stat_> getTeamStats(@Path("id") int id);

    @GET("/api/v1/people/{id}")
    Call<Teams> getPlayerInfoById(@Path("id") int id);

    @GET("api/v1/schedule")
    Observable<Teams> getSheduledGamesByDate2(@Query("date") String date);

    @GET("api/v1/schedule")
    Call<Teams> getSheduledGamesByDate(@Query("date") String date);

    @GET("api/v1/game/{game}/feed/live")
    Observable<GameResult> getLiveData(@Path("game") String id);

    @GET("/api/v1/game/{id}/content")
    Observable<EventContent> getEventContent(@Path("id") String id);
}