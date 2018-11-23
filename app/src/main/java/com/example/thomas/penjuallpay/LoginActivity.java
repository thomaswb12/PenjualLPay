package com.example.thomas.penjuallpay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.Password;
import com.example.thomas.penjuallpay.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    public Button btnLoginLogin;
    public Button btnLoginRegister;
    private EditText edtLoginPhoneNumber;
    private EditText edtLoginPassword;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("ListPhone");
    private DatabaseReference mSeller = FirebaseDatabase.getInstance().getReference("Seller");
    private boolean exist = false;
    private HashMap<String, Boolean> hm;
    private User user1;
    private FirebaseUser user;
    private boolean correct = false;

    private ValueEventListener valueEvent;

    private static long back_pressed ;


    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hm = (HashMap) dataSnapshot.getValue();
                if(progressDialog.isShowing())
                    progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "sds",Toast.LENGTH_LONG).show();
                /*
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    HashMap
                    Toast.makeText(LoginActivity.this, dataSnapshot1.getKey(),Toast.LENGTH_LONG).show();
                }*/
                //hm = (HashMap) dataSnapshot.getValue();


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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_login);


        btnLoginLogin = (Button) findViewById(R.id.btnLoginLogin);
        btnLoginRegister = (Button) findViewById(R.id.btnLoginRegister);
        edtLoginPhoneNumber = findViewById(R.id.edtLoginPhoneNumber);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();


        edtLoginPhoneNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                try{
                    if(hm.get(s.toString())){
                        Toast.makeText(LoginActivity.this,""+hm.get("+6289521337118"),Toast.LENGTH_LONG).show();
                        exist = true;
                    }
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this,"false",Toast.LENGTH_LONG).show();
                    exist = false;
                }
                if(!s.equals("") ) {
                     //cekExist(s.toString());
                    //do your work here
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        //LOGIN sementara (tanpa pengecekan nomor&password)
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exist == true){
                    progressDialog.setMessage("Please Wait");
                    progressDialog.show();
                    if(user == null){
                        login();
                    }
                    else{
                        if(user1 != null){
                            if(user1.getPassword().equals(edtLoginPassword.getText().toString())){
                                correct = true;
                                login();
                            }
                            else{
                                FirebaseAuth.getInstance().signOut();
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this,"Password tidak cocok",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this,"no telepon tidak valid",Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

    }

    public void login(){
        //FirebaseAuth.getInstance().
        String noTelp = edtLoginPhoneNumber.getText().toString();
        //Toast.makeText(this,noTelp,Toast.LENGTH_LONG).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                noTelp,        // Phone number to verify
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

            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
                Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_LONG).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Too Many Attempt",Toast.LENGTH_LONG).show();
            }

            // Show a message and update the UI
            // ...
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            if(user == null){
                                user = task.getResult().getUser();

                                mSeller = mSeller.child(user.getUid());

                                mSeller.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        user1 = dataSnapshot.getValue(User.class);
                                        //Toast.makeText(LoginActivity.this,user1.getNoPin(),Toast.LENGTH_LONG).show();


                                        if(user1.getPassword().equals(edtLoginPassword.getText().toString())){
                                            correct = true;
                                            progressDialog.dismiss();
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            FirebaseAuth.getInstance().signOut();
                                            progressDialog.dismiss();
                                            Toast.makeText(LoginActivity.this,"Password tidak cocok",Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else{
                                if(correct == true){
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }



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
