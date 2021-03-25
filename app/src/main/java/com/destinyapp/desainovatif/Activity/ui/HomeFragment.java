package com.destinyapp.desainovatif.Activity.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.API.RetroServerCovid;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.BankSampah;
import com.destinyapp.desainovatif.Activity.ui.Menu.Berita.BeritaActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.ECommerce.ECommerceActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Pariwisata.PariwisataActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.SuratActivity;
import com.destinyapp.desainovatif.Adapter.AdapterBerita;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.Covid.CovidData;
import com.destinyapp.desainovatif.Model.NewModel.Covid.ResponseCovid;
import com.destinyapp.desainovatif.Model.NewModel.Data;
import com.destinyapp.desainovatif.Model.NewModel.Response;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {
    LinearLayout LihatSemuaBerita;
    TextView nama;
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID,ID_Desa;
    LinearLayout Pariwisata,UMKM,Bansos,DPT,Sampah;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private List<Data> Datas = new ArrayList<>();
    private List<CovidData> CovidData = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    Destiny destiny;
    TextView Positif,Sembuh,Meninggal,Kasus;
    int rawat;
    int kasus;
    int sembuh;
    int meninggal;
    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        nama = view.findViewById(R.id.tvNama);
        LihatSemuaBerita = view.findViewById(R.id.linearLihatSemuaBerita);
        Pariwisata = view.findViewById(R.id.linearPariwisata);
        UMKM = view.findViewById(R.id.linearUMKM);
        Bansos = view.findViewById(R.id.linearBansos);
        DPT = view.findViewById(R.id.linearDPT);
        Sampah = view.findViewById(R.id.linearSampah);
        recycler = view.findViewById(R.id.recyclerKabarBerita);
        //COVID
        Positif = view.findViewById(R.id.tvPositif);
        Sembuh = view.findViewById(R.id.tvSembuh);
        Meninggal = view.findViewById(R.id.tvMeninggal);
        Kasus = view.findViewById(R.id.tvKasus);
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
            }
            nama.setText("Selamat Datang, "+Nama);
        }else{
            nama.setText("Selamat Datang, Guest");
        }
        ONClick();
        GetCovid();
        KabarBerita();

    }
    private void GetCovid(){
        ApiRequest api = RetroServerCovid.getClient().create(ApiRequest.class);
        Call<ResponseCovid> Corona = api.Covid();
        Corona.enqueue(new Callback<ResponseCovid>() {
            @Override
            public void onResponse(Call<ResponseCovid> call, retrofit2.Response<ResponseCovid> response) {
                CovidData=response.body().getList_data();
                for (int i=1;i<CovidData.size();i++){
                    rawat = rawat + Integer.parseInt(CovidData.get(i).getJumlah_dirawat());
                    kasus = kasus + Integer.parseInt(CovidData.get(i).getJumlah_kasus());
                    sembuh = sembuh + Integer.parseInt(CovidData.get(i).getJumlah_sembuh());
                    meninggal = meninggal + Integer.parseInt(CovidData.get(i).getJumlah_meninggal());
                }

                Positif.setText(destiny.MagicNumber(Double.parseDouble(String.valueOf(rawat))));
                Meninggal.setText(destiny.MagicNumber(Double.parseDouble(String.valueOf(meninggal))));
                Sembuh.setText(destiny.MagicNumber(Double.parseDouble(String.valueOf(sembuh))));
                Kasus.setText(destiny.MagicNumber(Double.parseDouble(String.valueOf(kasus))));

            }

            @Override
            public void onFailure(Call<ResponseCovid> call, Throwable t) {

            }
        });
    }
    private void ONClick(){
        LihatSemuaBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BeritaActivity.class);
                startActivity(intent);
            }
        });
        Sampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Username == null){
                    Toast.makeText(getActivity(), "Anda Harus Login untuk Menggunakan Fitur ini", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), BankSampah.class);
                    startActivity(intent);

                }
            }
        });
        Pariwisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PariwisataActivity.class);
                startActivity(intent);
            }
        });
        UMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://eform.bri.co.id/bpum"));
                startActivity(browserIntent);
            }
        });
        Bansos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dtks.kemensos.go.id"));
                startActivity(browserIntent);
            }
        });
        DPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lindungihakpilihmu.kpu.go.id"));
                startActivity(browserIntent);
            }
        });
    }
    private void KabarBerita(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Response> KabarBerita = api.Berita_bogor(1);
        KabarBerita.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Datas=response.body().getData();
                mAdapter = new AdapterBerita(getActivity(),Datas);
                recycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}