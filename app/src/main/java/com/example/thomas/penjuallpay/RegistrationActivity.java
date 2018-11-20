package com.example.thomas.penjuallpay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity {
    public Button btnRegisRegister;
    public Button btnRegisLogin;
    private static long back_pressed;
    public TextView edtRegisName;
    public TextView edtRegisPhoneNumber;
    public TextView edtRegisEmail;
    public TextView edtRegisPassword;
    public TextView edtRegisConfirmPassword;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        btnRegisLogin = (Button) findViewById(R.id.btnRegisLogin);
        btnRegisRegister = (Button) findViewById(R.id.btnRegisRegister);
        edtRegisName=(TextView) findViewById(R.id.edtRegisName);
        edtRegisPhoneNumber=(TextView) findViewById(R.id.edtRegisPhoneNumber);
        edtRegisEmail=(TextView) findViewById(R.id.edtRegisEmail);
        edtRegisPassword=(TextView) findViewById(R.id.edtRegisPassword);
        edtRegisConfirmPassword = findViewById(R.id.edtRegisConfirmPassword);

        progressDialog = new ProgressDialog(this);

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
                String storeConfirmPassword = edtRegisConfirmPassword.getText().toString();


                if(storeEmail.equals("") || storePhone.equals("") || storeEmail.equals("") || storePassword.equals("") || storeConfirmPassword.equals("")){
                    Toast.makeText(RegistrationActivity.this,"Data incomplete",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    if(!storePassword.equals(storeConfirmPassword)){
                        edtRegisConfirmPassword.requestFocus();
                        Toast.makeText(RegistrationActivity.this,"beda",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(isValidEmail(storeEmail)){
                            regis();
                        }
                        else{
                            Toast.makeText(RegistrationActivity.this,"not valid",Toast.LENGTH_SHORT).show();
                        }

                    }
                }

//                Intent intent = new Intent(RegistrationActivity.this, VerificationNumberActivity.class);
//                intent.putExtra("storeName",storeName);
//                intent.putExtra("storePhone",storePhone);
//                intent.putExtra("storeEmail",storeEmail);
//                intent.putExtra("storePassword",storePassword);
//                startActivity(intent);
            }
        });
    }

    public void regis(){

        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                edtRegisPhoneNumber.getText().toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
                Toast.makeText(RegistrationActivity.this,"Failed",Toast.LENGTH_LONG).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this,"Too Many Attempt",Toast.LENGTH_LONG).show();
            }

            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            //Log.d(TAG, "onCodeSent:" + verificationId);
            super.onCodeSent(verificationId,token);
            progressDialog.dismiss();
            Intent intent = new Intent(RegistrationActivity.this,VerificationNumberActivity.class);
            intent.putExtra("Code",verificationId);

            String storeName = edtRegisName.getText().toString();
            String storeEmail = edtRegisEmail.getText().toString();
            String storePassword = edtRegisPassword.getText().toString();

            intent.putExtra("storeName",storeName);
            intent.putExtra("storeEmail",storeEmail);
            intent.putExtra("storePassword",storePassword);

            startActivity(intent);

            // ...
        }
    };


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
