package com.example.mytweetviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ListView mytweetListView;
    private ArrayAdapter<String> simpleAdapter;
    private MyTweetAdapater tweetAdapter;
    private int compteurTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // c'est le setContentView qui "creer" les instance des composants
        // graphiques
        setContentView(R.layout.activity_main);
        Log.i("cyclevie", "appel de onCreate sur mon activite");
        mytweetListView = findViewById(R.id.listTweet);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("cyclevie", "appel de onStart sur mon activite");
        ArrayList<Tweet> tweets = new ArrayList<>();
       /* tweets.addAll(Arrays.asList(
                new Tweet(Color.BLACK, "steve jobs", "j'ai mange une pomme"),
                new Tweet(Color.RED, "bill gates", "vive le moteur bing"),
                new Tweet(Color.BLUE, "elon musk", "Mars, j'arrive"),
                new Tweet(Color.YELLOW, "patrick etoile", "pau de meduse aujourd'hui"),
                new Tweet(Color.RED, "bill gates", "visual code sous linux!"),
                new Tweet(Color.BLACK, "steve jobs", "bien au calme maintenant"),
                new Tweet(Color.RED, "bill gates", "j'aime mon player zune")));
*/
        tweetAdapter = new MyTweetAdapater(this, new ArrayList<Tweet>());
        mytweetListView.setAdapter(tweetAdapter);
        tweetAdapter.addAll(tweets);

        // remplissage des tweets
  //      ArrayList<String> simpletweets =  new ArrayList<String>();
  //      simpletweets.addAll(Arrays.asList("hello", "bonjour", "konichiwa", "allo", "hola"));

        // fournir des chaines de caractere à une ListView
//        simpleAdapter = new ArrayAdapter<>(this,
//                                                        android.R.layout.simple_list_item_1,
//                                                        simpletweets);
//        mytweetListView.setAdapter(simpleAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("cyclevie", "appel de onResume sur mon activite");
        Gson gson = new Gson();
        tweetAdapter.clear();
        try {
            FileInputStream fp = getApplicationContext().openFileInput("tweets");
            Scanner reader = new Scanner(fp);
            String jsontweets = reader.nextLine();
            Log.i("jsonload", jsontweets);
            Tweet[] tweetArray = gson.fromJson(jsontweets, Tweet[].class);
            tweetAdapter.addAll(tweetArray);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("cyclevie", "appel de onPause sur mon activite");
        // sérialisation json de ma listview
        Gson gson = new Gson();
        // je récupere tous mes tweet affiché dans la listeView
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < tweetAdapter.getCount(); i++) {
            tweets.add(tweetAdapter.getItem(i));
        }
        String jsonTweets = gson.toJson(tweets);
        Log.i("jsonsave", jsonTweets);
        // ouverture d'un fichier dans le repertoire personnel de l'application
        // pas besoin de droits étendus
        // cela disparait a la suppression de l'application
        // ce n'est accessible que de l'application
        try {
            FileOutputStream fp = getApplicationContext()
                                    .openFileOutput("tweets", MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fp);
            pw.print(jsonTweets);
            pw.close();
        } catch (FileNotFoundException e) {
            Log.e("tweets", e.getMessage());
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("cyclevie", "appel de onStop sur mon activite");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("cyclevie", "appel de onDestroy sur mon activite");
    }

    public void ajouterTweet(View v) {
        EditText champTweet = findViewById(R.id.texteTweet);
        tweetAdapter.add(new Tweet(Color.GREEN,
                        "bob",
                        champTweet.getText().toString()));
        this.compteurTweet++;
        Log.i("compteur", "compteur = " + this.compteurTweet);
}

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("cyclevie", "chargement etat instance");
        this.compteurTweet = savedInstanceState.getInt("compteurTweet",
                0);
        Gson gson = new Gson();
        try {
            FileInputStream fi = getApplicationContext()
                                    .openFileInput("tweets");
            Scanner reader = new Scanner(fi);
            String tweetjson = reader.nextLine();
            tweetAdapter.clear();
            tweetAdapter.addAll(gson.fromJson(tweetjson, Tweet[].class));
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("cyclevie", "sauvegarde etat instance");
        outState.putInt("compteurTweet", this.compteurTweet);
        Gson gson = new Gson();
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < tweetAdapter.getCount(); i++) {
            tweets.add(tweetAdapter.getItem(i));
        }
        String tweetsjson = gson.toJson(tweets);
        try {
            FileOutputStream fp = getApplicationContext().openFileOutput("tweets", MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fp);
            pw.print(tweetsjson);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
