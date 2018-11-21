package com.example.thomas.penjuallpay.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthUser {
    FirebaseAuth mAuth;
    FirebaseUser curUSer;
    private DatabaseReference mDatabase;
    private User user;

    public AuthUser(){
        mAuth = FirebaseAuth.getInstance();
        curUSer = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void newUser(){
        user= new User(10000.0);
        mDatabase.child("users").child(curUSer.getUid()).setValue(user);
    }

    public String getUserEmail(){
        return curUSer.getEmail();
    }

    public String getUserPhone(){
        return curUSer.getPhoneNumber();
    }

    public String getUserName(){
        return curUSer.getDisplayName();
    }

    public String getUserSaldoRp(){
        return toRp(user.getSaldo());
    }

    public String toRp(Double nilai){
        String nilaiRp = nilai.toString();
//        int count = 0;
//        String tamp = "";
//        for(int i = nilaiRp.length()-1;i>=0;i--){
//            if(count%3==0){
//                tamp += ".";
//                tamp += nilaiRp.charAt(i);
//                count = 0;
//            }
//            else{
//                count++;
//                tamp += nilaiRp.charAt(i);
//            }
//        }
        return nilaiRp;
    }
}
