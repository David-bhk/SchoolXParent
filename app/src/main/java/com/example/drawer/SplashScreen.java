package com.example.drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    Thread timer;
    FirebaseUser auth;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView1 = findViewById(R.id.titleA);
        textView2 = findViewById(R.id.titleB);
        textView3 = findViewById(R.id.titleC);

        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView2.setTypeface(textView2.getTypeface(), Typeface.BOLD_ITALIC);
        textView3.setTypeface(textView3.getTypeface(), Typeface.ITALIC);




        auth=FirebaseAuth.getInstance().getCurrentUser();

        //Method to launch the AIRBNB loading json
        timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(4000);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                  //  if (auth==null){
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
//                    }else {
//                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                        intent.putExtra("UserID", auth.getUid());
//                        startActivity(intent);
//                        finish();
//                    }

                }
            }
        };
        timer.start();


    }
}