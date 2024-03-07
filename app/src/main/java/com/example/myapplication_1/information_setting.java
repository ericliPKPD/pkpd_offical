package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class information_setting extends AppCompatActivity {

    Button back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        // initialize variable (backward button)
        back_btn = findViewById(R.id.cancel_button);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new  Intent(information_setting.this, setting_activity.class);
                startActivity(settings);
            }
        });
    }
}