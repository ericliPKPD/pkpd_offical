package com.example.myapplication_1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbOperation extends SQLiteOpenHelper {
    Context ctx;
    private static final String DB_NAME = "Product_info.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "All_the_product";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_PRICE = "PRICE";
    private static final String COLUMN_FROMSHOP = "FROM_SHOP";
    private static final String CREAT_QUERY = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_PRICE + " DOUBLE, "
            + COLUMN_FROMSHOP + " TEXT);";


    public DbOperation(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void add_in(String pname, int pprice, String fromshop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, pname);
        cv.put(COLUMN_PRICE, pprice);
        cv.put(COLUMN_FROMSHOP, fromshop);

        db.insert(TABLE_NAME,null, cv);

    }

    Cursor readAllData(){
        String read_query = "SELECT * FROM " + TABLE_NAME + " ORDER BY PRICE";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        cursor = db.rawQuery(read_query, null);

        return cursor;
    }
}
