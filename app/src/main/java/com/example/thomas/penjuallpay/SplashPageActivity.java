package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashPageActivity extends AppCompatActivity {
    private  static int SPLASH_TIME_OUT = 3000;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (curUser == null){
                    Intent homeIntent = new Intent(SplashPageActivity.this, LoginActivity.class);
                    startActivity(homeIntent);
                    finish();
                    //Toast.makeText(SplashPageActivity.this,"Ga ada user",Toast.LENGTH_LONG);

                }
                else{
                    //Toast.makeText(SplashPageActivity.this,"Ga ada user",Toast.LENGTH_LONG);
                    Intent homeIntent = new Intent(SplashPageActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }

            }
        },SPLASH_TIME_OUT);
    }
}
