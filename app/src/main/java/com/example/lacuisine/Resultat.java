package com.example.lacuisine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class Resultat extends AppCompatActivity {
    private ListView liste;

    private String recherche;
    private String genre;
    private int nombre;

    private ArrayList<Recette> recettes;

    private RecetteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        liste = findViewById(R.id.listeView);

        recettes = new ArrayList<>();

        recherche = getIntent().getStringExtra("recherche");
        genre = getIntent().getStringExtra("genre");
        nombre = getIntent().getIntExtra("nombre",10);

        Log.e("RECETTE", "https://api.spoonacular.com/recipes/search?query="+recherche+"&cuisine="+genre+"&instructionsRequired=true&number="+nombre+"&apiKey="+MainActivity.apiKey);

        Ion.with(Resultat.this.getApplicationContext())
                .load("https://api.spoonacular.com/recipes/search?query="+recherche+"&cuisine="+genre+"&instructionsRequired=true&number="+nombre+"&apiKey="+MainActivity.apiKey)
                .setLogging("KEY", Log.DEBUG) //pour ajouter des logs sur la connexion
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject response) {
                        for (JsonElement el : response.get("results").getAsJsonArray()){
                            JsonObject objet = el.getAsJsonObject();


                            int id = Integer.parseInt(objet.get("id").toString());
                            String title = objet.get("title").getAsString();
                            int ready = Integer.parseInt(objet.get("readyInMinutes").toString());
                            int pers = Integer.parseInt(objet.get("servings").toString());
                            String image = "";
                            if(objet.get("image") != null){
                                image = objet.get("image").getAsString();
                            }

                            recettes.add(new Recette(id, title, ready, pers, image));
                        }
                        adapter = new RecetteAdapter(Resultat.this.getApplicationContext(), recettes);
                        liste.setAdapter(adapter);
                    }
                });


        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Resultat.this, RecetteVue.class);
                it.putExtra("id",adapter.getItem(position).getId());
                startActivity(it);
            }
        });
    }
}