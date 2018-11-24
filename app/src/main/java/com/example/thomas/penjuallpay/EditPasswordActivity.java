package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser curUser = mAuth.getCurrentUser();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Seller").child(curUser.getUid());

    private ValueEventListener valueEvent;
    private String currentPassword;
    private User user;

    public EditText edtEditPassCurrentPassword;
    public EditText edtEditPassNewPassword;
    public EditText edtEditPassConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        edtEditPassCurrentPassword = (EditText) findViewById(R.id.edtEditPassCurrentPassword);
        edtEditPassNewPassword= (EditText) findViewById(R.id.edtEditPassNewPassword);
        edtEditPassConfirmPassword= (EditText) findViewById(R.id.edtEditPassConfirmPassword);
    }

    public void savePassword(View v){
        if(edtEditPassCurrentPassword.getText().toString().equals("") || edtEditPassNewPassword.getText().toString().equals("") || edtEditPassConfirmPassword.getText().toString().equals("")){
            Toast.makeText(this, "You must fill your data", Toast.LENGTH_LONG).show();
        }
        else if (!edtEditPassCurrentPassword.getText().toString().equals(currentPassword)){
            Toast.makeText(this, "You input a wrong password", Toast.LENGTH_LONG).show();
        }
        else if (!edtEditPassNewPassword.getText().toString().equals(edtEditPassConfirmPassword.getText().toString())){
            Toast.makeText(this, "New password and confirm password must be same", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Your Password successfully edited", Toast.LENGTH_LONG).show();
            mDatabase.child("password").setValue(edtEditPassNewPassword.getText().toString());
            Intent intent = new Intent(EditPasswordActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                currentPassword = user.getPassword();
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
