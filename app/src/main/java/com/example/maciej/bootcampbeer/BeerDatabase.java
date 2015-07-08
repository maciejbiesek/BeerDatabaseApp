package com.example.maciej.bootcampbeer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static com.example.maciej.bootcampbeer.DbConstants.*;

public class BeerDatabase {

    private BeerDbHelper beerDbHelper;

    public BeerDatabase(Context context) {
        this.beerDbHelper = new BeerDbHelper(context);
    }

    public void addBeer(Beer beer) {
        SQLiteDatabase db = beerDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, beer.getName());
        values.put(COLUMN_DESCRIPTION, beer.getDesctiption());
        values.put(COLUMN_PHOTO, beer.getPhoto());

        db.insert(TABLE_NAME, null, values);
    }


    public Beer getBeer(int position) {
        SQLiteDatabase db = beerDbHelper.getReadableDatabase();

        String[] projection = {
                COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PHOTO
        };


        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToPosition(position);

        String name = cursor.getString(0);
        String description = cursor.getString(1);
        String photo = cursor.getString(2);

        return new Beer(name, description, photo);
    }

    public int getBeersNumber() {
        SQLiteDatabase db =  beerDbHelper.getReadableDatabase();

        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
    }
}
