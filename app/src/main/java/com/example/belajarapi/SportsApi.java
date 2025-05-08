package com.example.belajarapi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface SportsApi {
    @GET("search_all_teams.php")
    Call<TeamResponse> getAllTeams(@Query("l") String league);
}
