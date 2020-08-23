package com.example.lacuisine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public final static String apiKey = "190df4d03db146faaa41b24beb9dcf5e";

    private ArrayList genres;
    private Spinner g;
    private EditText recherche;
    private SeekBar nombre;
    private Button boutonRecherche;
    private TextView nombreTexte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genres = new ArrayList();
        g = findViewById(R.id.g);
        recherche = findViewById(R.id.rechercheText);

        nombre = findViewById(R.id.nombreBar);
        nombre.setMax(20);
        nombreTexte = findViewById(R.id.nombreText);

        boutonRecherche = findViewById(R.id.buttonRecherche);

        nombre.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                nombreTexte.setText("résultats : "+ progressChangedValue);
            }
        });

        genres.addAll(Arrays.asList("African","American", "British", "Cajun", "Caribbean","Chinese","Eastern European","European","French","German","Greek","Indian","Irish","Italian","Japanese","Jewish","Korean","Latin", "American","Mediterranean","Mexican","Middle Eastern","Nordic","Southern","Spanish","Thai","Vietnamese"));
        ArrayAdapter adapterG = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                genres
        );

        g.setAdapter(adapterG);

        boutonRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Resultat.class);
                it.putExtra("recherche",recherche.getText().toString());
                it.putExtra("genre", g.getSelectedItem().toString());
                it.putExtra("nombre", nombre.getProgress());

                startActivity(it);
            }
        });

    }

}
