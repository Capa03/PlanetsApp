package com.example.planetsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterPlanet.AdapterPlanetEventListener {
    private AdapterPlanet adapterPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = new RecyclerView(this);
        this.adapterPlanet = new AdapterPlanet(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(this.adapterPlanet);
        recyclerView.setLayoutManager(layoutManager);

        RetrofitClient.getService().getAllPlanets().enqueue(new Callback<List<Planet>>() {
            @Override
            public void onResponse(Call<List<Planet>> call, Response<List<Planet>> response) {
                if(response.isSuccessful()){
                    adapterPlanet.updateList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Planet>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onPlanetClicked(int planetId) {
        PlanetDetailsActivity.startActivity(this,planetId);
    }

}