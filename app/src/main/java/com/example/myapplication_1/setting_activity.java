package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class setting_activity extends AppCompatActivity {

    Switch night_switch;
    Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // initialize variable (backward button)
        back_btn = (Button) findViewById(R.id.cancel_button);

        // clicked
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(setting_activity.this, activity_main.class);
                startActivity(settings);
            }
        });

        // initialize variable (night mode switch)
        night_switch = findViewById(R.id.night_mode_switch);

        night_switch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (night_switch.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }
}

