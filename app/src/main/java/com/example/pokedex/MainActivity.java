package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex.adapter.PokemonAdaptador;
import com.example.pokedex.model.Pokemon;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pokemon> mipokemon = new ArrayList<Pokemon>();
    RecyclerView recyclerVieww;
    Pokemon pokemonclass;
    PokemonAdaptador Pokemonadaptador;
    String url = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=10000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerVieww = findViewById(R.id.recyclewview);
        recyclerVieww.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerVieww.setLayoutManager(layoutManager);

        getPokemon();
    }
    public void getPokemon()
    {

        JsonObjectRequest aplicacion = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int p=0; p<jsonArray.length(); p++){
                        JSONObject jsonObject = jsonArray.getJSONObject(p);
                        Pokemon pokemon = gson.fromJson(String.valueOf(jsonObject), Pokemon.class);
                        mipokemon.add(pokemon);
                    }
                    Pokemonadaptador = new PokemonAdaptador(getApplicationContext(), mipokemon);
                    recyclerVieww.setAdapter(Pokemonadaptador);



                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });

        /*RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(aplicacion);*/

        Singleton.getInstance(this).addToRequestQueue(aplicacion);

    }
}