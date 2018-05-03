package com.knowme.knowme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.knowme.knowme.model.ManagmentDatabase;

/**
 * Created by coredeveloper on 5/3/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KnowMe.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ManagmentDatabase.UserTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(ManagmentDatabase.UserTable.DELETE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        super.onDowngrade(db,oldVersion,newVersion);
    }
}
