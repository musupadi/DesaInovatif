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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.BuildConfig;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
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
    EditText NamaSurat,NoteSurat,AnakKe,TglLahrBayi,Pukul,NamaBayi;
    //Ayah
    EditText NamaAyah,TempatTglLahirAyah,PekerjaanAyah,AgamaAyah,NoKTPAyah,AlamatAyah;
    //Ibu
    EditText NamaIbu,TempatTanggalLahirIbu,PekerjaanIbu,AgamaIbu,NoKTPIbu,AlamatIbu;

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
        TglLahrBayi = view.findViewById(R.id.etTanggalLahir);
        Pukul = view.findViewById(R.id.etPukul);
        NamaBayi = view.findViewById(R.id.etNamaBayi);

        NamaAyah = view.findViewById(R.id.etNamaAyah);
        TempatTglLahirAyah = view.findViewById(R.id.etTTLAyah);
        PekerjaanAyah = view.findViewById(R.id.etPekerjaanAyah);
        AgamaAyah = view.findViewById(R.id.etAgamaAyah);
        NoKTPAyah = view.findViewById(R.id.etNoKTPAyah);
        AlamatAyah = view.findViewById(R.id.etAlamatAyah);

        NamaIbu = view.findViewById(R.id.etNamaIbu);
        TempatTanggalLahirIbu = view.findViewById(R.id.etTTLIbu);
        PekerjaanIbu = view.findViewById(R.id.etPekerjaanIbu);
        AgamaIbu = view.findViewById(R.id.etAgamaIbu);
        NoKTPIbu = view.findViewById(R.id.etNoKTPIbu);
        AlamatIbu = view.findViewById(R.id.etAlamatIbu);
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
                AnakKe.getText().toString(),NamaAyah.getText().toString(),TempatTglLahirAyah.getText().toString(),PekerjaanAyah.getText().toString(),AgamaAyah.getText().toString(),
                NoKTPAyah.getText().toString(),AlamatAyah.getText().toString(),NamaIbu.getText().toString(),TempatTanggalLahirIbu.getText().toString(),
                PekerjaanIbu.getText().toString(),AgamaIbu.getText().toString(),NoKTPIbu.getText().toString(),AlamatIbu.getText().toString(),
                TglLahrBayi.getText().toString(),Pukul.getText().toString(),NamaBayi.getText().toString()
        );
        data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    if (response.body().getData().equals("Sukses, data akan ditinjau oleh petugas")){
                        Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),MainActivity.class);
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