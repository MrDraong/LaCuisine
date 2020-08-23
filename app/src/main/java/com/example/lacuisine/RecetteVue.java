package com.example.lacuisine;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class RecetteVue extends AppCompatActivity {

    private TextView nom;
    private TextView minutes;
    private TextView description;
    private ImageView image;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette_vue);

        nom = findViewById(R.id.nomRecette);
        minutes = findViewById(R.id.minutesText);
        description = findViewById(R.id.descriptionText);
        image = findViewById(R.id.imageRecette);
        id = getIntent().getIntExtra("id",0);

        description.setMovementMethod(new ScrollingMovementMethod());
        Log.e("RECETTE-U", "https://api.spoonacular.com/recipes/"+id+"/information?apiKey="+MainActivity.apiKey);
        Ion.with(RecetteVue.this.getApplicationContext())
                .load("https://api.spoonacular.com/recipes/"+id+"/information?apiKey="+MainActivity.apiKey)
                .setLogging("KEY", Log.DEBUG) //pour ajouter des logs sur la connexion
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject response) {
                        JsonObject objet =  response.getAsJsonObject();

                        nom.setText(objet.get("title").getAsString());
                        minutes.setText(minutes.getText().toString() +" "+ objet.get("readyInMinutes").toString() + "mins");
                        description.setText(objet.get("instructions").getAsString());
                        if(objet.get("image") != null){
                            Picasso.get().load(objet.get("image").getAsString()).into(image);
                        }
                    }
                });
    }
}

