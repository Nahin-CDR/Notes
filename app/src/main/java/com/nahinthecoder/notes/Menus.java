package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Menus extends AppCompatActivity {

    LinearLayout passWordLayout;
    LinearLayout menusLayout;
    TextView id;
    String userName,stored_PassWord;
    EditText inputPassWord;
    Button login;
    String myPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        getSupportActionBar().hide();
        passWordLayout = (LinearLayout)findViewById(R.id.inputPasswordLinearLayoutID);
        passWordLayout.setVisibility(View.VISIBLE);
        id = (TextView) findViewById(R.id.nameID);
        SharedPreferences sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("userName","Unknown");
        id.setText(userName);

        inputPassWord = (EditText)findViewById(R.id.passwordID);
        SharedPreferences sharedPreferences1 = getSharedPreferences("userPassWord", Context.MODE_PRIVATE);
        stored_PassWord = sharedPreferences1.getString("userPassWord","Unknown");

        MenuLayout();

        menusLayout = (LinearLayout)findViewById(R.id.menusLayoutID);
        login = (Button)findViewById(R.id.loginID);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myPassWord = inputPassWord.getText().toString().trim();
                if(myPassWord.equals(stored_PassWord))
                {
                    passWordLayout.setVisibility(View.GONE);
                    menusLayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    inputPassWord.setTextColor(Color.parseColor("#FF0000"));
                    inputPassWord.setBackground(getDrawable(R.drawable.edit_text_background));
                    Toast.makeText(Menus.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    private void MenuLayout() {









    }
}
