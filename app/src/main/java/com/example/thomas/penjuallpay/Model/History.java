package com.example.thomas.penjuallpay.Model;

import java.util.Date;

public class History {
    public String tglTransaksi;
    public int harga;
    public String pelaku;
    public String idTransaksi;

    public History(String tglTransaksi, int harga, String pelaku, String idTransaksi){
        this.tglTransaksi = tglTransaksi;
        this.harga = harga;
        this.pelaku = pelaku;
        this.idTransaksi = idTransaksi;
    }
}
