package com.example.drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    Thread timer;
    FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        auth=FirebaseAuth.getInstance().getCurrentUser();

        //Method to launch the AIRBNB loading json
        timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(1000);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                  //  if (auth==null){
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
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