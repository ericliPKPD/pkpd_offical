package com.example.myapplication_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAdapter extends SQLiteOpenHelper {
    Context context;
    Cursor cursor;
    private static final String DB_NAME = "Shop_Product_info.db";
    private static final int DB_VERSION = 1;

    static final String TABLE_NAME = "Shop_the_product";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_NAME = "NAME";
    static final String COLUMN_PRICE = "PRICE";
    static final String COLUMN_FROMSHOP = "FROM_SHOP";

    static final String COLUMN_DES = "Des";
    static final String COLUMN_DIS ="Discount";

    static final String COLUMN_Stock = "Stock";
    private static final String CREAT_QUERY = "CREATE TABLE " + TABLE_NAME+
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_PRICE + " DOUBLE, "
            + COLUMN_FROMSHOP + " TEXT,"
            + COLUMN_Stock + " INTEGER,"
            + COLUMN_DES + " TEXT,"
            + COLUMN_DIS + " TEXT);";


    public DatabaseAdapter(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DatabaseAdapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void add_in(String pname, int pprice, String fromshop, int pstock, String pdes, String pdiscount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, pname);
        cv.put(COLUMN_PRICE, pprice);
        cv.put(COLUMN_FROMSHOP, fromshop);
        cv.put(COLUMN_Stock, pstock);
        if(pdes.isEmpty()){
            cv.put(COLUMN_DES, "No depiction");
        }
        else{
            cv.put(COLUMN_DES,pdes);
        }
        if(pdiscount.isEmpty()){
            cv.put(COLUMN_DIS, "No discount");
        }
        else{
            cv.put(COLUMN_DIS,pdiscount);
        }

        db.insert(TABLE_NAME, null, cv);

    }



}

