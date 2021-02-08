package com.app.unofficial_nhl;

import com.app.unofficial_nhl.pojos.Roster;
import com.app.unofficial_nhl.pojos.Teams;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface JSONPlaceHolderApi {
    @GET("/api/v1/teams")
    Call<Teams> getAllTeams();
    @GET("/api/v1/teams/{id}?expand=team.roster")
    Call<Teams> getRosterOfTeam(@Path("id") int id);
    @GET("api/v1/standings")
    Call<Teams> getStandings();
    @GET("/api/v1/teams/{id}/stats")
    Call<Teams> getTeamStats(@Path("id") int id);
    @GET("/api/v1/people/{id}")
    Call<Teams> getPlayerInfoById(@Path("id") int id);
    @GET("api/v1/schedule")
    Call<Teams> getSheduledGamesByDate(@Query("date") String date);
}