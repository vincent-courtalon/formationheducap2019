package com.example.mycontactapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.mycontactapplication.metier.Contact;
import com.example.mycontactapplication.util.ContactDAO;
import com.example.mycontactapplication.util.ContactListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactListView;
    private ContactListAdapter contactAdapter;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactDAO = new ContactDAO(getApplicationContext());
        contactListView = findViewById(R.id.contacts);
        contactAdapter = new ContactListAdapter(this,new ArrayList<Contact>());
        contactListView.setAdapter(contactAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view,
                                    int position,
                                    long id) {
                Contact contact = contactAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,
                                            ContactFormActivity.class);
                intent.putExtra("contact_id", contact.getId());
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.i("cycle de vie", "onResume de MainActivty");
        contactAdapter.clear();
        contactAdapter.addAll(contactDAO.findAll());
        /*contactAdapter.addAll(new Contact[] {
                new Contact(1, "Stark", "tony", "ironman@angel.com"),
                new Contact(2, "Rogers", "steve", "captain@50forever.com"),
                new Contact(3, "Roumanov", "natacha", "twilightfan@gmail.com")
        });*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create_contact) {
            Intent intent = new Intent(this, ContactFormActivity.class);
            intent.putExtra("contact_id", 0);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
