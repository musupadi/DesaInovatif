package com.destinyapp.desainovatif.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.Activity.ui.EPasarFragment;
import com.destinyapp.desainovatif.Activity.ui.HomeFragment;
import com.destinyapp.desainovatif.Activity.ui.LaporanFragment;
import com.destinyapp.desainovatif.Activity.ui.LayananDesaFragment;
import com.destinyapp.desainovatif.Activity.ui.UserFragment;
import com.destinyapp.desainovatif.Activity.ui2.AlternativeHomeFragment;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity {
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    LinearLayout LHome,LUser,LEPasar,LSurat,LLaporan;
    TextView THome,TUser,TEPasar,TSurat,TLaporan;
    ImageView IHome,IUser,IEPasar,ISurat,ILaporan;
    Fragment fragment;
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(EasyPermissions.hasPermissions(HomeActivity.this, galleryPermissions)) {

        }else{
            EasyPermissions.requestPermissions(HomeActivity.this, "Access for storage",
                    101, galleryPermissions);
        }
        Declaration();
        Default();
        Home();
        OnClick();
        dbHelper = new DB_Helper(HomeActivity.this);
    }
    private void OnClick(){
        LHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home();
            }
        });
        LUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.checkUser();
                if (cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        Username = cursor.getString(0);
                        Password = cursor.getString(1);
                        Nama = cursor.getString(2);
                        Photo = cursor.getString(3);
                        ID = cursor.getString(4);
                    }
                    User();
                }else{
                    Toast.makeText(HomeActivity.this, "Anda Harus Login untuk Menggunakan Fitur ini", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        LEPasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.checkUser();
                if (cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        Username = cursor.getString(0);
                        Password = cursor.getString(1);
                        Nama = cursor.getString(2);
                        Photo = cursor.getString(3);
                        ID = cursor.getString(4);
                    }
                    Pasar();
                }else{
                    Toast.makeText(HomeActivity.this, "Anda Harus Login untuk Menggunakan Fitur ini", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        LSurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.checkUser();
                if (cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        Username = cursor.getString(0);
                        Password = cursor.getString(1);
                        Nama = cursor.getString(2);
                        Photo = cursor.getString(3);
                        ID = cursor.getString(4);
                    }
                    Surat();
                }else{
                    Toast.makeText(HomeActivity.this, "Anda Harus Login untuk Menggunakan Fitur ini", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        LLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.checkUser();
                if (cursor.getCount()>0){
                    while (cursor.moveToNext()){
                        Username = cursor.getString(0);
                        Password = cursor.getString(1);
                        Nama = cursor.getString(2);
                        Photo = cursor.getString(3);
                        ID = cursor.getString(4);
                    }
                    Laporan();
                }else{
                    Toast.makeText(HomeActivity.this, "Anda Harus Login untuk Menggunakan Fitur ini", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void Declaration(){
        LHome = findViewById(R.id.linearHome);
        LUser = findViewById(R.id.linearUser);
        THome = findViewById(R.id.tvHome);
        TUser = findViewById(R.id.tvUser);
        IHome = findViewById(R.id.ivHome);
        IUser = findViewById(R.id.ivUser);
        LEPasar = findViewById(R.id.linearEPasar);
        TEPasar = findViewById(R.id.tvEPasar);
        IEPasar = findViewById(R.id.ivEpasar);
        LSurat = findViewById(R.id.linearSurat);
        TSurat = findViewById(R.id.tvSurat);
        ISurat = findViewById(R.id.ivSurat);
        LLaporan = findViewById(R.id.linearLaporan);
        TLaporan = findViewById(R.id.tvLaporan);
        ILaporan = findViewById(R.id.ivLaporan);
    }

    private void Default(){
        IHome.setImageResource(R.drawable.home);
        THome.setTextColor(Color.rgb(208,228,212));
        IUser.setImageResource(R.drawable.user);
        TUser.setTextColor(Color.rgb(208,228,212));
        IEPasar.setImageResource(R.drawable.pasar);
        TEPasar.setTextColor(Color.rgb(208,228,212));
        ISurat.setImageResource(R.drawable.layanan_desa_1);
        TSurat.setTextColor(Color.rgb(208,228,212));
        ILaporan.setImageResource(R.drawable.report);
        TLaporan.setTextColor(Color.rgb(208,228,212));
    }
    private void Home(){
        Default();
        IHome.setImageResource(R.drawable.home_active);
        THome.setTextColor(Color.rgb(255,255,255));
        fragment = new AlternativeHomeFragment();
        ChangeFragment(fragment);
    }
    private void User(){
        Default();
        IUser.setImageResource(R.drawable.user_active);
        TUser.setTextColor(Color.rgb(255,255,255));
        fragment = new UserFragment();
        ChangeFragment(fragment);
    }
    private void Pasar(){
        Default();
        IEPasar.setImageResource(R.drawable.pasar_active);
        TEPasar.setTextColor(Color.rgb(255,255,255));
        fragment = new EPasarFragment();
        ChangeFragment(fragment);
    }
    private void Surat(){
        Default();
        ISurat.setImageResource(R.drawable.layanan_desa_active);
        TSurat.setTextColor(Color.rgb(255,255,2550));
        fragment = new LayananDesaFragment();
        ChangeFragment(fragment);
    }
    private void Laporan(){
        Default();
        ILaporan.setImageResource(R.drawable.report_active);
        TLaporan.setTextColor(Color.rgb(255,255,255));
        fragment = new LaporanFragment();
        ChangeFragment(fragment);
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }
}