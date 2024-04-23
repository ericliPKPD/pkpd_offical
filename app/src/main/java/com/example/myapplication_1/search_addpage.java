package com.example.myapplication_1;


import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class search_addpage extends AppCompatActivity {

        EditText name, price, fromshop, stock,depiction,discount;
        Button addbutton;
        Button btw_button,jump;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.search_addpage);

            name = findViewById(R.id.add_pname);
            price = findViewById(R.id.add_price);
            fromshop = findViewById(R.id.add_fromshop);
            stock = findViewById(R.id.add_stock);
            depiction = findViewById(R.id.add_depiction);
            discount = findViewById(R.id.add_discount);


            btw_button = (Button) findViewById(R.id.cancel_button);
            addbutton = findViewById(R.id.addindb2);
            jump=findViewById(R.id.updatepage);
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseAdapter operation = new DatabaseAdapter(search_addpage.this);
                    operation.add_in(name.getText().toString().trim(),
                            Integer.valueOf(price.getText().toString().trim()) ,
                            fromshop.getText().toString().trim(),
                            Integer.valueOf(stock.getText().toString().trim()),
                            depiction.getText().toString().trim(),
                            discount.getText().toString().trim());
                    name.getText().clear();
                    price.getText().clear();
                    fromshop.getText().clear();
                    stock.getText().clear();
                    depiction.getText().clear();
                    discount.getText().clear();
                    Toast.makeText(search_addpage.this, "Data is added", LENGTH_SHORT).show();
                }
            });

            btw_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent back = new Intent(search_addpage.this, searchPage.class);
                    startActivity(back);
                }
            });


            jump.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent jump = new Intent(search_addpage.this,Update_page.class);
                    startActivity(jump);
                }
            });
        }
    }

