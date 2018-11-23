package com.example.thomas.penjuallpay.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DummyTransaction {
    private String telp_pembeli;
    private int total;
    private String waktu;
    private String nama_toko;
    private String image_toko;

    //constructor
    public DummyTransaction(int total, String nama_toko, String image_toko){
        this.total = total;
        this.nama_toko = nama_toko;
        this.image_toko = image_toko;
    }


    //getter setter
    public String getNama_toko() {
        return nama_toko;
    }

    public int getTotal() {
        return total;
    }

    public String getImage_toko() {
        return image_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setImage_toko(String image_toko) {
        this.image_toko = image_toko;
    }
}
