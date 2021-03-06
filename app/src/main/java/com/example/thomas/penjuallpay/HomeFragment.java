package com.example.thomas.penjuallpay;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thomas.penjuallpay.Model.AuthUser;
import com.example.thomas.penjuallpay.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser curUser = mAuth.getCurrentUser();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Seller").child(curUser.getUid());

    private ValueEventListener valueEvent;
    private User user;
    private TextView tvHomeSaldo;
    private ImageView imgHomeStoreLogo;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                //user = new User(Double.valueOf(dataSnapshot.child("saldo").getValue().toString())); //pake constructor -> User(Double saldo)
                //tvHomeSaldo.setText("Rp. "+ user.getSaldo().toString());
                tvHomeSaldo.setText("Rp. "+ user.getSaldo().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(valueEvent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvHomeName = (TextView) v.findViewById(R.id.tvHomeName);
        TextView tvHomeEmail = (TextView) v.findViewById(R.id.tvHomeEmail);
        TextView tvHomePhone = (TextView) v.findViewById(R.id.tvHomePhone);
        imgHomeStoreLogo = (ImageView) v.findViewById(R.id.imgHomeStoreLogo);
        tvHomeSaldo = v.findViewById(R.id.tvHomeSaldo);


        StorageReference profile = FirebaseStorage.getInstance().getReference("/profilepics/"+curUser.getUid()+".jpg");

        final long ONE_MEGABYTE = 1024 * 1024;
        StorageReference ref = FirebaseStorage.getInstance().getReference("/profilepics/"+curUser.getUid()+".jpg");
        ref.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgHomeStoreLogo.setImageBitmap(bitmap);
            }
        });


        //AuthUser authUser= new AuthUser();
        tvHomeName.setText(curUser.getDisplayName());
        tvHomeEmail.setText(curUser.getEmail());
        tvHomePhone.setText(curUser.getPhoneNumber());
        //tvHomeSaldo.setText(""+user.getSaldo());


        return v;
    }
}
