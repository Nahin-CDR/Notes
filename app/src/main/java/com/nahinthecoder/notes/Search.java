package com.nahinthecoder.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Search extends AppCompatActivity {

    TextView search;
    TextView myInput;
    DatePickerDialog picker;
    TextView eText;
    LinearLayout linearLayout_loading;
    String  userPhoneNumber;
    TextView displayView;
    private ValueEventListener eventListener;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    LinearLayout nodata;

    LinearLayout displayBackgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();



        search();

        nodata = (LinearLayout) findViewById( R.id.noDataFoundID );
        linearLayout_loading=findViewById( R.id.loading_layoutID );
        displayBackgroundLayout = (LinearLayout)findViewById(R.id.myDisplayID);



        eText=(TextView) findViewById(R.id.date);

        eText.setInputType(InputType.TYPE_NULL);

        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    private void search() {

        search = (TextView)findViewById(R.id.search_button_id);
        myInput = (TextView)findViewById(R.id.date);
        displayView =(TextView)findViewById(R.id.display_id);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myInput.getText().toString().length() != 0)
                {
                    if(isNetworkConnected()==true)
                    {
                        displayBackgroundLayout.setBackground(getDrawable(R.drawable.addnotes_ui));
                        linearLayout_loading.setVisibility( View.VISIBLE );
                        showData();
                    }else
                    {
                        Toast.makeText( Search.this, "No Internet !", Toast.LENGTH_SHORT ).show();
                    }
                }
                else
                {

                    displayBackgroundLayout.setBackground(getDrawable(R.drawable.edit_text_background));
                    Toast.makeText( Search.this, "You have to give a date first !", Toast.LENGTH_SHORT ).show();
                }


            }
        });


    }

    private void showData() {

        if(myInput.getText().toString().trim().length()!=0){


            myInput.setError(null); //when u entered date
            SharedPreferences sharedPreferences = getSharedPreferences("userPhoneNumberKey", Context.MODE_PRIVATE);
            userPhoneNumber = sharedPreferences.getString("userPhoneNumberKey","Unknown");

            DatabaseReference requestRf =  db.getReference(userPhoneNumber);


            String sv = myInput.getText().toString();
            eventListener = requestRf.orderByChild("date").equalTo(sv).addValueEventListener(new ValueEventListener() {



                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    String data = "";
                    if (dataSnapshot.exists()){

                        nodata.setVisibility( View.GONE );

                        for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                            AddData addData = itemSnapshot.getValue(AddData.class);
                            String mydate = addData.getDate();
                            String mytime = addData.getTime();
                            String mytopic = addData.getTopic();
                            String mynote = addData.getNote();

                            data = data+"\n"+mydate+"     "+mytime+"\n"+mytopic+"\n\n"+mynote+"\n\n\n\n\n";

                        }
                        linearLayout_loading.setVisibility( View.GONE );
                        displayView.setText(data);
                    }
                    else {
                        nodata.setVisibility( View.VISIBLE );
                        displayView.setText( " " );
                        linearLayout_loading.setVisibility( View.GONE );
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else if(myInput.getText().toString().trim().length()==0){

            myInput.setError("empty !");  //suggestion from russel vai
        }

    }



    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
