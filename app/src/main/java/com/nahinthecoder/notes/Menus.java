package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Menus extends AppCompatActivity {

    ImageButton addNoteButton,myNotesButton,searchButton,deleteButton;
    LinearLayout passWordLayout;
    LinearLayout menusLayout;
    TextView id;
    String userName,stored_PassWord;
    EditText inputPassWord;
    Button login;
    String myPassWord;
    Button googlePlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        getSupportActionBar().hide();




       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED); // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Make to run your application only in LANDSCAPE mode
      //  setContentView(R.layout.disable_android_orientation_change);


      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);



        passWordLayout = (LinearLayout)findViewById(R.id.inputPasswordLinearLayoutID);
        passWordLayout.setVisibility(View.VISIBLE);
        id = (TextView) findViewById(R.id.nameID);
        SharedPreferences sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("userName","Unknown");
        id.setText(userName);

        inputPassWord = (EditText)findViewById(R.id.passwordID);
        SharedPreferences sharedPreferences1 = getSharedPreferences("userPassWord", Context.MODE_PRIVATE);
        stored_PassWord = sharedPreferences1.getString("userPassWord","Unknown");


        googlePlay = (Button)findViewById(R.id.googlePlayButtonID);
        googlePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkConnected()==true)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(
                            "https://play.google.com/store/apps/developer?id=Nahin+thE+Coder"));
                    intent.setPackage("com.android.vending");
                    startActivity(intent);
                    // Intent intent = new Intent(getApplicationContext(),SignIn.class);
                    // startActivity(intent);
                    overridePendingTransition(R.anim.myanim2,R.anim.slider_2);
                }
                else
                {
                    Toast.makeText(Menus.this, "No Internet !", Toast.LENGTH_SHORT).show();
                }

            }
        });






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

                    /** code for hiding keyboard after taking input and going another activity **/
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(inputPassWord.getWindowToken(), 0);

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


           addNoteButton = (ImageButton)findViewById(R.id.addNoteID);
           addNoteButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (isNetworkConnected()==true)
                   {
                       Intent intent = new Intent(getApplicationContext(),AddNote.class);
                       startActivity(intent);
                       overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                   }
                   else
                   {
                       Toast.makeText(Menus.this, "No Internet !", Toast.LENGTH_SHORT).show();
                   }


               }
           });

           myNotesButton = (ImageButton)findViewById(R.id.myNotesID);
           myNotesButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (isNetworkConnected()==true)
                   {
                       Intent intent = new Intent(getApplicationContext(),Themes.class);
                       startActivity(intent);
                       overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                   }
                   else
                   {
                       Toast.makeText(Menus.this, "No Internet !", Toast.LENGTH_SHORT).show();
                   }

               }
           });


           searchButton = (ImageButton)findViewById(R.id.searchID);
           searchButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(isNetworkConnected()==true)
                   {
                       Intent intent = new Intent(getApplicationContext(),Search.class);
                       startActivity(intent);
                       overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                   }
                   else
                   {
                       Toast.makeText(Menus.this, "No Internet !", Toast.LENGTH_SHORT).show();
                   }

               }
           });

           deleteButton = (ImageButton)findViewById(R.id.deleteID);
           deleteButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (isNetworkConnected()==true)
                   {
                       Intent intent = new Intent(getApplicationContext(),Delete.class);
                       startActivity(intent);
                       overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                   }
                   else
                   {
                       Toast.makeText(Menus.this, "No Internet !", Toast.LENGTH_SHORT).show();
                   }

               }
           });
    }





    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MyNotes.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
