package com.example.recyclerview_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DbOperation dbOperation;
    ArrayList<String> pid, pname, pprice, pfromshop;
    ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_item);
        add_button = findViewById(R.id.Add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPage.class);
                startActivity(intent);
            }
        });

        dbOperation = new DbOperation(MainActivity.this);
        pid = new ArrayList<>();
        pname = new ArrayList<>();
        pprice = new ArrayList<>();
        pfromshop = new ArrayList<>();

        getdatainarray();

        productAdapter = new ProductAdapter(MainActivity.this, pid, pname, pprice, pfromshop, this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

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
        Intent intent = new Intent(MainActivity.this, ProductDetil.class);

        intent.putExtra("Product ID", String.valueOf(pid.get(position)));
        intent.putExtra("Product Name", String.valueOf(pname.get(position)));
        intent.putExtra("Product Price", String.valueOf(pprice.get(position)));
        intent.putExtra("Product From Shop", String.valueOf(pfromshop.get(position)));

        startActivity(intent);

    }
}
