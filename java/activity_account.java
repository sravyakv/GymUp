package com.example.gymupdraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_account extends AppCompatActivity {
    Button beginner;
    Button intermediate;
    Button advanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_workout_videos);
        beginner = (Button)findViewById(R.id.beginner);
        intermediate = (Button)findViewById(R.id.intermediate);
        advanced = (Button)findViewById(R.id.advanced);
        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity_account.this, Beginner_workout_videos.class);
                startActivity(i);

            }
        });
    }
}