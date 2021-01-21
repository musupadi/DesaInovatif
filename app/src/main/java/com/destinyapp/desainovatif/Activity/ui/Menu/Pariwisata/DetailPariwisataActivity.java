package com.destinyapp.desainovatif.Activity.ui.Menu.Pariwisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

public class DetailPariwisataActivity extends AppCompatActivity {
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    //DETAIL KABAR
    String NAMA,ALAMAT,DESKRIPSI,HARGA,GAMBAR;
    TextView nama,alamat;
    WebView deskripsi,harga;
    ImageView gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pariwisata);
        destiny = new Destiny();
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Level = cursor.getString(4);
                Photo = cursor.getString(5);
            }
        }
        Declaration();
        GETDATA();

    }
    private void Declaration(){
        alamat = findViewById(R.id.tvAlamat);
        deskripsi = findViewById(R.id.webDeskripsiPariwisata);
        harga = findViewById(R.id.webDeskripsiHarga);
        gambar = findViewById(R.id.ivGambar);
    }
    private void GETDATA(){
        Intent intent = getIntent();
        NAMA = intent.getExtras().getString("NAMA");
        ALAMAT = intent.getExtras().getString("ALAMAT");
        DESKRIPSI = intent.getExtras().getString("DESKRIPSI");
        HARGA = intent.getExtras().getString("HARGA");
        GAMBAR = intent.getExtras().getString("GAMBAR");
        getSupportActionBar().setTitle(NAMA);
        alamat.setText(ALAMAT);
        deskripsi.loadData(DESKRIPSI,"text/html","UTF-8");
        harga.loadData(HARGA,"text/html","UTF-8");
        Glide.with(this)
                .load(GAMBAR)
                .into(gambar);
    }
}