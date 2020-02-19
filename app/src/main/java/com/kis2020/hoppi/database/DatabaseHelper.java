package com.kis2020.hoppi.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "brand_opportunity_db";
    private static int DATABASE_VERSION = 1;

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


        db = getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(BrandOpportunityDB.CREATE_TABLE_SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        try {

            sqLiteDatabase.execSQL(BrandOpportunityDB.DROP_TABLE_SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase);

    }

    public Cursor getAllRecords(String tableName, String[] columns) {

        Cursor cursor = db.query(tableName, columns, null, null, null, null, null);

        return cursor;
    }


    public long insert(String tableName, ContentValues contentValues) {

        return db.insert(tableName, null, contentValues);
    }

    public boolean delete(String tableName, String whereCondition, String[] values) {

        return db.delete(tableName, whereCondition, values) > 0;
    }


    public boolean isExist(String tableName, String whereCondition, String[] values) {


        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE " + whereCondition, values);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }


}

