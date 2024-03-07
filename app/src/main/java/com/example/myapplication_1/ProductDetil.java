package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProductDetil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detil);

        String ID = getIntent().getStringExtra("Product ID");
        String Name = getIntent().getStringExtra("Product Name");
        String Price = getIntent().getStringExtra("Product Price");
        String From_Shop = getIntent().getStringExtra("Product From Shop");

        TextView tx_name = findViewById(R.id.Prod_Name2);
        TextView tx_price = findViewById(R.id.Prod_Price2);
        TextView tx_fromshop = findViewById(R.id.FromShop2);

        tx_name.setText(Name);
        tx_price.setText(Price);
        tx_fromshop.setText(From_Shop);

    }
}