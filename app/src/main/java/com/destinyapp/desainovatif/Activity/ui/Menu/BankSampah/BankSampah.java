package com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah.DaftarRewardActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah.HadiahBankSampahActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Jenis.JenisSampahActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Lokasi.LokasiBankSampahActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Lokasi.LokasiSampah;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Transaksi.TransaksiSampahActivity;
import com.destinyapp.desainovatif.Adapter.AdapterJenisBankSampah;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankSampah extends AppCompatActivity {
    LinearLayout Lokasi,Transaksi,Jenis,Hadiah;
    TextView Saldo;
    String Username,Password,Nama,Photo,ID,ID_Desa;
    DB_Helper dbHelper;
    Destiny destiny;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    LinearLayout Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_sampah);
        recycler = findViewById(R.id.recycler);
        destiny = new Destiny();
        Saldo = findViewById(R.id.tvSaldo);
        Lokasi = findViewById(R.id.linearLokasi);
        Transaksi = findViewById(R.id.linearTransaksi);
        Jenis = findViewById(R.id.linearJenis);
        Hadiah = findViewById(R.id.linearReward);
        dbHelper = new DB_Helper(BankSampah.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_Desa = cursor.getString(5);
            }
        }
        Lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankSampah.this, LokasiSampah.class);
                startActivity(intent);
            }
        });
        Jenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankSampah.this, JenisSampahActivity.class);
                startActivity(intent);
            }
        });
        Transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankSampah.this, TransaksiSampahActivity.class);
                startActivity(intent);
            }
        });
        Hadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BankSampah.this, DaftarRewardActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        GetSaldo();

        Back  = findViewById(R.id.linearBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void GetData(){
        mManager = new LinearLayoutManager(BankSampah.this, RecyclerView.VERTICAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.JenisSampah(destiny.AUTH(),destiny.Kunci(),ID_Desa);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if(response.isSuccessful()){
                        mItems=response.body().getData();
                        mAdapter = new AdapterJenisBankSampah(BankSampah.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    Toast.makeText(BankSampah.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(BankSampah.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetSaldo(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> saldoBankSampah = api.SaldoBankSampah(destiny.AUTH(),destiny.Kunci(),ID);
        saldoBankSampah.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                Saldo.setText(response.body().getData().getSaldo_user());
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Saldo.setText("Error");
                Toast.makeText(BankSampah.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}