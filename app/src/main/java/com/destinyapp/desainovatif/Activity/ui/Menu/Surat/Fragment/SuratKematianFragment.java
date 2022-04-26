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

public class SuratKematianFragment extends Fragment {
    //Cut Here
    Button Submit;
    Destiny destiny;
    //Main
    EditText NamaSurat, NoteSurat;
    String IDS;
    DB_Helper dbHelper;
    String Username,Password,Namas,Photo,ID,ID_Desa,Level;
    //Cut Here


    EditText Nama,TTL,Pekerjaan,NoKTP,Alamat,Jam,Menit,Di,Disebabkan;
    Spinner JenisKelamin,Hari,Bulan,Tahun,TahunMeninggal,BulanMeninggal,HariMeninggal,Agama;

    public SuratKematianFragment() {
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
        return inflater.inflate(R.layout.fragment_surat_kematian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Cut Here
        destiny = new Destiny();
        Bundle bundle = getArguments();
        IDS = bundle.getString("ID");
        Submit = view.findViewById(R.id.btnSubmit);
        NamaSurat = view.findViewById(R.id.etNamaSurat);
        NoteSurat = view.findViewById(R.id.etNoteSurat);
        //DB Helper
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Namas = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_Desa = cursor.getString(5);
                Level = cursor.getString(6);
            }
        }
        //Cut Here
        Hari = view.findViewById(R.id.spHari);
        Bulan = view.findViewById(R.id.spBulan);
        Tahun = view.findViewById(R.id.spTahun);
        Nama = view.findViewById(R.id.etNama);
        TTL = view.findViewById(R.id.etTTL);
        JenisKelamin = view.findViewById(R.id.spJenisKelamin);
        Agama = view.findViewById(R.id.spAgama);
        Pekerjaan = view.findViewById(R.id.etPekerjaan);
        TahunMeninggal = view.findViewById(R.id.spTahunMeninggal);
        BulanMeninggal = view.findViewById(R.id.spBulanMeninggal);
        HariMeninggal = view.findViewById(R.id.spHariMeninggal);
        NoKTP = view.findViewById(R.id.etNoKTP);
        Alamat = view.findViewById(R.id.etAlamat);
        Jam = view.findViewById(R.id.etJam);
        Menit = view.findViewById(R.id.etMenit);
        Di = view.findViewById(R.id.etDi);
        Disebabkan = view.findViewById(R.id.etDisebabkan);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
            }
        });
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

    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mengirimkan Permintaan Surat");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        final Call<Ress> data =api.PostSuratKematian(destiny.AUTH(),destiny.Kunci(),ID,ID_Desa,"7","0",NamaSurat.getText().toString(),NoteSurat.getText().toString(),
                Nama.getText().toString(),destiny.TTL(TTL,Tahun,Bulan,Hari),JenisKelamin.getSelectedItem().toString(),
                Agama.getSelectedItem().toString(),Pekerjaan.getText().toString(),destiny.Tanggal(TahunMeninggal,BulanMeninggal,HariMeninggal),
                Jam.getText().toString()+":"+Menit.getText().toString(),Di.getText().toString(),Disebabkan.getText().toString());
        data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("Zyarga Error",e.toString());
                }
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal Mohon Coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}