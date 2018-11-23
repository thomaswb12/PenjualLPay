package com.example.thomas.penjuallpay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thomas.penjuallpay.Model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>{
    private List<History> listHistory;//untuk data Dummy
    private Context mContext;//supaya FilmAdapter tau activity apa yang mau pake dia

    public HistoryAdapter(List<History> listHistory, Context mContext){
        this.listHistory = listHistory;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup,false);
        return new HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryHolder historyHolder, int i) {
        History history = listHistory.get(i);
        historyHolder.txtHarga.setText("Rp "+history.harga);
        historyHolder.txtTanggal.setText(history.tglTransaksi);
        historyHolder.txtPelaku.setText(history.pelaku);
        historyHolder.txtNoTransaksi.setText("" + history.idTransaksi);
    }

    @Override
    public int getItemCount() {
        return this.listHistory.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{
        public TextView txtTanggal, txtHarga, txtPelaku, txtNoTransaksi;

        public HistoryHolder(View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            txtPelaku = itemView.findViewById(R.id.txtNomor);
            txtNoTransaksi = itemView.findViewById(R.id.txtNomorTransaksi);
        }
    }
}
