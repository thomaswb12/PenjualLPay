package com.example.thomas.penjuallpay;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.AuthUser;
import com.example.thomas.penjuallpay.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvHomeName = (TextView) v.findViewById(R.id.tvHomeName);
        TextView tvHomeEmail = (TextView) v.findViewById(R.id.tvHomeEmail);
        TextView tvHomePhone = (TextView) v.findViewById(R.id.tvHomePhone);
        TextView tvHomeSaldo = v.findViewById(R.id.tvHomeSaldo);


        AuthUser authUser= new AuthUser();
        tvHomeName.setText(authUser.getUserName());
        tvHomeEmail.setText(authUser.getUserEmail());
        tvHomePhone.setText(authUser.getUserPhone());
        //tvHomeSaldo.setText(authUser.getUserSaldoRp());

        return v;
    }

}
