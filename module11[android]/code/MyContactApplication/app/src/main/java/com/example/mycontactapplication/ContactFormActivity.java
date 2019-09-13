package com.example.mycontactapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycontactapplication.metier.Contact;
import com.example.mycontactapplication.util.ContactDAO;

public class ContactFormActivity extends AppCompatActivity {

    private Contact edition_contact;
    private ContactDAO contactDAO;
    private EditText champPrenom;
    private EditText champNom;
    private EditText champEmail;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        Log.i("onCreate", "contactForm");
        int id = getIntent().getIntExtra("contact_id", -1);
        Log.i("onCreate", "contact id = " + id);
        deleteButton = findViewById(R.id.buttonDelete);
        contactDAO = new ContactDAO(getApplicationContext());
        if (id > 0) {
            edition_contact = contactDAO.findById(id);
        }
        else {
            deleteButton.setVisibility(View.INVISIBLE);
            edition_contact = new Contact(0, "","","");
        }
        champPrenom = findViewById(R.id.edit_prenom_contact);
        champNom = findViewById(R.id.edit_nom_contact);
        champEmail = findViewById(R.id.edit_email_contact);
    }

    @Override
    protected void onResume() {
        super.onResume();
        champPrenom.setText(edition_contact.getPrenom());
        champNom.setText(edition_contact.getNom());
        champEmail.setText(edition_contact.getEmail());
    }

    @Override
    protected void onPause() {
        super.onPause();
        edition_contact.setPrenom(champPrenom.getText().toString());
        edition_contact.setNom(champNom.getText().toString());
        edition_contact.setEmail(champEmail.getText().toString());
    }

    public void saveContact(View v) {
        edition_contact.setPrenom(champPrenom.getText().toString());
        edition_contact.setNom(champNom.getText().toString());
        edition_contact.setEmail(champEmail.getText().toString());

        if (edition_contact.getId() == 0)
            contactDAO.insertContact(edition_contact);
        else
            contactDAO.updateContact(edition_contact);

        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void deleteContact(View view) {
        if (edition_contact.getId() > 0) {
            contactDAO.deleteContact(edition_contact.getId());
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
