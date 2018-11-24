package com.example.thomas.penjuallpay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.thomas.penjuallpay.Model.HistoryWithdraw;

import java.util.List;

public class HistoryWithdrawAdapter extends RecyclerView.Adapter<HistoryWithdrawAdapter.HistoryWithdrawHolder>{
    private List<HistoryWithdraw> listHistoryWithdraw;
    private Context mContext;

    public HistoryWithdrawAdapter(List<HistoryWithdraw> listHistoryWithdraw, Context mContext){
        this.listHistoryWithdraw = listHistoryWithdraw;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HistoryWithdrawAdapter.HistoryWithdrawHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_withdraw, viewGroup,false);
        return new HistoryWithdrawAdapter.HistoryWithdrawHolder(itemView);
    }
    

    @Override
    public void onBindViewHolder(@NonNull HistoryWithdrawAdapter.HistoryWithdrawHolder historyHolder, int i) {
        HistoryWithdraw historyWithdraw = listHistoryWithdraw.get(i);
        historyHolder.txtTotal.setText("Rp "+historyWithdraw.total);
        historyHolder.txtTanggal.setText(historyWithdraw.tglTransaksi);
        historyHolder.txtNoTransaksi.setText("" + historyWithdraw.idTransaksi);
    }

    @Override
    public int getItemCount() {
        return this.listHistoryWithdraw.size();
    }

    public class HistoryWithdrawHolder extends RecyclerView.ViewHolder {
        public TextView txtTanggal, txtTotal, txtNoTransaksi;

        public HistoryWithdrawHolder(View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txtHistoryWithdrawTanggal);
            txtTotal = itemView.findViewById(R.id.txtHistoryWithdrawTotal);
            txtNoTransaksi = itemView.findViewById(R.id.txtHistoryWithdrawNomorTransaksi);
        }
    }
}
