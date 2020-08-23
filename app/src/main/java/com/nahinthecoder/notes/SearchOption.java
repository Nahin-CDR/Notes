package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Objects;

public class SearchOption extends AppCompatActivity {

    LinearLayout date,topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_option);
        Objects.requireNonNull(getSupportActionBar()).hide();

        date = (LinearLayout)findViewById(R.id.searchByDateID);
        topic = (LinearLayout)findViewById(R.id.searchByTopicID);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
            }
        });


        topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchbyTopic.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
        finish();
    }
}
