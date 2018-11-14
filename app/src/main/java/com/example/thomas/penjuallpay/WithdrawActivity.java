package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WithdrawActivity extends AppCompatActivity {
    public ImageButton btnWithdrawBack;
    public Button btnWithdrawWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
    }

    public void toEnterPin(View v){
        Intent intent = new Intent(WithdrawActivity.this, EnterpinActivity.class);
        startActivity(intent);
    }

    public void toMain(View v){
        Intent intent = new Intent(WithdrawActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
