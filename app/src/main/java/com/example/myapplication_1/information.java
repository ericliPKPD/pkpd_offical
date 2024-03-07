package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class information extends AppCompatActivity {

    Button back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_setting);

        // initialize variable (backward button)
        back_btn = (Button) findViewById(R.id.cancel_button);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(information.this, setting_activity.class);
                startActivity(settings);
            }
        });
    }
}