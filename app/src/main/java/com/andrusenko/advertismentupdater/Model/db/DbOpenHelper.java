package com.andrusenko.advertismentupdater.Model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "advupd.db";
    public static String TBL_NAME = "Data";
    public static String COL_ID = "id";
    public static String COL_DOMAIN = "domain";
    public static String COL_ACTIVE = "active";
    public static String COL_STATS = "stats";
    public static String COL_LOGIN = "username";
    public static String COL_PASSWORD = "passwd";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db_mysqlite", "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table "+TBL_NAME+" ("
                + COL_ID +" integer primary key autoincrement,"
                + COL_DOMAIN + " text,"
                + COL_ACTIVE + " text,"
                + COL_STATS + " integer,"
                + COL_LOGIN + " text,"
                + COL_PASSWORD + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}