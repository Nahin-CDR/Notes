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


    EditText topic,note;
    Button addFire;
    private FirebaseDatabase db = FirebaseDatabase.getInstance(); //new
    //code for date
    DatePickerDialog picker;
    TextView dateInput,date,time;
    String userPhoneNumber;
    String mytime;
    ScrollView scrollView;
    LinearLayout dateTimeLinearLayout;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().hide();


        updateprofile();
        codefor_date();
        codefor_time();

        scrollView = (ScrollView)findViewById(R.id.scrollView_showID);
        dateTimeLinearLayout = (LinearLayout)findViewById(R.id.dateTimeLinearLayoutID);


    }

    private void codefor_time() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss aa");
        mytime = dateformat.format(c.getTime());
        time = (TextView) findViewById(R.id.address_id);
        time.setText(mytime);
    }


    private void codefor_date() {

        dateInput =(TextView) findViewById(R.id.date_id);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("d/M/yyyy");
        String mydate = dateformat.format(c.getTime());
        dateInput.setText(mydate);
    }

    private void updateprofile() {

        topic =(EditText)findViewById(R.id.topic_id);
        note = (EditText)findViewById(R.id.father_name_id);
        date = (TextView)findViewById(R.id.date_id);
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


                if(TextUtils.isEmpty(user_topic) && !TextUtils.isEmpty(user_note) && TextUtils.isEmpty(current_date) && current_date.length()==0)
                {
                    /** if note field has value and all others are empty**/

                    topic.setBackground(getDrawable(R.drawable.edit_text_background));
                    dateTimeLinearLayout.setBackground(getDrawable(R.drawable.edit_text_background));

                    note.setBackground(getDrawable(R.drawable.addnotes_ui));
                    scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                }
                if(!TextUtils.isEmpty(user_topic) && TextUtils.isEmpty(user_note) && TextUtils.isEmpty(current_date) && current_date.length()==0)
                {
                    /** if topic field has value and all others are empty**/

                    topic.setBackground(getDrawable(R.drawable.addnotes_ui));
                    dateTimeLinearLayout.setBackground(getDrawable(R.drawable.edit_text_background));
                    note.setBackground(getDrawable(R.drawable.edit_text_background));
                    scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                }

                /**
                if(!TextUtils.isEmpty(user_topic) && !TextUtils.isEmpty(user_note) && TextUtils.isEmpty(current_date) && current_date.length()==0)
                {
                    dateTimeLinearLayout.setBackground(getDrawable(R.drawable.edit_text_background));
                    scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                    topic.setBackground(getDrawable(R.drawable.addnotes_ui));
                    note.setBackground(getDrawable(R.drawable.addnotes_ui));


                }

                **/


                if(TextUtils.isEmpty(user_topic) && !TextUtils.isEmpty(user_note) && !TextUtils.isEmpty(current_date) && current_date.length()!=0)
                {
                    topic.setBackground(getDrawable(R.drawable.edit_text_background));

                    note.setBackground(getDrawable(R.drawable.addnotes_ui));
                    dateTimeLinearLayout.setBackground(getDrawable(R.drawable.addnotes_ui));
                    scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                }
                if(!TextUtils.isEmpty(user_topic) && TextUtils.isEmpty(user_note) && !TextUtils.isEmpty(current_date) && current_date.length()!=0)
                {
                    note.setBackground(getDrawable(R.drawable.edit_text_background));
                    topic.setBackground(getDrawable(R.drawable.addnotes_ui));
                    dateTimeLinearLayout.setBackground(getDrawable(R.drawable.addnotes_ui));
                    scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                }
                 if(!TextUtils.isEmpty(user_topic) && !TextUtils.isEmpty(user_note) && !TextUtils.isEmpty(current_date) && current_date.length()!=0)
                {

                    if(isNetworkConnected()==true){

                        AddData sendData = new AddData(user_topic,user_note,current_date,current_time);
                        requestRf.child(user_topic).setValue(sendData);
                        Toast.makeText(AddNote.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        topic.setText("");
                        note.setText("");

                        scrollView.setBackground(getDrawable(R.drawable.addnotes_ui));
                        topic.setBackground(getDrawable(R.drawable.addnotes_ui));
                        dateTimeLinearLayout.setBackground(getDrawable(R.drawable.addnotes_ui));
                        note.setBackground(getDrawable(R.drawable.addnotes_ui));
                        codefor_time(); //called this function again because there is a chance to change this
                        codefor_date();//called this function again because there is a chance to change this
                        //so every time date and time will be checked to update
                    }
                    else
                    {
                        Toast.makeText( AddNote.this, "No internet !", Toast.LENGTH_SHORT ).show();
                        scrollView.setBackground(getDrawable(R.drawable.edit_text_background));
                        topic.setBackground(getDrawable(R.drawable.addnotes_ui));
                        dateTimeLinearLayout.setBackground(getDrawable(R.drawable.addnotes_ui));

                    }

                }
                if (TextUtils.isEmpty(user_topic) && TextUtils.isEmpty(user_note))
                {
                    Toast.makeText(AddNote.this, "You have to fill up all the fields !", Toast.LENGTH_SHORT).show();
                    scrollView.setBackground(getDrawable(R.drawable.edit_text_background));
                    topic.setBackground(getDrawable(R.drawable.addnotes_ui));
                    dateTimeLinearLayout.setBackground(getDrawable(R.drawable.addnotes_ui));
                    note.setBackground(getDrawable(R.drawable.addnotes_ui));

                }
            }
        });

    }
    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);

         return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
