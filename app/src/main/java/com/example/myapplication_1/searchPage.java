package com.example.myapplication_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public  class searchPage extends AppCompatActivity {
    Datamangaer databasemangaer;
            ListView lvContact;
            DatabaseAdapter helper;
            SimpleCursorAdapter Adapter;
            String search_key="NAME";
            int i=0;
            int userChoice;

        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.search_listview);
                databasemangaer = new Datamangaer(this);
                helper = new DatabaseAdapter(this);
                lvContact = findViewById(R.id.listView);
                Adapter = databasemangaer.populateListViewFromDB();
                lvContact.setAdapter(Adapter);

                }


        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.searchpage_menu, menu);
                MenuItem  search = menu.findItem(R.id.search);
                SearchView searchView = (SearchView) search.getActionView();
                searchView.setQueryHint("Search name here");



                MenuItem home_page = menu.findItem(R.id.cancel_back2);
                MenuItem add_page = menu.findItem(R.id.add_data);
                MenuItem sorting = menu.findItem(R.id.fun_sorting);
                MenuItem filter = menu.findItem(R.id.filter);

        home_page.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                        Intent intent = new Intent();
                        intent.setClass(searchPage.this, activity_main.class);
                        startActivity(intent);
                        Toast.makeText(searchPage.this, "Welcome back to home page", Toast.LENGTH_SHORT).show();
                        return false;
                }
        });
        add_page.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                        Intent intent = new Intent();
                        intent.setClass(searchPage.this, search_addpage.class);
                        startActivity(intent);
                        Toast.makeText(searchPage.this, "Here is the Add page", Toast.LENGTH_SHORT).show();
                        return false;
                                }
                        });

        sorting.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                        SQLiteDatabase DB = helper.getWritableDatabase();
                        Cursor cursor = null;
                        String querySql= null;
                        if(i==0) {
                                querySql = "SELECT * FROM " + DatabaseAdapter.TABLE_NAME + " ORDER BY NAME ASC";
                                i=1;
                                Toast.makeText(searchPage.this, "Ascending order", Toast.LENGTH_SHORT).show();
                        }
                        else {
                                querySql = "SELECT * FROM " + DatabaseAdapter.TABLE_NAME + " ORDER BY NAME DESC";
                                Toast.makeText(searchPage.this, "Descending order", Toast.LENGTH_SHORT).show();
                                i=0;
                        }
                        cursor = DB.rawQuery(querySql, null);
                        cursor.moveToFirst();
                        Adapter.changeCursor(cursor);
                        return false;
                                }
                        });

        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                final String[] items = { "Product name","Shop","Qutoe","Price" };
                        userChoice = -1;
                        AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(searchPage.this);
                        singleChoiceDialog.setTitle("Choose thee search item");
                        singleChoiceDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                @Override
                        public void onClick(DialogInterface dialog, int which) {
                                userChoice = which;
                                        }
                                });
                        singleChoiceDialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                @Override
                        public void onClick(DialogInterface dialog, int which) {
                                String key;
                                if (userChoice != -1) {
                                        Toast.makeText(searchPage.this,
                                        "You choose " + items[userChoice], Toast.LENGTH_SHORT).show();
                                }
                                else{
                                        userChoice=0;
                                }

                                switch (items[userChoice]){
                                        case "Shop":
                                                key= "FROM_SHOP";
                                                break;
                                        case "Product name":
                                                key= "NAME";
                                                break;
                                        default:
                                                key=items[userChoice];
                                                }
                                search_key = key;
                                }

                        });
                        singleChoiceDialog.show();
                        return false;
                        }
                        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(final String mS) {

                        Toast.makeText(searchPage.this, "Searched", Toast.LENGTH_SHORT).show();
                        return false;
                        }

                @Override
                public boolean onQueryTextChange(String s) {
                        Cursor cursor = queryData(s,search_key);
                        if (Adapter== null) {
                        Adapter=databasemangaer.populateListViewFromDB();
                        } else {
                        Adapter.changeCursor(cursor);
                        }

                        return false;
                        }
        });

                lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // Move the cursor to the position of the clicked item
                                Cursor cursor = (Cursor) Adapter.getItem(position);
                                if (getIntent().getIntExtra("com", 0) == 1){
                                        Intent result = new Intent();
                                        result.putExtra("Product Name", cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_NAME)));
                                        result.putExtra("Product Price", cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_PRICE)));
                                        result.putExtra("Product From Shop", cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_FROMSHOP)));
                                        setResult(RESULT_OK, result);
                                        finish();
                                } else {
                                        Intent intent = new Intent(searchPage.this, ProductDetil.class);
                                        intent.putExtra("Product Name", cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_NAME)));
                                        intent.putExtra("Product Price", cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_PRICE)));
                                        intent.putExtra("Product From Shop", cursor.getString(cursor.getColumnIndex(DatabaseAdapter.COLUMN_FROMSHOP)));
                                        startActivity(intent);
                                }
                        }
                });

                        return false;
                        }
                        Cursor queryData(String key,String search_key) {
                                SQLiteDatabase DB = helper.getWritableDatabase();
                                Cursor cursor = null;
                                String querySql = "SELECT * FROM " + DatabaseAdapter.TABLE_NAME + " WHERE " + search_key + " LIKE '%" + key + "%'";
                                cursor = DB.rawQuery(querySql, null);
                                cursor.moveToFirst();
                                return cursor;
                        }

                        }



