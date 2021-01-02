package com.app.unofficial_nhl;

import com.app.unofficial_nhl.pojos.Teams;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JSONPlaceHolderApi {
    @GET("/api/v1/teams")
    Call<Teams> getAllTeams();
}