package com.app.unofficial_nhl;

import com.app.unofficial_nhl.pojos.news.News;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApiNews {
   /* @GET("/api/v1/teams")
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
    Call<Teams> getSheduledGamesByDate(@Query("date") String date);*/
    @GET("/svc/search/v2/articlesearch.json?q=nhl&api-key=")
    Call<News> getNews();

    @GET("/svc/search/v2/articlesearch.json?q=nhl&api-key=")
        // Call<News> getNews();
    Observable<News> getNews2();
}