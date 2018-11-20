package com.example.thomas.penjuallpay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificationNumberActivity extends AppCompatActivity {
    public Button btnVerifNumberRegister;
    public EditText edtVerifNumberInput;

    private FirebaseAuth mAuth;

    String storeName;
    String storePhone;
    String storeEmail;
    String storePassword;
    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_number);

        mAuth = FirebaseAuth.getInstance();

        edtVerifNumberInput = (EditText) findViewById(R.id.edtVerifNumberInput);
        btnVerifNumberRegister = (Button) findViewById(R.id.btnVerifNumberRegister);

        codeSent = getIntent().getStringExtra("Code");
        storeName = getIntent().getStringExtra("storeName");
        storeEmail = getIntent().getStringExtra("storeEmail");
        storePassword = getIntent().getStringExtra("storePassword");

        btnVerifNumberRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, edtVerifNumberInput.getText().toString());
                signInWithPhoneAuthCredential(credential);

            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            user.updateEmail(storeEmail);
                            user.updatePassword(storePassword);


                            Intent intent = new Intent(VerificationNumberActivity.this, FinishingRegistrationActivity.class);
                            startActivity(intent);
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
