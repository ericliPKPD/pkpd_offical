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
                Intent settings = new Intent(note_page.this, activity_main.class);
                startActivity(settings);
            }
        });


    <<<<<<< Updated upstream;
        }
    }
        }

    public void onItemClick(int position) {
        Intent intent = new Intent(note_page.this, ProductDetil.class);

        intent.putExtra("Product ID", String.valueOf(pid.get(position)));
        intent.putExtra("Product Name", String.valueOf(pname.get(position)));
        intent.putExtra("Product Price", String.valueOf(pprice.get(position)));
        intent.putExtra("Product From Shop", String.valueOf(pfromshop.get(position)));

        startActivity(intent);

    }
}
=======
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(note_page.this, setting_activity.class);
                startActivity(settings);
            }
        });
    }
}
>>>>>>> Stashed changes
