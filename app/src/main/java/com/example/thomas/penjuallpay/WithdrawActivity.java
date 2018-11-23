package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class WithdrawActivity extends AppCompatActivity {
    public ImageButton btnWithdrawBack;
    public Button btnWithdrawWithdraw;
    public EditText txtWithdrawAmount;
    public TextView txtWithdrawSaldo;

    private int currentBallance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        txtWithdrawAmount = (EditText) findViewById(R.id.txtWithdrawAmount);
        txtWithdrawSaldo = (TextView) findViewById(R.id.txtWithdrawSaldo);
    }

    public void toEnterPin(View v){
        currentBallance = Integer.valueOf(txtWithdrawSaldo.getText().toString().substring(3,(txtWithdrawSaldo.getText().toString().length()-3)));
        if (txtWithdrawAmount.getText().toString().equals("")){
            Toast.makeText(this, "withdraw amount still empty", Toast.LENGTH_LONG).show();
        }
        else{
            int ballanceForWithdraw = Integer.valueOf(txtWithdrawAmount.getText().toString());
            if(ballanceForWithdraw<50000){
                Toast.makeText(this, "withdraw amount must greater than Rp 50.000", Toast.LENGTH_LONG).show();
            }
            else if (ballanceForWithdraw > currentBallance){
                Toast.makeText(this, "Your ballance not enough", Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent = new Intent(WithdrawActivity.this, EnterpinActivity.class);
                intent.putExtra("state",2);
                startActivity(intent);
            }
        }
    }

    public void toMain(View v){
        Intent intent = new Intent(WithdrawActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
