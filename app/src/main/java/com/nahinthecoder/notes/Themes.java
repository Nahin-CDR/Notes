package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Themes extends AppCompatActivity {
    ImageButton theme1Button,theme2Button,theme3Button,theme4Button;
    ImageButton theme5Button,theme6Button,theme7Button,theme8Button;
    ImageButton theme9Button,theme10Button,theme11Button,theme12Button;

    String themeNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        Objects.requireNonNull(getSupportActionBar()).hide();


        theme1Button = findViewById(R.id.theme1_ID);
        theme1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "0";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });


        theme2Button = findViewById(R.id.theme2_ID);
        theme2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "1";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);

                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }

            }
        });



        theme3Button = findViewById(R.id.theme3_ID);
        theme3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "2";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });

        theme4Button = findViewById(R.id.theme4_ID);
        theme4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "3";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });

        theme5Button = findViewById(R.id.theme5_ID);
        theme5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "4";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });

        theme6Button = findViewById(R.id.theme6_ID);
        theme6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "5";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });


        theme7Button = findViewById(R.id.theme7_ID);
        theme7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "6";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });


        theme8Button = findViewById(R.id.theme8_ID);
        theme8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "7";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });


        theme9Button = findViewById(R.id.theme9_ID);
        theme9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "8";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });



        theme10Button = findViewById(R.id.theme10_ID);
        theme10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "9";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });



        theme11Button = findViewById(R.id.theme11_ID);
        theme11Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "10";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }


            }
        });




        theme12Button = findViewById(R.id.theme12_ID);
        theme12Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNetworkConnected())
                {
                    themeNO = "11";
                    SharedPreferences sharedPreferences = getSharedPreferences( "themeNo", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "themeNo",themeNO );
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),MyNotes.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Themes.this, "No internet !", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MyNotes.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
