package com.example.thomas.penjuallpay.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DummyTransaction {
    private String idTransaksi;
    private String seller_UID;
    private String telp_pembeli;
    private int harga;
    private String waktu;
    private String nama_toko;
    private String image_toko;

    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    DatabaseReference mRef;

    public DummyTransaction(int harga, String idTransaksi, String seller_UID){
        this.harga = harga;
        this.idTransaksi = idTransaksi;
        this.seller_UID = seller_UID;

        mRef =  database.getReference().child("transaksi").child("dummy").child(idTransaksi);

        getSellerInfo();
        writeToDatabase();
    }

    public void getSellerInfo(){
        //nantinya:
            //User user; -> pake kelas user
            //nama_toko = user.getNamaToko();
    }

    public  void writeToDatabase(){
        mRef.child("total").setValue(harga);
        //setValue nama_toko, dll juga
    }
}
