package com.example.planetsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlanetsService {

    @GET("planets/")
    Call<List<Planet>> getAllPlanets();

    @GET("planetDetails/{id}")
    Call<Planet> getDetailPlanet(@Path("id") int planetId);
}
