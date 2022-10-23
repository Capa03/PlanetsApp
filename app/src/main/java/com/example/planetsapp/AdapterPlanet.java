package com.example.planetsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterPlanet extends RecyclerView.Adapter<AdapterPlanet.ViewHolder> {
    private final AdapterPlanetEventListener eventListener;
    public List<Planet> planetList;

    public AdapterPlanet(AdapterPlanetEventListener eventListener){
        this.eventListener = eventListener;
        this.planetList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterPlanet.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planet_row,parent,false);
        return new ViewHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlanet.ViewHolder holder, int position) {
        Planet planet = planetList.get(position);
        holder.setPlanetName(planet.getName());
        Glide.with(holder.context).load(planet.getImage()).into(holder.planetImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onPlanetClicked(planet.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.planetList.size();
    }

    public void updateList(List<Planet> planets){
        this.planetList = planets;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView planetName;
        private ImageView planetImage;
        private Context context;
        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.planetName = itemView.findViewById(R.id.textViewPlanet);
            this.planetImage = itemView.findViewById(R.id.imageViewPlanet);
            this.context = context;
        }

      public void setPlanetName(String planetName){
            this.planetName.setText(planetName);
      }

    }

    public interface AdapterPlanetEventListener{
        void onPlanetClicked(int planetId);
    }
}
