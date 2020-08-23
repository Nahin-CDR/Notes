package com.nahinthecoder.notes;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class WelcomeScreen extends AppCompatActivity {


    String userName;

    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nameText = (TextView)findViewById(R.id.welcomeNameID);

        SharedPreferences sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("userName")) {
            userName = sharedPreferences.getString("userName", "Unknown");
            nameText.setText("Welcome back " + userName + "!");

        }




    }

    @Override
    protected void onStart() {




        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                     Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                     startActivity(intent);
                     overridePendingTransition(R.anim.slider_1, R.anim.slider_2);
                     finish();


            }
        }, 4000);


    }
}
