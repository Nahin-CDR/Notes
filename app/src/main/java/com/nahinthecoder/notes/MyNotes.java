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
    ScrollView getPassWord_layout;
    Button button_for_go_to_view_notes;


    LinearLayout layout_for_showing_notes;
    LinearLayout noRecordFound;

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private int lastPosition = -1;

    private ValueEventListener eventListener;
    String stored_PassWord,inputPassWord,userPhoneNumber;
    EditText myInputPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        getSupportActionBar().hide();

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        listView = (ListView)findViewById( R.id.list_itemViewID );
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
                    view.setBackground(getDrawable(R.drawable.addnotes_ui));
                }
                Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.move:R.anim.no_animation); //animation for chatting is > "chatting"
                view.startAnimation(animation);
                lastPosition = position;
                return view;
            }
        };
        listView.setAdapter( arrayAdapter );

        layout_for_showing_notes = (LinearLayout) findViewById(R.id.showNotesLayoutID);

        linearLayout_loading_layout = (LinearLayout)findViewById(R.id.loading_layoutID);
        noRecordFound = (LinearLayout)findViewById(R.id.noDataFound_layoutID);
        getPassWord_layout = (ScrollView)findViewById( R.id.getPassWord_linear_layoutID);
       // getPassWord_layout.setVisibility( View.VISIBLE );



        getPassWord_layout.setVisibility( View.GONE );
        linearLayout_loading_layout.setVisibility(View.VISIBLE);
        layout_for_showing_notes.setVisibility( View.VISIBLE );
        showNotes();


        /** inactive codes **/

        myInputPassword = (EditText)findViewById(R.id.myPasswordID);
        button_for_go_to_view_notes = (Button)findViewById( R.id.get_PassWord_button_id);
        button_for_go_to_view_notes.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("userPassWord", Context.MODE_PRIVATE);
                stored_PassWord = sharedPreferences.getString("userPassWord","Unknown");
                inputPassWord = myInputPassword.getText().toString().trim();
                if(inputPassWord.equals(stored_PassWord))
                {
                    if(isNetworkConnected()==true)
                    {
                        /** code for hide keyboard after button press started**/
                        InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getPassWord_layout.getWindowToken(), 0);
                        /** code for hide keyboard after button press ended**/
                        linearLayout_loading_layout.setVisibility(View.VISIBLE);
                        getPassWord_layout.setVisibility( View.GONE );
                        layout_for_showing_notes.setVisibility( View.VISIBLE );
                        showNotes();

                    }
                    else
                    {
                        Toast.makeText( MyNotes.this, "No Internet !", Toast.LENGTH_SHORT ).show();
                    }
                }
                else
                {
                    myInputPassword.setTextColor(Color.parseColor("#FF0000"));
                    myInputPassword.setBackground(getDrawable(R.drawable.edit_text_background));
                    Toast.makeText(MyNotes.this, "Wrong Password !", Toast.LENGTH_SHORT).show();

                }
            }

        } );

        /** inactive codes **/




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
