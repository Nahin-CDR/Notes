package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class Themes extends AppCompatActivity {
    ImageButton theme1Button,theme2Button,theme3Button,theme4Button;
    ImageButton theme5Button,theme6Button,theme7Button,theme8Button;
    ImageButton theme9Button,theme10Button,theme11Button,theme12Button;

    String themeNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        getSupportActionBar().hide();


        theme1Button = findViewById(R.id.theme1_ID);
        theme1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "0";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });


        theme2Button = findViewById(R.id.theme2_ID);
        theme2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "1";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });



        theme3Button = findViewById(R.id.theme3_ID);
        theme3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "2";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });

        theme4Button = findViewById(R.id.theme4_ID);
        theme4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "3";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });

        theme5Button = findViewById(R.id.theme5_ID);
        theme5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "4";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });

        theme6Button = findViewById(R.id.theme6_ID);
        theme6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "5";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });


        theme7Button = findViewById(R.id.theme7_ID);
        theme7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "6";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });


        theme8Button = findViewById(R.id.theme8_ID);
        theme8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "7";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });


        theme9Button = findViewById(R.id.theme9_ID);
        theme9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "8";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });



        theme10Button = findViewById(R.id.theme10_ID);
        theme10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "9";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });



        theme11Button = findViewById(R.id.theme11_ID);
        theme11Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themeNO = "10";
                SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "themeNo",themeNO );
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                startActivity(intent);


            }
        });



























    }
}
