package com.destinyapp.desainovatif.Activity.ui.Menu.BeritaDesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Adapter.AdapterBeritaDesa;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaDesaActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_agenda_desa);
        recycler = findViewById(R.id.recycler);
        destiny = new Destiny();
        dbHelper = new DB_Helper(AgendaDesaActivity.this);
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
        Back  = findViewById(R.id.linearBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        GetData();
    }
    private void GetData(){
        mManager = new LinearLayoutManager(AgendaDesaActivity.this, RecyclerView.VERTICAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.Agenda_Desa(destiny.AUTH(),destiny.Kunci());
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if(response.isSuccessful()){
                        mItems=response.body().getData();
                        mAdapter = new AdapterBeritaDesa(AgendaDesaActivity.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    Toast.makeText(AgendaDesaActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AgendaDesaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}