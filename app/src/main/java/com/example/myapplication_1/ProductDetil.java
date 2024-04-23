package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.health.connect.datatypes.units.Length;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetil extends AppCompatActivity {
    
    Button back_btn;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    DbOperation dbOperation;
    ArrayList<String> pname, pprice, pfromshop;
    ArrayList<Double> diff;
    ComparsionAdapter comparsionAdapter;
    double oriPrice;
    LineChart chart;
    int yAxish,friprice,secprice;

    Button favour;

    List<String> xValues;

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
        String i =  getIntent().getStringExtra("Search calling");
        if(i=="1") {
            secprice = Integer.parseInt(getIntent().getStringExtra("Product SecPrice"));
            friprice = Integer.parseInt(getIntent().getStringExtra("Product FriPrice"));

        }
        int price2 = Integer.parseInt(Price);

        oriPrice = Double.parseDouble(Price);

        TextView tx_name = findViewById(R.id.Prod_Name2);
        TextView tx_price = findViewById(R.id.Prod_Price2);
        TextView tx_fromshop = findViewById(R.id.FromShop2);
        TextView tx_Des = findViewById(R.id.Depiction);
        TextView tx_stock = findViewById(R.id.stock);
        TextView tx_Dis = findViewById(R.id.discount);
        chart=findViewById(R.id.lineChart);


        tx_name.setText(Name);
        tx_price.setText(Price);
        tx_fromshop.setText(From_Shop);
        if(stock==null&&des==null&&discount==null){
            tx_stock.setText("null");
            tx_Dis.setText("null");
            tx_Des.setText("null");

        }
        else{tx_stock.setText(stock);
            tx_Dis.setText(des);
            tx_Des.setText(discount);

        }

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


    //line chart
        Description description= new Description();
        description.setText("Flow of price");
        description.setPosition(150f,15f);
        chart.setDescription(description);
        chart.getAxisRight().setDrawLabels(false);
        xValues  = Arrays.asList("0","2 week ago","last week","latest");

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(3);
        xAxis.setGranularity(1f);

        int yAxish=price2+5;


        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(yAxish);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(3);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0,0));
        entries.add(new Entry(1,friprice));
        entries.add(new Entry(2,secprice));
        entries.add(new Entry(3,price2));

        LineDataSet dataSet=new LineDataSet(entries, "Price");
        dataSet.setColor(Color.RED);


        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();


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