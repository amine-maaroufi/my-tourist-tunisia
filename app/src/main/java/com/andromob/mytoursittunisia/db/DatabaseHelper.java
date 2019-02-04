package com.andromob.mytoursittunisia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.andromob.mytoursittunisia.pojo.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static String DATABASE_NAME = "contact_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "CONTACT";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TEL = "tel";

    /*CREATE TABLE CONTACT ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT, "+ KEY_TEL + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(db);
    }

    public long addContactDetail(String name, String tel) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_TEL, tel);
        // insert row in contacts table
        long insert = db.insert(TABLE_NAME, null, values);

        return insert;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                contact.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                contact.setTel(c.getString(c.getColumnIndex(KEY_TEL)));
                // adding to Students list
                contactArrayList.add(contact);
            } while (c.moveToNext());
        }
        return contactArrayList;
    }

    public int updateContact(int id, String name, String tel) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_TEL, tel);
        // update row in students table base on students.is value
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteContact(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
