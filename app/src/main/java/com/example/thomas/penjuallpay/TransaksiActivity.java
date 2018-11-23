package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class TransaksiActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String idTransaksi;
    private String totalPrice;

    public EditText edtTransaksiIdTransaksi;
    public EditText edtTransaksiTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        edtTransaksiIdTransaksi = (EditText) findViewById(R.id.edtTransaksiIdTransaksi);
        edtTransaksiTotalPrice = (EditText) findViewById(R.id.edtTransaksiTotalPrice);

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
        idTransaksi = database.getInstance().getReference("transaksi").child("dummy").push().getKey(); //bikin id transaksi jualbeli
        edtTransaksiIdTransaksi.setText(idTransaksi); //masukkan id transaksi ke edit text Id Transaksi
    }

    public void back(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void toQR(View v){
        Intent intent = new Intent(this,QRActivity.class);
        intent.putExtra("idTransaksi",idTransaksi); //kirim ID transaksi yang sudah dibuat
        totalPrice = edtTransaksiTotalPrice.getText().toString();
        intent.putExtra("totalPrice",totalPrice); //kirim total harga yang sudah diinputkan
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
