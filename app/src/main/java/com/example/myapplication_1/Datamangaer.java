package com.example.myapplication_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;

public class Datamangaer {

    DatabaseAdapter helper;
    SQLiteDatabase db;
    Context context;

    public Datamangaer(Context context) {
        helper = new DatabaseAdapter(context);
        db = helper.getWritableDatabase();
        this.context = context;
    }

    public SimpleCursorAdapter populateListViewFromDB(){
        String columns[] = {DatabaseAdapter.COLUMN_ID,DatabaseAdapter.COLUMN_NAME, DatabaseAdapter.COLUMN_PRICE,DatabaseAdapter.COLUMN_FROMSHOP,DatabaseAdapter.COLUMN_Stock};
        Cursor cursor = db.query(DatabaseAdapter.TABLE_NAME, columns,null, null,null, null, null, null);
        String[] fromFieldNames = new String[]{
                DatabaseAdapter.COLUMN_ID,DatabaseAdapter.COLUMN_NAME, DatabaseAdapter.COLUMN_PRICE,DatabaseAdapter.COLUMN_FROMSHOP,DatabaseAdapter.COLUMN_Stock
        };
        int[] toViewIDs = new int[]{R.id.item_pid,R.id.item_product, R.id.item_price,R.id.item_shop,R.id.item_stock};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,
                R.layout.single_items,
                cursor,
                fromFieldNames,
                toViewIDs
        );
        return contactAdapter;
    }

}


