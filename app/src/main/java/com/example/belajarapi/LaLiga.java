package com.example.belajarapi;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaLiga extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar pbLoading;
    TeamAdapter teamAdapter;
    List<Team> teamList = new ArrayList<>();
    SpainLeagueInterface api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // pakai layout yang benar!

        pbLoading = findViewById(R.id.pbLoading);
        recyclerView = findViewById(R.id.rvTeam);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamAdapter = new TeamAdapter(this, teamList);
        recyclerView.setAdapter(teamAdapter);

        api = ApiClient.getClient().create(SpainLeagueInterface.class);
        fetchTeams();
    }

    private void fetchTeams() {
        pbLoading.setVisibility(View.VISIBLE);    // tampilkan loading
        recyclerView.setVisibility(View.GONE);    // sembunyikan list

        Call<TeamResponse> call = api.getAllTeams();
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                pbLoading.setVisibility(View.GONE);   // sembunyikan loading
                if (response.isSuccessful() && response.body() != null) {
                    teamList.clear();
                    teamList.addAll(response.body().getTeams());
                    teamAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);  // munculkan list
                } else {
                    Toast.makeText(LaLiga.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);  // sembunyikan loading
                Toast.makeText(LaLiga.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
