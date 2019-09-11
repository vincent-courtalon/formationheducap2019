package com.example.mytodomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FormTacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tache);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent resultIntent = new Intent();
        resultIntent.putExtra("titre",
                ((EditText)findViewById(R.id.champTitre)).getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();*/
    }

    public void saveTache(View v) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("titre",
                ((EditText)findViewById(R.id.champTitre)).getText().toString());
        resultIntent.putExtra("contexte",
                ((EditText)findViewById(R.id.champContexte)).getText().toString());
        resultIntent.putExtra("priorite",
                ((EditText)findViewById(R.id.champPriorite)).getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
