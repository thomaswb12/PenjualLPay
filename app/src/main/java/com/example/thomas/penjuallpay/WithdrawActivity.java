package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class WithdrawActivity extends AppCompatActivity {
    public ImageButton btnWithdrawBack;
    public Button btnWithdrawWithdraw;
    public EditText txtWithdrawAmount;
    public TextView txtWithdrawSaldo;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser curUser = mAuth.getCurrentUser();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Seller").child(curUser.getUid());
    private Double currentBallance;
    private ValueEventListener valueEvent;
    private User user;
    private Double myCurrentSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        txtWithdrawAmount = (EditText) findViewById(R.id.txtWithdrawAmount);
        txtWithdrawSaldo = (TextView) findViewById(R.id.txtWithdrawSaldo);

    }

    public void toEnterPin(View v){
        currentBallance = myCurrentSaldo;
        if (txtWithdrawAmount.getText().toString().equals("")){
            Toast.makeText(this, "withdraw amount still empty", Toast.LENGTH_LONG).show();
        }
        else{
            Double ballanceForWithdraw = Double.parseDouble(txtWithdrawAmount.getText().toString());
            if(ballanceForWithdraw<50000){
                Toast.makeText(this, "withdraw amount must greater than Rp 50.000", Toast.LENGTH_LONG).show();
            }
            else if (ballanceForWithdraw > currentBallance){
                Toast.makeText(this, "Your ballance not enough", Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent = new Intent(WithdrawActivity.this, EnterpinActivity.class);
                intent.putExtra("state",2);
                intent.putExtra("withdrawAmount",ballanceForWithdraw);
                startActivity(intent);
            }
        }
    }

    public void toMain(View v){
        Intent intent = new Intent(WithdrawActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                myCurrentSaldo = user.getSaldo();
                txtWithdrawSaldo.setText("Rp "+myCurrentSaldo.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(valueEvent);
    }

    @Override
    protected void onStop() {
        mDatabase.removeEventListener(valueEvent);
        super.onStop();
    }
}
