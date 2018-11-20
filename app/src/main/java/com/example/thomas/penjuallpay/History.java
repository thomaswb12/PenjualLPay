package com.example.thomas.penjuallpay;

import java.util.Date;

public class History {
    public String tglTransaksi;
    public int harga;
    public String pelaku;
    public int noTransaksi;

    public History(String tglTransaksi, int harga, String pelaku, int noTransaksi){
        this.tglTransaksi = tglTransaksi;
        this.harga = harga;
        this.pelaku = pelaku;
        this.noTransaksi = noTransaksi;
    }
}
