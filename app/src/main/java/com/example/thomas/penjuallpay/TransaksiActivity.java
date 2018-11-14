package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class TransaksiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
    }

    public void toQR(View v){
        Intent intent = new Intent(this,QRActivity.class);
        startActivity(intent);

       this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

            this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
