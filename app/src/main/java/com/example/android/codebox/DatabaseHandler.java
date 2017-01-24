package com.example.android.codebox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagnik on 29/12/16.
 * DATABASE HANDLER FOR HANDLING THE ENTERED RECORDS
 *
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "VAULT.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ UtilityContract.NewRecordInfo.TABLE_NAME +" ("+ UtilityContract.NewRecordInfo.WEBSITE_NAME+" TEXT, "+
                    UtilityContract.NewRecordInfo.USER_NAME+" TEXT, "+ UtilityContract.NewRecordInfo.PASSWORD+" TEXT);";

    Context context;
    DatabaseHandler dbHandler;
    SQLiteDatabase sqLiteDatabase;


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "DB CREATED OR OPENED...");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");
    }


    public void addRecords(String website, String username, String password, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilityContract.NewRecordInfo.WEBSITE_NAME,website);
        contentValues.put(UtilityContract.NewRecordInfo.USER_NAME,username);
        contentValues.put(UtilityContract.NewRecordInfo.PASSWORD,password);

        db.insert(UtilityContract.NewRecordInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...");

        //list the records in the MainActivity

    }

    public void deleteRecords(SQLiteDatabase db){

        db.delete(UtilityContract.NewRecordInfo.TABLE_NAME,null,null);
    }

    public Cursor getRecords(SQLiteDatabase db){

        Cursor cursor;

        String [] projections = {UtilityContract.NewRecordInfo.WEBSITE_NAME,
                UtilityContract.NewRecordInfo.USER_NAME,
                UtilityContract.NewRecordInfo.PASSWORD };

        cursor = db.query(UtilityContract.NewRecordInfo.TABLE_NAME, projections, null,null,null,null,null);
        return cursor;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}