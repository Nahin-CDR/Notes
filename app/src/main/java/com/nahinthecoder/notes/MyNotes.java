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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MyNotes extends AppCompatActivity {

    DatabaseReference databaseReference;
    LinearLayout linearLayout_loading_layout;



    LinearLayout layout_for_showing_notes;
    LinearLayout noRecordFound;

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private int lastPosition = -1;

    private ValueEventListener eventListener;
    String userPhoneNumber;


    LinearLayout mynotesFullLayout;

    String myTheme;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        getSupportActionBar().hide();
        listView = (ListView)findViewById( R.id.list_itemViewID );

        linearLayout_loading_layout = (LinearLayout)findViewById(R.id.loading_layoutID);
        noRecordFound = (LinearLayout)findViewById(R.id.noDataFound_layoutID);
        layout_for_showing_notes = (LinearLayout) findViewById(R.id.showNotesLayoutID);
        mynotesFullLayout = (LinearLayout)findViewById(R.id.myNotesFullLayoutID);

        SharedPreferences sharedPreferences = getSharedPreferences("themeNo", Context.MODE_PRIVATE);
        myTheme = sharedPreferences.getString("themeNo","Unknown");
        title = findViewById(R.id.titleID);





         if(myTheme.equals("0"))
         {
             //when layout is full dark
             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                             view.setBackground(getDrawable(R.drawable.note_effect2));




                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect2));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.move:R.anim.no_animation); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };


             listView.setAdapter( arrayAdapter );



         }
         else if(myTheme.equals("1"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.dark_effect));
             arrayAdapter = new ArrayAdapter<String>(this,R.layout.dark_row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.dark_effect));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.dark_effect));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim:R.anim.myanim2); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }

         else if(myTheme.equals("2"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect4));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect4));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect4));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.chatting:R.anim.chatting); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }



         else if(myTheme.equals("3"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect1));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect1));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect2));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.chatting:R.anim.chatting); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }




         else if(myTheme.equals("4"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect3));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect3));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.addnotes_ui));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim:R.anim.myanim2); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }




         else if(myTheme.equals("5"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect5));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect5));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect5));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.move:R.anim.no_animation); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }



         else if(myTheme.equals("6"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect5));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect4));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect5));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.slider_1:R.anim.myanim2); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }




         else if(myTheme.equals("7"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect5));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.addnotes_ui));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect5));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim2:R.anim.slider_1); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }


         else if(myTheme.equals("8"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect6));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect6));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect6));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim2:R.anim.myanim2); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }




         else if(myTheme.equals("9"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect6));
             title.setTextColor(Color.parseColor("#000000"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));

                         view.setBackground(getDrawable(R.drawable.note_effect6));


                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.defult_button));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim:R.anim.myanim); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }


         else if(myTheme.equals("10"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect7));
             title.setTextColor(Color.parseColor("#ffffff"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect7));

                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect7));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim2:R.anim.myanim); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }




         else if(myTheme.equals("11"))
         {

             mynotesFullLayout.setBackground(getDrawable(R.drawable.note_effect7));
             title.setTextColor(Color.parseColor("#ffffff"));

             arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,arrayList){

                 @NonNull
                 @Override
                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                     View view = super.getView( position, convertView, parent );
                     if(position%2==1)
                     {
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect5));

                     }else{
                         view.setBackgroundColor(Color.parseColor("#ffffff"));
                         view.setBackground(getDrawable(R.drawable.note_effect7));
                     }
                     Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.myanim2:R.anim.myanim); //animation for chatting is > "chatting"
                     view.startAnimation(animation);
                     lastPosition = position;
                     return view;
                 }
             };
             listView.setAdapter( arrayAdapter );
         }









        linearLayout_loading_layout.setVisibility(View.VISIBLE);
        layout_for_showing_notes.setVisibility( View.VISIBLE );
        showNotes();











    }

    private void showNotes() {


        SharedPreferences sharedPreferences = getSharedPreferences("userPhoneNumberKey", Context.MODE_PRIVATE);
        userPhoneNumber = sharedPreferences.getString("userPhoneNumberKey","Unknown");
        databaseReference = FirebaseDatabase.getInstance().getReference(userPhoneNumber);


        /** code for hiding loading layout  starts**/

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                   linearLayout_loading_layout.setVisibility(View.GONE);
                   noRecordFound.setVisibility(View.GONE);

                }
                else
                {
                    linearLayout_loading_layout.setVisibility(View.GONE);
                    noRecordFound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (isNetworkConnected()!=true)
        {
            linearLayout_loading_layout.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet !", Toast.LENGTH_SHORT).show();
        }

        /** code for hiding loading layout ended **/


        databaseReference.addChildEventListener( new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                if (dataSnapshot.exists()) {

                    String value = dataSnapshot.getValue( AddData.class ).toString();
                    arrayList.add( value );
                    arrayAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }

        } );
    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MyNotes.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    
}
