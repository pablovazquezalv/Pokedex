package com.example.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdaptador extends RecyclerView.Adapter<PokemonAdaptador.MyViewHolder> {

    Context context;
    ArrayList<Pokemon> pokemons;

    public PokemonAdaptador(Context context,ArrayList<Pokemon> pokemons)
    {
     this.context=context;
     this.pokemons=pokemons;
    }

    @NonNull
    @Override
    public PokemonAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdaptador.MyViewHolder holder, int position) {
        final Pokemon  pokemon = pokemons.get(position);
        //holder.url.setText(pokemon.getName());
        holder.name.setText(pokemon.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png")
        .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView img;

        public MyViewHolder(final View view)
        {
            super(view);
            name=view.findViewById(R.id.name);
            img=view.findViewById(R.id.imgview);
            //  previous=view.findViewById(R.id.previoustxt);
        }
    }
}
