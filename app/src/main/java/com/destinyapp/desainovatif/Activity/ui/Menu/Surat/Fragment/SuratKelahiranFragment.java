package com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.BuildConfig;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.HomeActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratKelahiranFragment extends Fragment {
    Button Submit;
    Destiny destiny;

    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID,ID_Desa,Level;
    LinearLayout lOrangBersangkutan;

    //Main Surat
    EditText NamaSurat,NoteSurat,AnakKe,TglLahrBayi,Jam,Menit,NamaBayi;
    Spinner Tahun,Bulan,Hari;
    //Ayah
    EditText NamaAyah,TempatTglLahirAyah,PekerjaanAyah,NoKTPAyah,AlamatAyah;
    Spinner TahunAyah,BulanAyah,HariAyah,AgamaAyah;
    //Ibu
    EditText NamaIbu,TempatTanggalLahirIbu,PekerjaanIbu,NoKTPIbu,AlamatIbu;
    Spinner TahunIbu,BulanIbu,HariIbu,AgamaIbu;

    String IDS;

    public SuratKelahiranFragment() {
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
        return inflater.inflate(R.layout.fragment_surat_kelahiran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Submit = view.findViewById(R.id.btnSubmit);
        NamaSurat = view.findViewById(R.id.etNamaSurat);
        NoteSurat = view.findViewById(R.id.etNoteSurat);
        AnakKe = view.findViewById(R.id.etAnakKe);
        TglLahrBayi = view.findViewById(R.id.etTTL);
        NamaBayi = view.findViewById(R.id.etNamaBayi);
        Tahun = view.findViewById(R.id.spTahun);
        Bulan = view.findViewById(R.id.spBulan);
        Hari = view.findViewById(R.id.spHari);
        Jam = view.findViewById(R.id.etJam);
        Menit = view.findViewById(R.id.etMenit);

        NamaAyah = view.findViewById(R.id.etNamaAyah);
        TempatTglLahirAyah = view.findViewById(R.id.etTTLAyah);
        PekerjaanAyah = view.findViewById(R.id.etPekerjaanAyah);
        AgamaAyah = view.findViewById(R.id.spAgamaAyah);
        NoKTPAyah = view.findViewById(R.id.etNoKTPAyah);
        AlamatAyah = view.findViewById(R.id.etAlamatAyah);
        TahunAyah = view.findViewById(R.id.spTahunAyah);
        BulanAyah = view.findViewById(R.id.spBulanAyah);
        HariAyah = view.findViewById(R.id.spHariAyah);

        NamaIbu = view.findViewById(R.id.etNamaIbu);
        TempatTanggalLahirIbu = view.findViewById(R.id.etTTLIbu);
        PekerjaanIbu = view.findViewById(R.id.etPekerjaanIbu);
        AgamaIbu = view.findViewById(R.id.spAgamaIbu);
        NoKTPIbu = view.findViewById(R.id.etNoKTPIbu);
        AlamatIbu = view.findViewById(R.id.etAlamatIbu);
        TahunIbu = view.findViewById(R.id.spTahunIbu);
        BulanIbu = view.findViewById(R.id.spBulanIbu);
        HariIbu = view.findViewById(R.id.spHariIbu);

        Submit = view.findViewById(R.id.btnSubmit);
        destiny = new Destiny();
        Bundle bundle = getArguments();
        IDS = bundle.getString("ID");

        //DB Helper
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_Desa = cursor.getString(5);
                Level = cursor.getString(6);
            }
        }

        Jam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (Integer.parseInt(Jam.getText().toString()) >24 || Integer.parseInt(Jam.getText().toString()) < 0){
                        Jam.setText("24");
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Menit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (Integer.parseInt(Menit.getText().toString()) >60 || Integer.parseInt(Menit.getText().toString()) < 0){
                        Menit.setText("60");
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic();
            }
        });

    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mengirimkan Permintaan Surat");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        final Call<Ress> data =api.PostSuratKelahiran(destiny.AUTH(),destiny.Kunci(),ID,ID_Desa,
                IDS,"0",NamaSurat.getText().toString(),NoteSurat.getText().toString(),
                AnakKe.getText().toString(),NamaAyah.getText().toString(),destiny.TTL(TempatTglLahirAyah,Tahun,Bulan,Hari),PekerjaanAyah.getText().toString(),AgamaAyah.getSelectedItem().toString(),
                NoKTPAyah.getText().toString(),AlamatAyah.getText().toString(),NamaIbu.getText().toString(),destiny.TTL(TempatTanggalLahirIbu,Tahun,Bulan,Hari),
                PekerjaanIbu.getText().toString(),AgamaIbu.getSelectedItem().toString(),NoKTPIbu.getText().toString(),AlamatIbu.getText().toString(),
                destiny.TTL(TglLahrBayi,Tahun,Bulan,Hari),Jam.getText().toString()+":"+Menit.getText().toString(),NamaBayi.getText().toString()
        );
        data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    if (response.body().getData().equals("Sukses, data akan ditinjau oleh petugas")){
                        Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}