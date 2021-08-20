package com.example.gymupdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class acivity_plan extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button Logout;
    Button Profile;
    Button beg;
    Button inter, adv;
    Button chat;

    Animation bttone, bttwo, bttfour, bttfive, bttsix, bttseven, btteight;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        beg=findViewById(R.id.beginner);
        Logout = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        Profile =findViewById(R.id.Profile);
        inter=findViewById(R.id.intermediate);
        adv=findViewById(R.id.advanced);
        chat=findViewById(R.id.chat007);



        //Load Animation
        bttone = AnimationUtils.loadAnimation(this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        bttfive = AnimationUtils.loadAnimation(this, R.anim.bttfive);
        bttsix = AnimationUtils.loadAnimation(this, R.anim.bttsix);
        bttseven = AnimationUtils.loadAnimation(this, R.anim.bttseven);
        btteight = AnimationUtils.loadAnimation(this, R.anim.btteight);



        //assign the animation
        beg.startAnimation(bttone);
        inter.startAnimation(bttone);
        adv.startAnimation(bttone);

        Profile.startAnimation(bttwo);
        Logout.startAnimation(bttwo);
        chat.startAnimation(bttwo);


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acivity_plan.this, Profile_page.class);
                startActivity(intent);
            }
        });

        beg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acivity_plan.this, Workout_beginner.class);
                startActivity(intent);
            }
        });

        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acivity_plan.this, Workout_intermediate.class);
                startActivity(intent);
            }
        });

        adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acivity_plan.this, workout_advance.class);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(acivity_plan.this, Chat_names.class);
                startActivity(intent);
            }
        });




    }

}