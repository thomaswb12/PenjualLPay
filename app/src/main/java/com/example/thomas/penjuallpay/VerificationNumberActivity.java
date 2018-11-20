package com.example.thomas.penjuallpay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

public class VerificationNumberActivity extends AppCompatActivity {
    public Button btnVerifNumberRegister;
    public EditText edtVerifNumberInput;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    String storeName;
    String storePhone;
    String storeEmail;
    String storePassword;
    String codeSent;

    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_number);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

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
                progressDialog.setMessage("Please wait");
                progressDialog.show();
                signInWithPhoneAuthCredential(credential);

            }
        });
    }

    private void setUser(){




        progressDialog.dismiss();

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");

                            user = mAuth.getCurrentUser();
                            user.updateEmail(storeEmail)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                user.updatePassword(storePassword)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {

                                                                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                                                            .setDisplayName(storeName)
                                                                            .build();

                                                                    user.updateProfile(profile)
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>(){

                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if(task.isSuccessful()){
                                                                                        progressDialog.dismiss();
                                                                                        Toast.makeText(VerificationNumberActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();

                                                                                        Intent intent = new Intent(VerificationNumberActivity.this, FinishingRegistrationActivity.class);
                                                                                        startActivity(intent);
                                                                                        }
                                                                                    else {
                                                                                        progressDialog.dismiss();
                                                                                        Toast.makeText(VerificationNumberActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });
                                                                    // Log.d(TAG, "Email sent.");
                                                                }
                                                                else {
                                                                    progressDialog.dismiss();
                                                                    user.delete()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                    }
                                                                                }
                                                                            });
                                                                    Toast.makeText(VerificationNumberActivity.this,"Gagal password", Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                        });
                                                // Log.d(TAG, "Email sent.");
                                            }
                                            else {
                                                progressDialog.dismiss();
                                                user.delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                }
                                                            }
                                                        });
                                                Toast.makeText(VerificationNumberActivity.this,"Gagal email", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                progressDialog.dismiss();
                                Toast.makeText(VerificationNumberActivity.this,"Failed", Toast.LENGTH_SHORT);
                            }
                        }
                    }
                });
    }
}
