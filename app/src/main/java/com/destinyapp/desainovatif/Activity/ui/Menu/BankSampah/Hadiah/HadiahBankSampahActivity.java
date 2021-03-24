package com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah.AmbilReward.AmbilRewardActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah.HistoryTransaksi.TransaksiRewardActivity;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HadiahBankSampahActivity extends AppCompatActivity {
    LinearLayout Ambil,Daftar,History;
    TextView Saldo;
    String Username,Password,Nama,Photo,ID,ID_Desa;
    DB_Helper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadiah_bank_sampah);
        Saldo = findViewById(R.id.tvSaldo);
        Ambil = findViewById(R.id.linearAmbil);
        Daftar = findViewById(R.id.linearDaftarReward);
        History = findViewById(R.id.linearTransaksi);
        dbHelper = new DB_Helper(HadiahBankSampahActivity.this);
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
        GetSaldo();
        Ambil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HadiahBankSampahActivity.this, AmbilRewardActivity.class);
                startActivity(intent);
            }
        });
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HadiahBankSampahActivity.this, DaftarRewardActivity.class);
                startActivity(intent);
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HadiahBankSampahActivity.this, TransaksiRewardActivity.class);
                startActivity(intent);
            }
        });
    }
    private void GetSaldo(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> saldoBankSampah = api.SaldoBankSampah(ID);
        saldoBankSampah.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                Saldo.setText(response.body().getData().getSaldo_user());
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Saldo.setText("Error");
                Toast.makeText(HadiahBankSampahActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}