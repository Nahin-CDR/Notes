package com.nahinthecoder.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNote extends AppCompatActivity {


    EditText topic,note,date,time;
    Button addFire;
    private FirebaseDatabase db = FirebaseDatabase.getInstance(); //new

    //code for date
    DatePickerDialog picker;
    EditText dateInput;
    String userPhoneNumber;
    String mytime;



    ScrollView scrollView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().hide();


        updateprofile();

        codefor_date();

        codefor_time();

        scrollView = (ScrollView)findViewById(R.id.scrollView_showID);


    }

    private void codefor_time() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss aa");
        mytime = dateformat.format(c.getTime());
        time = (EditText)findViewById(R.id.address_id);
        time.setText(mytime);
    }


    private void codefor_date() {

        dateInput =(EditText) findViewById(R.id.date_id);
        dateInput.setInputType(InputType.TYPE_NULL);

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddNote.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateInput.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

    }

    private void updateprofile() {

        topic =(EditText)findViewById(R.id.full_name_id);
        note = (EditText)findViewById(R.id.father_name_id);

        date = (EditText)findViewById(R.id.date_id);

        addFire = (Button)findViewById(R.id.submit_button_id);


        addFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userPhoneNumberKey", Context.MODE_PRIVATE);
                userPhoneNumber = sharedPreferences.getString("userPhoneNumberKey","Unknown");
                DatabaseReference requestRf =  db.getReference(userPhoneNumber);
                String user_topic = topic.getText().toString();
                String user_note = note.getText().toString();
                String current_date =  date.getText().toString().trim();
                String current_time = mytime;
                if(!TextUtils.isEmpty(user_topic) && !TextUtils.isEmpty(user_note) && !TextUtils.isEmpty(current_date) && current_date.length()!=0)
                {

                    if(isNetworkConnected()==true){

                        AddData sendData = new AddData(user_topic,user_note,current_date,current_time);
                        requestRf.child(user_topic).setValue(sendData);
                        Toast.makeText(AddNote.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        topic.setText("");
                        note.setText("");
                        scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                        //date.setText("");
                        //time.setText("");
                    }
                    else
                    {
                        Toast.makeText( AddNote.this, "No internet !", Toast.LENGTH_SHORT ).show();
                        scrollView.setBackground(getDrawable(R.drawable.edit_text_background));
                    }

                }
                else
                {
                    Toast.makeText(AddNote.this, "You have to fill up all the fields !", Toast.LENGTH_SHORT).show();
                    scrollView.setBackground(getDrawable(R.drawable.edit_text_background));
                    //topic.setText("");
                    //note.setText("");
                    //date.setText("");
                    //time.setText("");
                }


            }
        });

    }


    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);

         return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
