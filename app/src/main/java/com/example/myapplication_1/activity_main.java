package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.search.SearchBar;

import java.util.ArrayList;

public class activity_main extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DbOperation dbOperation;
    ArrayList<String> pid, pname, pprice, pfromshop;
    ProductAdapter productAdapter;
    Button btn1;
    Button btn2;
    SearchBar search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize variable (backward button)
        btn1 = (Button) findViewById(R.id.settings);
        btn2 = (Button) findViewById(R.id.Note);
        recyclerView = findViewById(R.id.list_item);
        add_button = findViewById(R.id.Add);
        search = findViewById(R.id.search_bar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(activity_main.this, setting_activity.class);
                startActivity(settings);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent note = new Intent(activity_main.this, note_page.class);
                startActivity(note);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_main.this, AddPage.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_main.this, searchPage.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Here is the search function", Toast.LENGTH_SHORT).show();
            }
        });





        dbOperation = new DbOperation(activity_main.this);
        pid = new ArrayList<>();
        pname = new ArrayList<>();
        pprice = new ArrayList<>();
        pfromshop = new ArrayList<>();

        getdatainarray();

        productAdapter = new ProductAdapter(activity_main.this, pid, pname, pprice, pfromshop, this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity_main.this));

    }

    void getdatainarray() {
        Cursor cursor = dbOperation.readAllData();
        while (cursor.moveToNext()) {
            pid.add(cursor.getString(0));
            pname.add(cursor.getString(1));
            pprice.add(cursor.getString(2));
            pfromshop.add(cursor.getString(3));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(activity_main.this, ProductDetil.class);

        intent.putExtra("Product ID", String.valueOf(pid.get(position)));
        intent.putExtra("Product Name", String.valueOf(pname.get(position)));
        intent.putExtra("Product Price", String.valueOf(pprice.get(position)));
        intent.putExtra("Product From Shop", String.valueOf(pfromshop.get(position)));

        startActivity(intent);

    }
}
