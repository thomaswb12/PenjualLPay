package com.example.thomas.penjuallpay.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private Double saldo;
    private String noPin;
    private String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)

    }

    public User(Double saldo, String noPin, String password) {
        this.saldo = saldo;
        this.noPin = noPin;
    }


    public Double getSaldo() {
        return saldo;
    }

    public String getNoPin() {
        return noPin;
    }

    public String getPassword() {
        return password;
    }

    public void setNoPin(String noPin) {
        this.noPin = noPin;
    }
}
