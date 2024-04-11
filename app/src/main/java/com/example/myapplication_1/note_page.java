package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public abstract class note_page extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    DbOperation dbOperation;
    ArrayList<String> pid, pname, pprice, pfromshop;
    ProductAdapter productAdapter;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_page);

        btn1 = (Button) findViewById(R.id.settings);
        btn2 = (Button) findViewById(R.id.Note);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(note_page.this, setting_activity.class);
                startActivity(settings);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent main_page = new Intent(note_page.this, activity_main.class);
                startActivity(main_page);
            }
        });

        dbOperation = new DbOperation(note_page.this);
        pid = new ArrayList<>();
        pname = new ArrayList<>();
        pprice = new ArrayList<>();
        pfromshop = new ArrayList<>();

        getdatainarray();

        productAdapter = new ProductAdapter(note_page.this, pid, pname, pprice, pfromshop, this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(note_page.this));

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
        Intent intent = new Intent(note_page.this, ProductDetil.class);

        intent.putExtra("Product ID", String.valueOf(pid.get(position)));
        intent.putExtra("Product Name", String.valueOf(pname.get(position)));
        intent.putExtra("Product Price", String.valueOf(pprice.get(position)));
        intent.putExtra("Product From Shop", String.valueOf(pfromshop.get(position)));

        startActivity(intent);
    }
}
