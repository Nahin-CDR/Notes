package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Home extends AppCompatActivity {

    EditText userID,userPass;
    String userName,userPassWord;
    Button saveProfile;
    boolean name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

        userID = (EditText)findViewById(R.id.userNameID);
        userPass = (EditText)findViewById(R.id.passwordID);
        saveProfile = (Button)findViewById(R.id.saveProfileID);

        userID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = userID.getText().toString();

                if(userName.length()<5)
                {
                    userID.setTextColor(Color.parseColor("#FF0000"));
                    userID.setBackground(getDrawable(R.drawable.edit_text_background));
                    name = false;
                }
                else
                {
                    userID.setTextColor(Color.parseColor("#FFFFFF"));
                    userID.setBackground(getDrawable(R.drawable.edit_text_background_green));
                    name = true;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        userPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userPassWord = userPass.getText().toString().trim();
                if(userPassWord.length()<5)
                {
                    userPass.setTextColor(Color.parseColor("#FF0000"));
                    userPass.setBackground(getDrawable(R.drawable.edit_text_background));
                    password = false;
                }
                else
                {
                    userPass.setTextColor(Color.parseColor("#FFFFFF"));
                    userPass.setBackground(getDrawable(R.drawable.edit_text_background_green));
                    password = true;

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name == false && password==false)
                {
                    Toast.makeText(Home.this, "user name and password is too short !!", Toast.LENGTH_SHORT).show();
                }
                if(name==false && password==true)
                {
                    Toast.makeText(Home.this, "user name is too short !!", Toast.LENGTH_SHORT).show();
                }
                if(name==true && password==false)
                {

                    Toast.makeText(Home.this, "password is too short !!", Toast.LENGTH_SHORT).show();

                }
                if(name == true && password==true)
                {
                    Toast.makeText(Home.this, "Everything is okk", Toast.LENGTH_SHORT).show();


                    SharedPreferences sharedPreferences = getSharedPreferences( "userName", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "userName",userName );
                    editor.commit();

                    SharedPreferences sharedPreferences1 = getSharedPreferences( "userPassWord", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.putString( "userPassWord",userPassWord );
                    editor1.commit();

                    Intent intent = new Intent(getApplicationContext(),Menus.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                    finish();











                }
            }
        });






    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
        finish();
    }
}
