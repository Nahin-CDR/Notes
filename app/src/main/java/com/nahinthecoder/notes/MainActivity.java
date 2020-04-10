package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    FirebaseAuth mAuth;

    private Button verifyButton;
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
                    Intent intent = new Intent(MainActivity.this,OTPActivity.class);
                    intent.putExtra("Mobile",mobile);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                    finish();  }else {
                    Toast.makeText( MainActivity.this, "No internet!", Toast.LENGTH_SHORT ).show();
                }

            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String mobile = numberPhone.getText().toString().trim();
                int length = numberPhone.length();
                if (b){
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

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String mobile = numberPhone.getText().toString().trim();
                int length = numberPhone.length();
                if (checkBox.isChecked()){
                    checkBox.setChecked(false);
                }
                verify.setEnabled(!mobile.isEmpty() && length == 11 && flag == true);
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

        if (currentUser != null){
            SendUserToMainmenu();
        }
    }

    private void SendUserToMainmenu() {
        Intent intent = new Intent(MainActivity.this,Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
