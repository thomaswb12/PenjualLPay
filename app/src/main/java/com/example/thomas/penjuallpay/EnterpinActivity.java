package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterpinActivity extends AppCompatActivity {
    public ImageView btnEnterpinOk;
    public ImageView btnEnterpinDelete;
    public Button btnEnterpin0;
    public Button btnEnterpin1;
    public Button btnEnterpin2;
    public Button btnEnterpin3;
    public Button btnEnterpin4;
    public Button btnEnterpin5;
    public Button btnEnterpin6;
    public Button btnEnterpin7;
    public Button btnEnterpin8;
    public Button btnEnterpin9;

    public TextView txtEnterpinPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterpin);

        btnEnterpinOk = (ImageView) findViewById(R.id.btnEnterpinOk);
        btnEnterpinDelete = (ImageView) findViewById(R.id.btnEnterpinDelete);
        txtEnterpinPin = (TextView) findViewById(R.id.txtEnterpinPin);

        btnEnterpin0 = (Button) findViewById(R.id.btnEnterpin0);
        btnEnterpin1 = (Button) findViewById(R.id.btnEnterpin1);
        btnEnterpin2 = (Button) findViewById(R.id.btnEnterpin2);
        btnEnterpin3 = (Button) findViewById(R.id.btnEnterpin3);
        btnEnterpin4 = (Button) findViewById(R.id.btnEnterpin4);
        btnEnterpin5 = (Button) findViewById(R.id.btnEnterpin5);
        btnEnterpin6 = (Button) findViewById(R.id.btnEnterpin6);
        btnEnterpin7 = (Button) findViewById(R.id.btnEnterpin7);
        btnEnterpin8 = (Button) findViewById(R.id.btnEnterpin8);
        btnEnterpin9 = (Button) findViewById(R.id.btnEnterpin9);

        btnEnterpinOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterpinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEnterpinDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()>0){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString()).substring(0,(txtEnterpinPin.getText().toString()).length()-1));
                }
            }
        });
        btnEnterpin0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"0");
                }
            }
        });
        btnEnterpin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"1");
                }
            }
        });
        btnEnterpin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"2");
                }
            }
        });
        btnEnterpin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"3");
                }
            }
        });
        btnEnterpin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"4");
                }
            }
        });
        btnEnterpin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"5");
                }
            }
        });
        btnEnterpin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"6");
                }
            }
        });
        btnEnterpin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"7");
                }
            }
        });
        btnEnterpin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"8");
                }
            }
        });
        btnEnterpin9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"9");
                }
            }
        });
    }
}
