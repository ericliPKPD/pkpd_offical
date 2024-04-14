package com.example.myapplication_1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class setting_activity extends AppCompatActivity {

    Switch note_switch;
    Switch night_switch;
    Button back_btn;
    Button info_btn;

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean o) {
            if (o) {
                Toast.makeText(setting_activity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(setting_activity.this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // initialize variable
        back_btn = findViewById(R.id.cancel_button);
        info_btn = findViewById(R.id.info_button);
        night_switch = findViewById(R.id.night_mode_switch);
        note_switch = findViewById(R.id.mode_switch);

        // Button for back to main page
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(setting_activity.this, activity_main.class);
                startActivity(settings);
            }
        });

        // Button for information page
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jump from setting page to home page
                Intent settings = new Intent(setting_activity.this, information.class);
                startActivity(settings);
            }
        });

        // Switch for turn on night mode
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

        // Set notification content
        NotificationCompat.Builder builder = new NotificationCompat.Builder(setting_activity.this, "Note")
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Notification has been turned ON")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        // Switch for turn on notification
        note_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(setting_activity.this, android.Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED){
                    activityResultLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        NotificationChannel notificationChannel = new NotificationChannel("Note", getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
                        notificationChannel.setDescription("Notification has been turned ON ");
                        notificationManager.createNotificationChannel(notificationChannel);
                        notificationManager.notify(10, builder.build());
                    }
                }
            }
        });
    }
}

