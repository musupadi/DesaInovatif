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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.destinyapp.desainovatif.Activity.ui.Menu.SuratActivity;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

public class HomeFragment extends Fragment {
    LinearLayout surat;
    TextView nama;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Photo;
    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = view.findViewById(R.id.tvNama);
        surat = view.findViewById(R.id.linearSurat);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Photo = cursor.getString(4);
            }
        }
        nama.setText("Selamat Datang, "+Nama);
        ONClick();
    }
    private void ONClick(){
        surat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SuratActivity.class);
                startActivity(intent);
            }
        });
    }
}