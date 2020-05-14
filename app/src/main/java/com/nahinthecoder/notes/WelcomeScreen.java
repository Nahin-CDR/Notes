package com.nahinthecoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {
    private ImageView img;
    TextView text;
    TextView des;
    LinearLayout textLinear;
    LinearLayout first;

    private static int SPLASH_SCREEN_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        des = (TextView)findViewById(R.id.textID);
        textLinear = (LinearLayout)findViewById(R.id.textLinearID);
        first = (LinearLayout)findViewById(R.id.firstID);
        /** hiding title bar  and action bar starts **/

        /** hiding title bar  and action bar ends **/


        /**code for animation starts **/


        text = (TextView)findViewById(R.id.textMS);
       // img =(ImageView)findViewById(R.id.img);

        Animation myanimation1 = AnimationUtils.loadAnimation(this,R.anim.myanim);
        Animation myanimation2 = AnimationUtils.loadAnimation(this,R.anim.myanim2);


        text.startAnimation(myanimation2);
        first.startAnimation(myanimation2);
        /** Animation Code Ends **/
        textLinear.startAnimation(myanimation1);



    }

    @Override
    protected void onStart() {
        super.onStart();





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeScreen.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slider_1,R.anim.slider_2);
                finish();
            }
        },SPLASH_SCREEN_TIME_OUT);






    }
}
