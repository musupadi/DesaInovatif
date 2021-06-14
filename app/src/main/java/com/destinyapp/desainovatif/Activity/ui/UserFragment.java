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
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Laporan.LaporanActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    ImageView Picture;
    TextView nama,email,pekerjaan,saldo;
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,Email,ID;
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
        saldo = view.findViewById(R.id.tvSaldo);
        pekerjaan = view.findViewById(R.id.tvPekerjaan);
        email = view.findViewById(R.id.tvEmail);
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
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
            }
        }
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> User = api.user_detail(destiny.AUTH(),destiny.Kunci(),ID);
        User.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                if (!Photo.isEmpty()){
                    Glide.with(getActivity())
                            .load(destiny.BASE_URL()+Photo)
                            .into(Picture);
                }
                nama.setText(response.body().getData().getNama_user());
                email.setText(response.body().getData().getEmail_user());
                pekerjaan.setText(response.body().getData().getPekerjaan_user());
                saldo.setText(response.body().getData().getSaldo_user());
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        Laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LaporanActivity.class);
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