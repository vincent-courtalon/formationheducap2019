package com.example.mycontactapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mycontactapplication.metier.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="contacts.db";
    public static final String TABLE_NAME = "contacts";

    public ContactDAO(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // cette méthode est appelée si on tente d'ouvrir une base qi n'existe pas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " " +
                "(id integer primary key," +
                " nom text," +
                " prenom text," +
                " email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Integer deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,
                        "id = ?",
                        new String[]{Integer.toString(id)});
    }

    public boolean insertContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("nom", contact.getNom());
        content.put("prenom", contact.getPrenom());
        content.put("email", contact.getEmail());
        long id = db.insert(TABLE_NAME, null, content);
        return id != -1;
    }

    public boolean updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("nom", contact.getNom());
        content.put("prenom", contact.getPrenom());
        content.put("email", contact.getEmail());
        db.update(TABLE_NAME,
                  content,
                  "id = ?",
                  new String[] {Integer.toString(contact.getId())});
        return true;
    }

    public Contact findById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT id, nom, prenom, email FROM "+ TABLE_NAME +
                                  " WHERE id=" +id, null);
        rs.moveToFirst();
        Contact contact = null;
        if (!rs.isAfterLast()) {
            contact = new Contact(rs.getInt(0),
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3));
        }
        rs.close();
        return contact;
    }

    public List<Contact> findAll() {
        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT id, nom, prenom, email FROM " +TABLE_NAME,
                                null);
        rs.moveToFirst();
        while(!rs.isAfterLast()){
            contacts.add(new Contact(rs.getInt(0),
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3)));
            rs.moveToNext();
        }
        rs.close();
        return contacts;
    }
}
