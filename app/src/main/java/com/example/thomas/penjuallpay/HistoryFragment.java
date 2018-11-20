package com.example.thomas.penjuallpay;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbTransaksi = database.getReference("transaksi");
    public List<History> listHistory;
    ValueEventListener valueEvent;

    private RecyclerView rcyHistory;
    private FloatingActionButton fab;
    private HistoryAdapter historyAdapter;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listHistory = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();

        dbTransaksi.addValueEventListener(valueEvent);
    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listHistory.clear();
                ArrayList ListDapat = new ArrayList();
                ListDapat = (ArrayList) dataSnapshot.getValue();
                for(int i = 0; i < ListDapat.size(); i++){
                    Map item = (Map) ListDapat.get(i);
                    listHistory.add(new History(item.get("tanggal_transaksi").toString(), Integer.parseInt(item.get("total_harga").toString()), item.get("telp_user").toString(), Integer.parseInt(item.get("no_transaksi").toString())));
                    historyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbTransaksi.addValueEventListener(valueEvent);
    }

    @Override
    public void onPause() {
        if (valueEvent != null && dbTransaksi!=null) {
            dbTransaksi.removeEventListener(valueEvent);
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (valueEvent != null && dbTransaksi!=null) {
            dbTransaksi.removeEventListener(valueEvent);
        }
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        rcyHistory = v.findViewById(R.id.rcyHistory);

        Context context = getActivity();
        historyAdapter = new HistoryAdapter(listHistory,context);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(context);
        rcyHistory.setLayoutManager(lm);
        rcyHistory.setItemAnimator(new DefaultItemAnimator());
        rcyHistory.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();

        return v;
    }

}
