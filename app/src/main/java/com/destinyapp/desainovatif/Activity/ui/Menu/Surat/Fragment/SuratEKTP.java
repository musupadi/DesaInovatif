package com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

public class SuratEKTP extends AppCompatActivity {
    //Cut Here
    Button Submit;
    Destiny destiny;
    //Main
    EditText NamaSurat, NoteSurat;
    String IDS;
    DB_Helper dbHelper;
    String Username,Password,Namas,Photo,ID,ID_Desa,Level;
    //Cut Here


    EditText Nama,TTL,Pekerjaan,NIK,Alamat;
    Spinner JenisKelamin,Hari,Bulan,Tahun,Agama,StatusPernikahan,GolonganDarah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_ektp);
    }
}