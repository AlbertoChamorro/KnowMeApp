package com.knowme.knowme.model;

import android.provider.BaseColumns;

/**
 * Created by coredeveloper on 5/3/18.
 */

public class ManagmentDatabase {

    public ManagmentDatabase(){}

    /* Inner class that defines the table contents */
    public static class UserTable implements BaseColumns {

        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CREATED_DATE = "created_date";

        public static final String CREATE =
                "CREATE TABLE " + UserTable.TABLE_NAME + " (" +
                        UserTable._ID + " INTEGER PRIMARY KEY, " +
                        UserTable.COLUMN_USERNAME + " TEXT, " +
                        UserTable.COLUMN_EMAIL + " TEXT, " +
                        UserTable.COLUMN_PASSWORD + " TEXT, " +
                        UserTable.COLUMN_CREATED_DATE + " TEXT" +
                        ")";

        public static final String DELETE =
                "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;
    }
}
