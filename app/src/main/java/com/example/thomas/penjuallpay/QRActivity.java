package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.DummyTransaction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Calendar;
import java.util.Date;

public class QRActivity extends AppCompatActivity {
    private String idTransaksi;
    private String totalPrice;

    EditText edtIDTransaksiFB;
    EditText edtQRTotalPrice;
    ImageView imgQR;
    DummyTransaction dummyTransaction;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        //sambungkan dengan xml
        edtIDTransaksiFB = findViewById(R.id.edtIDTransaksiFB);
        edtQRTotalPrice = findViewById(R.id.edtQRTotalPrice);

        //dapatkan extra dari page TransaksiActivity
        Bundle extras = getIntent().getExtras();
        idTransaksi = extras.getString("idTransaksi");
        totalPrice = extras.getString("totalPrice");

        //tampilkan extra ke xml
        edtIDTransaksiFB.setText(idTransaksi);
        edtQRTotalPrice.setText(totalPrice);

        //buat class DummyTransaction
        String myUID = currentFirebaseUser.getUid();
        dummyTransaction = new DummyTransaction(Integer.parseInt(totalPrice), idTransaksi, myUID);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            //buat QR dengan pesan didalamnya adalah idTransaksi
            BitMatrix bitMatrix = multiFormatWriter.encode(idTransaksi,BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imgQR = (ImageView) findViewById(R.id.imgQR);
            imgQR.setImageBitmap(bitmap);
        }
        catch (WriterException e){
            e.printStackTrace();
        }

    }

    public void back(View v){
        Intent intent = new Intent(this,TransaksiActivity.class);
        startActivity(intent);

        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this,TransaksiActivity.class);
            startActivity(intent);

            this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
