package com.example.thomas.penjuallpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thomas.penjuallpay.Model.History;
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


public class HistoryJualBeliFragment extends Fragment {
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    String myUID = currentFirebaseUser.getUid();

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference dbTransaksiJualBeli = database.getReference("transaksi").child("seller").child("jualBeli").child(myUID);
    DatabaseReference dbTransaksiWithdraw = database.getReference("transaksi").child("seller").child("withdraw").child(myUID);
    public List<History> listHistory;
    ValueEventListener valueEvent;

    private RecyclerView rcyHistory;
    private HistoryAdapter historyAdapter;

    public static HistoryJualBeliFragment newInstance() {
        HistoryJualBeliFragment fragment = new HistoryJualBeliFragment();
        return fragment;
    }

    public HistoryJualBeliFragment() {
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

        dbTransaksiJualBeli.addValueEventListener(valueEvent);
    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listHistory.clear();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                HashMap<String, Object> history = null;
                TextView noHistory = (TextView) getActivity().findViewById(R.id.txtHistoryJualBeliEmpty);
                if(!items.hasNext())  noHistory.setVisibility(View.VISIBLE);
                else noHistory.setVisibility(View.INVISIBLE);
                while (items.hasNext()){
                    DataSnapshot item = items.next();
                    history = (HashMap<String, Object>) item.getValue();
                    listHistory.add(new History(history.get("waktu").toString(), Integer.parseInt(history.get("total").toString()), history.get("telp_pembeli").toString(), item.getKey()));
                    historyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbTransaksiJualBeli.addValueEventListener(valueEvent);
    }

    @Override
    public void onPause() {
        if (valueEvent != null && dbTransaksiJualBeli!=null) {
            dbTransaksiJualBeli.removeEventListener(valueEvent);
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (valueEvent != null && dbTransaksiJualBeli!=null) {
            dbTransaksiJualBeli.removeEventListener(valueEvent);
        }
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_jual_beli, container, false);

        rcyHistory = v.findViewById(R.id.rcyHistoryJualBeli);

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
