package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.health.connect.datatypes.units.Length;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductDetil extends AppCompatActivity {
    
    Button back_btn;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DbOperation dbOperation;
    ArrayList<String> pname, pprice, pfromshop;
    ArrayList<Double> diff;
    ComparsionAdapter comparsionAdapter;
    double oriPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detil);


        String Name = getIntent().getStringExtra("Product Name");
        String Price = getIntent().getStringExtra("Product Price");
        String From_Shop = getIntent().getStringExtra("Product From Shop");
        String stock= getIntent().getStringExtra("Product Stock");
        String des= getIntent().getStringExtra("Product discount");
        String discount = getIntent().getStringExtra("Product des");


        oriPrice = Double.parseDouble(Price);

        TextView tx_name = findViewById(R.id.Prod_Name2);
        TextView tx_price = findViewById(R.id.Prod_Price2);
        TextView tx_fromshop = findViewById(R.id.FromShop2);
        TextView tx_Des = findViewById(R.id.Depiction);
        TextView tx_stock = findViewById(R.id.stock);
        TextView tx_Dis = findViewById(R.id.discount);

        tx_name.setText(Name);
        tx_price.setText(Price);
        tx_fromshop.setText(From_Shop);
        tx_stock.setText(stock);
        tx_Dis.setText(des);
        tx_Des.setText(discount);

        recyclerView = findViewById(R.id.list_item);
        add_button = findViewById(R.id.Add);
        back_btn = (Button) findViewById(R.id.cancel_button);

        pname = new ArrayList<>();
        pprice = new ArrayList<>();
        pfromshop = new ArrayList<>();
        diff = new ArrayList<>();

        comparsionAdapter = new ComparsionAdapter(ProductDetil.this, pname, pprice, pfromshop, diff);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetil.this));
        recyclerView.setAdapter(comparsionAdapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ProductDetil.this, activity_main.class);
                startActivity(back);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {

            int compare = 1;

            @Override
            public void onClick(View v) {
                Intent search = new Intent(ProductDetil.this, searchPage.class);
                search.putExtra("com", 1);
                startActivityForResult(search, compare);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String Name = data.getStringExtra("Product Name");
                String Price = data.getStringExtra("Product Price");
                String Shop = data.getStringExtra("Product From Shop");

                pname.add(Name);
                pprice.add(Price);
                pfromshop.add(Shop);

                double comPrice = Double.parseDouble(Price);
                double different = comPrice - oriPrice;
                diff.add(different);

                comparsionAdapter.notifyDataSetChanged();

                Toast.makeText(ProductDetil.this, "Product added successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }
}