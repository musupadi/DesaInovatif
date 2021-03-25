package com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Adapter.AdapterListHadiahBankSampah;
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

public class DaftarRewardActivity extends AppCompatActivity {
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID,ID_Desa;
    Destiny destiny;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    TextView Saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_reward);
        Saldo = findViewById(R.id.tvSaldo);
        recycler = findViewById(R.id.recycler);
        dbHelper = new DB_Helper(DaftarRewardActivity.this);
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
        GetData();
    }
    private void GetData(){
        mManager = new LinearLayoutManager(DaftarRewardActivity.this,RecyclerView.VERTICAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.ListRewardBankSampah(ID_Desa);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if(response.isSuccessful()){
                        mItems=response.body().getData();
                        mAdapter = new AdapterListHadiahBankSampah(DaftarRewardActivity.this,mItems,recycler,Saldo);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    Toast.makeText(DaftarRewardActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DaftarRewardActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DaftarRewardActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}