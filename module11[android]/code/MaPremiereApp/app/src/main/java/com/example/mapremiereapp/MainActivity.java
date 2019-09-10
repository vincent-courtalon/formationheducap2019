package com.example.mapremiereapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // le parametre view sera utile pour des actions plus complexe
    // accedant au contenu du layout, l'element déclencheur, etc..
    public void firstMethod(View v) {

        Toast.makeText(this, "bonjour depuis bouton", Toast.LENGTH_SHORT)
             .show();
    }

    public void secondMethod(View v) {
        // je construit un nouvel intent pour demander a android
        // de lancer une autre activité
        // pour les activité "interne" a notre app, la classe suffit
        // pour selectionner l'activité visée
        Intent intent = new Intent(this, DetailsActivity.class);
        // le startActivity lance l'intent, et donc basculera vers la nouvelle
        // activité
        // il existe plein d'autre variation de startActivity, entre autre
        // si on a besoin de passer des parametres, ou recevoir des données
        // en retour
        startActivity(intent);
    }
}
