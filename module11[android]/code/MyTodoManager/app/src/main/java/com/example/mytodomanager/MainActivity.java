package com.example.mytodomanager;

import android.content.Intent;
import android.os.Bundle;

import com.example.mytodomanager.metier.Tache;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public static final int AJOUT_TACHE_REQUEST = 1;

    private ListView tacheListe;
    private TacheListAdapter tacheData;
    private Tache tacheToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------- creation menu!!!
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ------------------------------------
        tacheListe = findViewById(R.id.listeTaches);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*tacheData = new TacheListAdapter(this,
                Arrays.asList(new Tache("faire menage",
                                        "maison",
                                        3,
                                        false)));*/
        tacheData = new TacheListAdapter(this, new ArrayList<Tache>());
        tacheListe.setAdapter(tacheData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void creerTache() {
        Intent intent = new Intent(this, FormTacheActivity.class);
        // appel notre deuxieme activité
        startActivityForResult(intent, AJOUT_TACHE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        Log.i("result", "request= " + requestCode + " result= " + resultCode);
        if (requestCode == AJOUT_TACHE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Log.i("result",
                      "ajout tache titre = " + data.getStringExtra("titre"));
                tacheToAdd = new Tache(data.getStringExtra("titre"),
                                        data.getStringExtra("contexte"),
                                        Integer.parseInt(data.getStringExtra("priorite")),
                                        false);
            }
            if (resultCode == RESULT_CANCELED) {
                Log.i("result", "ajout tache annulé");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_create) {
            Log.i("menu", "create tache");
            creerTache();
            return true;
        }
        if (id == R.id.action_clear) {
            Log.i("menu", "clear liste");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Gson gson = new Gson();
        FileInputStream fi = null;
        try {
            fi = getApplicationContext().openFileInput("taches");
            Scanner reader = new Scanner(fi);
            String tachejson = reader.nextLine();
            Log.i("taches restore", tachejson);
            reader.close();
            Tache[] taches=  gson.fromJson(tachejson, Tache[].class);
            tacheData.addAll(taches);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // on le fait a cette etape, car le contenu de la liste sauvegardé
        // a déjà été rechargé
        if (tacheToAdd != null) {
            Log.i("tache add", "ajout tache " + tacheToAdd.getTitre());
            tacheData.add(tacheToAdd);
            tacheToAdd = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gson gson = new Gson();
        ArrayList<Tache> taches = new ArrayList<>();
        for (int i = 0; i < tacheData.getCount(); i++) {
            taches.add(tacheData.getItem(i));
        }
        String tachesJson = gson.toJson(taches);
        Log.i("taches save", tachesJson);
        try {
            FileOutputStream fo = getApplicationContext().openFileOutput("taches", MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fo);
            pw.print(tachesJson);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
