package com.example.belajarapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpainLeagueInterface {
    @GET("search_all_teams.php?s=Soccer&c=Spain")
    Call<TeamResponse> getAllTeams();
}
