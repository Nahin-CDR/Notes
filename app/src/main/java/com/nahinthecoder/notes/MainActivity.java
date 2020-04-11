package com.nahinthecoder.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public static final String NUMBER_PASS="numberPassing"; //="numberPassing"    code for number passing
    String passNumber; //code for number passing


    private CheckBox checkBox;
    private EditText numberPhone;
    private Button verify;
    boolean flag = false;
    boolean correctNumber;
    FirebaseAuth mAuth;

    private Button verifyButton;

    String identity="";
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        mAuth = FirebaseAuth.getInstance();
        checkBox = findViewById(R.id.checkbox_id);
        numberPhone = findViewById(R.id.mobile_number_ID);
        verify = findViewById(R.id.verify_button_id);




      //  verifyButton=(Button)findViewById(R.id.verify_button_id);
/**
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passNumber =numberPhone.getText().toString(); //code for number passing
                Intent intent = new Intent(getApplicationContext(),OTPActivity.class);
                intent.putExtra(NUMBER_PASS,passNumber); //passing this number to next activity
                startActivity(intent);
                overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                finish();
            }
        });
**/


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = numberPhone.getText().toString().trim();
                    if(isNetworkConnected()==true)
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences( "userPhoneNumberKey", Context.MODE_PRIVATE );
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString( "userPhoneNumberKey",mobile );
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this,OTPActivity.class);
                        intent.putExtra("Mobile",mobile);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                        finish();
                    }
                    else {
                        Toast.makeText( MainActivity.this, "No internet!", Toast.LENGTH_SHORT ).show();
                    }
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mobile = numberPhone.getText().toString().trim();
                int length = numberPhone.length();
                if (b==true && correctNumber == true){
                    flag = true;
                    verify.setEnabled(!mobile.isEmpty() && length == 11 && flag == true);
                }else {
                    verify.setEnabled(false);
                    flag = false;

                }
                flag = false;
            }
        });

        numberPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {





                //code







            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 mobile = numberPhone.getText().toString().trim();
                 int length = numberPhone.length();

                 if(length==11)
                 {
                     identity = mobile.substring( 0,3 );
                     if(identity.contains( "017" ) || identity.contains( "016" )
                             || identity.contains( "018" ) || identity.contains( "019" ) || identity.contains( "013" ) || identity.contains("015"))
                     {
                         numberPhone.setTextColor(Color.parseColor("#03A109"));
                         numberPhone.setBackground(getDrawable(R.drawable.edit_text_background_green));
                         correctNumber = true;
                         verify.setEnabled(!mobile.isEmpty() && length == 11 && flag == true);
                         //taking valid number to shared preference




                     }else
                     {
                         numberPhone.setTextColor(Color.parseColor("#FF0000"));
                         numberPhone.setBackground(getDrawable(R.drawable.edit_text_background)); // if wrong input is given by user
                         correctNumber = false;

                     }

                 }
                 else{
                     numberPhone.setTextColor(Color.parseColor("#FF0000"));
                     numberPhone.setBackground(getDrawable(R.drawable.edit_text_background)); // if wrong input is given by user
                     correctNumber = false;

                 }

                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                /*String mobile = numberPhone.getText().toString().trim();
                int length = numberPhone.length();
                verify.setEnabled(!mobile.isEmpty() && length == 11 && flag == true);*/
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        SharedPreferences sharedPreferences = getSharedPreferences("userPassWord", MODE_PRIVATE);
        if (currentUser != null && sharedPreferences.contains("userPassWord") ){
            SendUserToMainmenu();
        }




    }

    private void SendUserToMainmenu() {
        Intent intent = new Intent(MainActivity.this,Menus.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
