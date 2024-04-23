package com.example.myapplication_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication_1.AddPage;
import com.example.myapplication_1.DatabaseAdapter;

public class Update_page extends AppCompatActivity {

    Button back,update;

    EditText pname,pfromshop,pprice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_price);

        back = findViewById(R.id.cancel_button2);
        update = findViewById(R.id.updatedb);
        pname=findViewById(R.id.p_pname);
        pfromshop=findViewById(R.id.p_fromshop);
        pprice=findViewById(R.id.p_price);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Update_page.this, search_addpage.class);
                startActivity(back);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog =
                        new AlertDialog.Builder(Update_page.this);
                alertDialog.setTitle("Update the price");
                alertDialog.setMessage("Warning! The enter price will be updated as the latest price");
                alertDialog.setPositiveButton("set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseAdapter operation = new DatabaseAdapter(Update_page.this);
                        operation.update_price(
                                pname.getText().toString().trim(),
                                pfromshop.getText().toString().trim(),
                                Integer.valueOf(pprice.getText().toString().trim()));
                        pname.getText().clear();
                        pprice.getText().clear();
                        pfromshop.getText().clear();
                        Toast.makeText(getBaseContext(),"updated",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNeutralButton("cancel",(dialog, which) -> {
                    dialog.dismiss();
                });
                alertDialog.setCancelable(false);
                alertDialog.show();


            }
        });



    }
}