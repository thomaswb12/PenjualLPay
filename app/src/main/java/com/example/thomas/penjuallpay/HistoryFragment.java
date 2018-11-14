package com.example.thomas.penjuallpay;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history, container, false);


        rcyHistory = v.findViewById(R.id.rcyHistory);
        List<History> listHistory = new ArrayList<>();
        Context context = getActivity();
        historyAdapter = new HistoryAdapter(listHistory,context);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(context);
        rcyHistory.setLayoutManager(lm);
        rcyHistory.setItemAnimator(new DefaultItemAnimator());
        rcyHistory.setAdapter(historyAdapter);

        listHistory.add(new History("Senin, 10 Oktober 2018",165000));
        listHistory.add(new History("Senin, 10 Oktober 2018",185000));
        listHistory.add(new History("Senin, 10 Oktober 2018",195000));

        historyAdapter.notifyDataSetChanged();

        return v;
    }

}
