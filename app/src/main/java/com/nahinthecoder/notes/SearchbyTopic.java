package com.nahinthecoder.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
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
import java.util.Objects;

public class SearchbyTopic extends AppCompatActivity {
    TextView search;
    EditText myInput;
    DatePickerDialog picker;
    TextView eText;
    LinearLayout linearLayout_loading;
    String  userPhoneNumber;
    TextView displayView;
    private ValueEventListener eventListener;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    LinearLayout nodata;

    LinearLayout displayBackgroundLayout;




    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private int lastPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchby_topic);
        Objects.requireNonNull(getSupportActionBar()).hide();





        /**
         if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
         } **/

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







        search();

        nodata = (LinearLayout) findViewById( R.id.noDataFoundID );
        linearLayout_loading=findViewById( R.id.loading_layoutID );
        displayBackgroundLayout = (LinearLayout)findViewById(R.id.myDisplayID);





    }

    private void search() {

        search = (TextView)findViewById(R.id.search_button_id);
        myInput = (EditText) findViewById(R.id.date);
        //displayView =(TextView)findViewById(R.id.display_id);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myInput.getText().toString().length() != 0)
                {
                    if(isNetworkConnected())
                    {
                        displayBackgroundLayout.setBackground(getDrawable(R.drawable.addnotes_ui));
                        listView.setVisibility(View.GONE);
                        nodata.setVisibility(View.GONE);
                        linearLayout_loading.setVisibility( View.VISIBLE );
                        showData();
                    }else
                    {
                        Toast.makeText( SearchbyTopic.this, "No Internet !", Toast.LENGTH_SHORT ).show();
                        displayBackgroundLayout.setBackground(getDrawable(R.drawable.edit_text_background));
                    }
                }
                else
                {

                    displayBackgroundLayout.setBackground(getDrawable(R.drawable.edit_text_background));
                    Toast.makeText( SearchbyTopic.this, "You have to enter a topic first !", Toast.LENGTH_SHORT ).show();
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
            eventListener = requestRf.orderByChild("topic").equalTo(sv).addValueEventListener(new ValueEventListener() {



                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {






                    String data;
                    if (dataSnapshot.exists()){

                        linearLayout_loading.setVisibility( View.GONE );
                        nodata.setVisibility( View.GONE );
                        listView.setVisibility(View.VISIBLE);



                        for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()){
                            AddData addData = itemSnapshot.getValue(AddData.class);
                            assert addData != null;
                            String mydate = addData.getDate();
                            String mytime = addData.getTime();
                            String mytopic = addData.getTopic();
                            String mynote = addData.getNote();

                            data = "\n"+mydate+"     "+mytime+"\n"+mytopic+"\n\n"+mynote+"\n";
                            arrayList.add( data );
                            arrayAdapter.notifyDataSetChanged();
                        }

                        //     linearLayout_loading.setVisibility( View.GONE );
                        //   displayView.setText(data);
                    }
                    else {
                        listView.setVisibility(View.GONE);
                        nodata.setVisibility( View.VISIBLE );
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

        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
