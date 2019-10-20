package com.example.kochbuch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.kochbuch.RecipeContract.RecipeEntry;

public class RecipeDBHelpter extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "recipielist.db";
    public static final int DATABASE_VERSION = 1;

    public RecipeDBHelpter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_RECIPELIST_TABLE = "CREATE TABLE " +
                RecipeEntry.TABLE_NAME + " (" +
                RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecipeEntry.COLUMN_AMOUNT + " TEXT NOT NULL, " +
                RecipeEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                RecipeEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_RECIPELIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
