package com.destinyapp.desainovatif.Activity.ui.Menu.Berita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

public class DetailBeritaActivity extends AppCompatActivity {
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    //DETAIL KABAR
    String JUDUL,ISI,TANGGAL,GANBAR;
    TextView judul,tanggal;
    WebView isi;
    ImageView gambar;
    LinearLayout Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
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
        isi = findViewById(R.id.webIsi);
        tanggal = findViewById(R.id.tvTanggal);
        gambar = findViewById(R.id.ivGambar);
        judul = findViewById(R.id.tvJudul);
        Back  = findViewById(R.id.linearBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void GETDATA(){
        Intent intent = getIntent();
        JUDUL = intent.getExtras().getString("JUDUL");
        ISI = intent.getExtras().getString("ISI");
        TANGGAL = intent.getExtras().getString("TANGGAL");
        GANBAR = intent.getExtras().getString("GAMBAR");
        judul.setText(JUDUL);
        isi.loadData(destiny.Changer(ISI),"text/html","UTF-8");
        tanggal.setText(destiny.MagicDateChange(TANGGAL));
        Glide.with(this)
                .load(GANBAR)
                .into(gambar);
    }
}