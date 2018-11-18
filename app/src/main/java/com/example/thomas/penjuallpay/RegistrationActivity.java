package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    public Button btnRegisRegister;
    public Button btnRegisLogin;
    private static long back_pressed;
    public TextView edtRegisName;
    public TextView edtRegisPhoneNumber;
    public TextView edtRegisEmail;
    public TextView edtRegisPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegisLogin = (Button) findViewById(R.id.btnRegisLogin);
        btnRegisRegister = (Button) findViewById(R.id.btnRegisRegister);
        edtRegisName=(TextView) findViewById(R.id.edtRegisName);
        edtRegisPhoneNumber=(TextView) findViewById(R.id.edtRegisPhoneNumber);
        edtRegisEmail=(TextView) findViewById(R.id.edtRegisEmail);
        edtRegisPassword=(TextView) findViewById(R.id.edtRegisPassword);

        btnRegisLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegisRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storeName = edtRegisName.getText().toString();
                String storePhone = edtRegisPhoneNumber.getText().toString();
                String storeEmail = edtRegisEmail.getText().toString();
                String storePassword = edtRegisPassword.getText().toString();

                Intent intent = new Intent(RegistrationActivity.this, VerificationNumberActivity.class);
                intent.putExtra("storeName",storeName);
                intent.putExtra("storePhone",storePhone);
                intent.putExtra("storeEmail",storeEmail);
                intent.putExtra("storePassword",storePassword);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP));
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "Press once again to exit",Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
