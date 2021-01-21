package com.destinyapp.desainovatif.Activity.ui.Menu.Toko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Adapter.AdapterGalleryProduk;
import com.destinyapp.desainovatif.Adapter.AdapterProduk;
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

public class DetailProductActivity extends AppCompatActivity {
    String ID,NAMA,DESKRIPSI,HARGA,ID_USER,STATUS;
    RecyclerView recyclerGallery;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Photo;
    String IDS;
    TextView harga,tvDeskripsi;
    WebView deskripsi;
    Destiny destiny;
    Button status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        destiny = new Destiny();
        Declaration();
        GetData();
    }
    private void Declaration(){
        recyclerGallery = findViewById(R.id.recyclerGallery);
        harga = findViewById(R.id.tvHarga);
        deskripsi = findViewById(R.id.webDeskripsi);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        status = findViewById(R.id.btnStatus);
    }
    private void GetData(){
        Intent intent = getIntent();
        NAMA = intent.getExtras().getString("NAMA");
        ID = intent.getExtras().getString("ID");
        DESKRIPSI = intent.getExtras().getString("DESKRIPSI");
        HARGA = intent.getExtras().getString("HARGA");
        ID_USER = intent.getExtras().getString("ID_USER");
        STATUS = intent.getExtras().getString("STATUS");
        getSupportActionBar().setTitle(NAMA);
        harga.setText(destiny.MagicRP(Double.parseDouble(HARGA)));
        tvDeskripsi.setText(DESKRIPSI);
//        deskripsi.loadData(DESKRIPSI,"text/html","UTF-8");
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (!STATUS.equals("aktif")){
            status.setBackgroundResource(R.drawable.round_red);
            status.setText("Tidak Aktif");
        }
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Photo = cursor.getString(4);
            }
        }
        Cursor cursors = dbHelper.checkID();
        if (cursors.getCount()>0){
            while (cursors.moveToNext()){
                IDS = cursors.getString(0);
            }
        }
        Gallery(ID);
    }
    private void Gallery(String id){
        mManager = new LinearLayoutManager(DetailProductActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerGallery.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.GalleryProduk(id);
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    mAdapter = new AdapterGalleryProduk(DetailProductActivity.this,mItems);
                    recyclerGallery.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(DetailProductActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(DetailProductActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DetailProductActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}