package com.example.thomas.penjuallpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thomas.penjuallpay.Model.History;
import com.example.thomas.penjuallpay.Model.HistoryWithdraw;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class HistoryWithdrawFragment extends Fragment {
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    String myUID = currentFirebaseUser.getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbTransaksiWithdraw = database.getReference("transaksi").child("seller").child("withdraw").child(myUID);
    public List<HistoryWithdraw> listHistoryWithdraw;
    ValueEventListener valueEvent;

    private RecyclerView rcyHistoryWithdraw;
    private HistoryWithdrawAdapter historyWithdrawAdapter;

    public static HistoryWithdrawFragment newInstance() {
        HistoryWithdrawFragment fragment = new HistoryWithdrawFragment();
        return fragment;
    }

    public HistoryWithdrawFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listHistoryWithdraw = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbTransaksiWithdraw.addValueEventListener(valueEvent);
    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listHistoryWithdraw.clear();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                HashMap<String, Object> historyWithdraw = null;
                TextView noHistory = (TextView) getActivity().findViewById(R.id.txtHistoryWithdrawEmpty);
                if(!items.hasNext())  noHistory.setVisibility(View.VISIBLE);
                else noHistory.setVisibility(View.INVISIBLE);
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    historyWithdraw = (HashMap<String, Object>) item.getValue();
                    listHistoryWithdraw.add(new HistoryWithdraw(historyWithdraw.get("waktu").toString(), Integer.parseInt(historyWithdraw.get("total").toString()), item.getKey()));
                    historyWithdrawAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        dbTransaksiWithdraw.addValueEventListener(valueEvent);
    }

    @Override
    public void onPause() {
        if (valueEvent != null && dbTransaksiWithdraw!=null) {
            dbTransaksiWithdraw.removeEventListener(valueEvent);
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (valueEvent != null && dbTransaksiWithdraw!=null) {
            dbTransaksiWithdraw.removeEventListener(valueEvent);
        }
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_withdraw, container, false);
        rcyHistoryWithdraw = v.findViewById(R.id.rcyHistoryWithdraw);

        Context context = getActivity();
        historyWithdrawAdapter = new HistoryWithdrawAdapter(listHistoryWithdraw,context);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(context);
        rcyHistoryWithdraw.setLayoutManager(lm);
        rcyHistoryWithdraw.setItemAnimator(new DefaultItemAnimator());
        rcyHistoryWithdraw.setAdapter(historyWithdrawAdapter);
        historyWithdrawAdapter.notifyDataSetChanged();
        return  v;
    }
}
