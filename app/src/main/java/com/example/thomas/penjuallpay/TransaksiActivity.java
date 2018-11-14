package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class TransaksiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar
        getSupportActionBar().setDisplayShowTitleEnabled(true); // hide the current title from the Toolbar
        // implement setNavigationOnClickListener event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // add code here that execute on click of navigation button
                //Intent intent1 = new Intent(this,MainActivity.class);
                //startActivity(intent1);
                back();
            }
        });
    }

    public void back(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
