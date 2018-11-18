package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VerificationNumberActivity extends AppCompatActivity {
    public Button btnVerifNumberRegister;
    public EditText edtVerifNumberInput;

    String storeName;
    String storePhone;
    String storeEmail;
    String storePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_number);

        edtVerifNumberInput = (EditText) findViewById(R.id.edtVerifNumberInput);
        btnVerifNumberRegister = (Button) findViewById(R.id.btnVerifNumberRegister);

        storeName = getIntent().getStringExtra("storeName");
        storeEmail = getIntent().getStringExtra("storeEmail");
        storePhone = getIntent().getStringExtra("storePhone");
        storePassword = getIntent().getStringExtra("storePassword");

        btnVerifNumberRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationNumberActivity.this, FinishingRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
