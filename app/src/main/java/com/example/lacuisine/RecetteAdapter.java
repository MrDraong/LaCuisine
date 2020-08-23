package com.example.lacuisine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecetteAdapter extends ArrayAdapter<Recette> {
    public RecetteAdapter(Context ct, ArrayList<Recette> items){
        super (ct,R.layout.ligne_layout ,items);
    }


    public View getView(int position, View ConvertView, ViewGroup parent){
        View row;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        row = inflater.inflate(R.layout.ligne_layout,parent,false);

        Recette recette = getItem(position);
        TextView titre = row.findViewById(R.id.ligne_texte);
        titre.setText(recette.getTitle());

        ImageView image = row.findViewById(R.id.ligne_image);
        Picasso.get().load(recette.getImage()).into(image);

        return row;
    }
}

