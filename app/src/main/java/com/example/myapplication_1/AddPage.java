package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPage extends AppCompatActivity {

    EditText etx_name, etx_price, etx_fromshop;
    Button addbutton;
    Button btw_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        etx_name = findViewById(R.id.etx_pname);
        etx_price = findViewById(R.id.etx_price);
        etx_fromshop = findViewById(R.id.etx_fromshop);

        btw_button = (Button) findViewById(R.id.cancel_button);
        addbutton = findViewById(R.id.addindb);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbOperation operation = new DbOperation(AddPage.this);
                operation.add_in(etx_name.getText().toString().trim(),
                        Integer.valueOf(etx_price.getText().toString().trim()) ,
                        etx_fromshop.getText().toString().trim());
                etx_name.getText().clear();
                etx_price.getText().clear();
                etx_fromshop.getText().clear();
                Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_LONG).show();
            }
        });

        btw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AddPage.this, activity_main.class);
                startActivity(back);
            }
        });
    }
}
