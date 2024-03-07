package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPage extends AppCompatActivity {

    EditText etx_name, etx_price, etx_fromshop;
    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        etx_name = findViewById(R.id.etx_pname);
        etx_price = findViewById(R.id.etx_price);
        etx_fromshop = findViewById(R.id.etx_fromshop);

        addbutton = findViewById(R.id.addindb);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbOperation operation = new DbOperation(AddPage.this);
                operation.add_in(etx_name.getText().toString().trim(),
                        Integer.valueOf(etx_price.getText().toString().trim()) ,
                        etx_fromshop.getText().toString().trim());
            }
        });
    }
}