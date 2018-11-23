package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public TextView txtEnterpinTitle;
    private enum state{CreateNewPin, ConfirmNewPin, ConfirmWithdraw};
    private state myState = state.CreateNewPin;

    private String myPin;
    private String myPinTamp="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterpin);

        btnEnterpinOk = (ImageView) findViewById(R.id.btnEnterpinOk);
        btnEnterpinDelete = (ImageView) findViewById(R.id.btnEnterpinDelete);
        txtEnterpinPin = (TextView) findViewById(R.id.txtEnterpinPin);
        txtEnterpinTitle = (TextView) findViewById(R.id.txtEnterpinTitle);

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
                if(myState == state.CreateNewPin){
                    if(txtEnterpinPin.getText().toString().length()==6){
                        myPin = myPinTamp;
                        txtEnterpinPin.setText("");
                        myPinTamp="";
                        txtEnterpinTitle.setText("Confirm Your New PIN");
                        myState = state.ConfirmNewPin;
                    }
                    else{
                        Toast.makeText(EnterpinActivity.this, "PIN must be 6 characters", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (myState == state.ConfirmNewPin){
                    if(! myPin.equals(myPinTamp)){
                        Toast.makeText(EnterpinActivity.this, "You input a wrong PIN", Toast.LENGTH_SHORT).show();
                        txtEnterpinPin.setText("");
                        myPinTamp="";
                    }
                    else{
                        Intent intent = new Intent(EnterpinActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else if (myState == state.ConfirmWithdraw){
                    //isi nanti yaa
                }
            }
        });

        btnEnterpinDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()>0){
                    myPinTamp = myPinTamp.substring(0,myPinTamp.length()-1);
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString()).substring(0,(txtEnterpinPin.getText().toString()).length()-1));
                }
            }
        });
        btnEnterpin0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "0";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "1";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "2";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "3";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "4";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "5";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "6";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "7";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "8";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
        btnEnterpin9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEnterpinPin.getText().toString().length()<6){
                    myPinTamp += "9";
                    txtEnterpinPin.setText((txtEnterpinPin.getText().toString())+"*");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(myState == state.ConfirmNewPin){
            myPin = "";
            myPinTamp="";
            txtEnterpinTitle.setText("Create New PIN");
            txtEnterpinPin.setText("");
            myState = state.CreateNewPin;
        }
        else
            super.onBackPressed();
    }
}
