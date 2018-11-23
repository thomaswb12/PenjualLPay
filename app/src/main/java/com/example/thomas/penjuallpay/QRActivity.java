package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.DummyTransaction;
import com.example.thomas.penjuallpay.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private String myStoreName;
    private User user;

    EditText edtIDTransaksiFB;
    EditText edtQRTotalPrice;
    ImageView imgQR;
    DummyTransaction dummyTransaction;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    String myUID = currentFirebaseUser.getUid();
    private ValueEventListener valueEvent;

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
        myStoreName = currentFirebaseUser.getDisplayName();
        dummyTransaction = new DummyTransaction(Integer.parseInt(totalPrice),myStoreName);
        //kirim ke db Dummy
        DatabaseReference mRefTransactionDummy =  database.getReference().child("transaksi").child("dummy").child(idTransaksi);
        mRefTransactionDummy.setValue(dummyTransaction);

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
