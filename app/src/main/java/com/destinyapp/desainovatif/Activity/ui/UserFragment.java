package com.destinyapp.desainovatif.Activity.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

public class UserFragment extends Fragment {
    ImageView Picture;
    TextView nama,Username1,Username2,email,Telpon;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Photo,Email;
    Destiny destiny;
    Button logout,Laporan;
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = view.findViewById(R.id.tvNama);
        Username1 = view.findViewById(R.id.tvUsername);
        Username2 = view.findViewById(R.id.tvUsername2);
        email = view.findViewById(R.id.tvEmail);
        Telpon = view.findViewById(R.id.tvNoTelpon);
        Picture = view.findViewById(R.id.ivProfile);
        logout = view.findViewById(R.id.btnLogot);
        Laporan = view.findViewById(R.id.btnLaporan);
        dbHelper = new DB_Helper(getActivity());
        destiny = new Destiny();
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Photo = cursor.getString(4);
                Email = cursor.getString(5);
            }
        }
        nama.setText(Nama);
        Username1.setText(Username);
        Username2.setText(Username);
        email.setText(Email);
        if (!Photo.isEmpty()){
            Glide.with(getActivity())
                    .load(destiny.BASE_URL()+Photo)
                    .into(Picture);
        }
        Laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LaporanActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.Logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Akun Anda Telah Berhasil Keluar", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
            }
        });

    }
}