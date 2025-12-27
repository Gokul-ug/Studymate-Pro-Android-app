package com.example.studymatepro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity and finish the splash activity

                FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();

                if(currentUser==null){

                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));

                }else{

                    startActivity(new Intent(SplashActivity.this,MainActivity.class));

                }

                finish();
            }
        }, SPLASH_DELAY);


    }
}
