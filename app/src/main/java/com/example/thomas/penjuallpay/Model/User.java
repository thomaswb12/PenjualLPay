package com.example.thomas.penjuallpay.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private Double saldo;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public User(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }
}