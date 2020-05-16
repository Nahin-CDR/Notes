package com.nahinthecoder.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Delete extends AppCompatActivity {
    EditText deleteText;
    TextView deleteName;
    Button del_search,confirmDelete;
    DatabaseReference databaseReference;
    LinearLayout linearLayout_loading;
    LinearLayout deleteBackGround;
    LinearLayout noDATAfound;
    String  userPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Objects.requireNonNull(getSupportActionBar()).hide();
        deleteText = findViewById(R.id.del_search_byId);
        deleteName = findViewById(R.id.del_preview_nameId);

        del_search = findViewById(R.id.del_search_button);
        confirmDelete = findViewById(R.id.delete_prdoductId);

        linearLayout_loading = (LinearLayout)findViewById( R.id.loading_layoutID );
        deleteBackGround = (LinearLayout)findViewById(R.id.deleteBackgroundID);
        noDATAfound = (LinearLayout)findViewById(R.id.noDataID);
        //getting reference from previous activity
        SharedPreferences sharedPreferences = getSharedPreferences("userPhoneNumberKey", Context.MODE_PRIVATE);
        userPhoneNumber = sharedPreferences.getString("userPhoneNumberKey","Unknown");


        databaseReference = FirebaseDatabase.getInstance().getReference(userPhoneNumber);


        //  progressDialog = new ProgressDialog(this);
        //  progressDialog.setMessage("Wait.....");

        del_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTopic= deleteText.getText().toString().trim();
                if (!inputTopic.equals(null) && inputTopic.length()!=0){
                    if(isNetworkConnected()){
                        linearLayout_loading.setVisibility( View.VISIBLE );
                        deleteBackGround.setBackground(getDrawable(R.drawable.defult_button));
                        findData();
                    }else
                    {
                        Toast.makeText( Delete.this, "No internet !", Toast.LENGTH_SHORT ).show();
                    }
                }else {

                    deleteBackGround.setBackground(getDrawable(R.drawable.edit_text_background));
                    Toast.makeText(Delete.this, "You have to enter a topic name", Toast.LENGTH_SHORT).show();
                    linearLayout_loading.setVisibility( View.GONE );
                    // progressDialog.dismiss();
                }
            }
        });

        confirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout_loading.setVisibility( View.VISIBLE );

                if (!TextUtils.isEmpty(deleteText.getText().toString())){
                    String proNames = deleteText.getText().toString();
                    deleteData(proNames);
                }else {
                    deleteText.setError("Enter name");
                    deleteName.setText("");

                    confirmDelete.setVisibility(View.GONE);
                    linearLayout_loading.setVisibility( View.GONE );
                    //  progressDialog.dismiss();
                }
            }
        });

    }




    private void deleteData(String proNames) {
        databaseReference.child(proNames).removeValue();
        Toast.makeText(this, "Topic Deleted Successfully", Toast.LENGTH_SHORT).show();
        deleteName.setText("");

        confirmDelete.setVisibility(View.GONE);
        linearLayout_loading.setVisibility( View.GONE );
        //  progressDialog.dismiss();
    }

    private void findData() {
        final String pName = deleteText.getText().toString();
        databaseReference.child(pName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    noDATAfound.setVisibility(View.GONE);
                    AddData productData = dataSnapshot.getValue(AddData.class);
                    deleteName.setText(productData.getTopic());
                    //deleteQuantity.setText(productData.getProductAvailable());
                    confirmDelete.setVisibility(View.VISIBLE);
                    linearLayout_loading.setVisibility( View.GONE );
                    //progressDialog.dismiss();
                }else {
                    Toast.makeText(Delete.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    noDATAfound.setVisibility(View.VISIBLE);
                    linearLayout_loading.setVisibility( View.GONE );
                    deleteText.setText(" ");
                    //progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Delete.this, "No Data Found", Toast.LENGTH_SHORT).show();
                noDATAfound.setVisibility(View.VISIBLE);
                linearLayout_loading.setVisibility( View.GONE );
                // progressDialog.dismiss();
            }
        });
    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);

        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
