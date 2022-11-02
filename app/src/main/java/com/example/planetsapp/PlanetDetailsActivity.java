package com.example.planetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanetDetailsActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    public static void startActivity(Context context, int planetId) {
        Intent intent = new Intent(context, PlanetDetailsActivity.class);
        intent.putExtra(KEY_INDEX, planetId);
        context.startActivity(intent);
    }
    private Context context = this;
    private Planet planet;
    private TextView planetName;
    private TextView planetDescription;
    private ImageView planetImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_details);

        this.cacheViews();

        if(getIntent().getExtras() !=null){
            int planetId = getIntent().getExtras().getInt(KEY_INDEX);

            RetrofitClient.getService().getDetailPlanet(planetId).enqueue(new Callback<Planet>() {
                @Override
                public void onResponse(Call<Planet> call, Response<Planet> response) {
                    if(response.isSuccessful()){
                        planet = response.body();
                        setPlanetName(planet.getName());
                        setPlanetDescription(planet.getDescription());
                        Glide.with(context).load(planet.getImage()).into(planetImage);
                    }
                }

                @Override
                public void onFailure(Call<Planet> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private void cacheViews(){
        this.planetName = findViewById(R.id.textViewPlanetDetailName);
        this.planetDescription = findViewById(R.id.textViewPlanetDetailDescription);
        this.planetImage = findViewById(R.id.imageViewDetail);
    }

    public void setPlanetName(String planetName){
        this.planetName.setText(planetName);
    }

    public void setPlanetDescription(String planetDescription){
        this.planetDescription.setText(planetDescription);
    }
}