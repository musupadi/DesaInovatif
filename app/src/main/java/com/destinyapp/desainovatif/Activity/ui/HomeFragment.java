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
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.destinyapp.desainovatif.Adapter.AdapterBanner;
import com.destinyapp.desainovatif.Adapter.AdapterBerita;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.Covid.CovidData;
import com.destinyapp.desainovatif.Model.NewModel.Covid.ResponseCovid;
import com.destinyapp.desainovatif.Model.NewModel.Data;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
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
    LinearLayout Pariwisata,UMKM,Bansos,DPT,Sampah,Profil,Anggaran,KemajuanDesa;
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

    //Slider
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private AdapterBanner adapterBanner;
    private Button btnBack, btnNext, btnPlay, btnPause, btnStop;
    private TextView[] mDots;
    private int CurrentPage;

    int SizeBanner = 0;
    boolean forward;
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
        Profil = view.findViewById(R.id.linearProfil);
        Anggaran = view.findViewById(R.id.linearAnggaran);
        KemajuanDesa = view.findViewById(R.id.linearKemajuanDesa);
        recycler = view.findViewById(R.id.recyclerKabarBerita);
        //COVID
        Positif = view.findViewById(R.id.tvPositif);
        Sembuh = view.findViewById(R.id.tvSembuh);
        Meninggal = view.findViewById(R.id.tvMeninggal);
        Kasus = view.findViewById(R.id.tvKasus);
        dbHelper = new DB_Helper(getActivity());
        //Slider
        //Slider
        mSlideViewPager = view.findViewById(R.id.SlideViewPager);
        mDotLayout = view.findViewById(R.id.dotSlayout);

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
//        GetCovid();
        KabarBerita();

        Header(0);
        AutoSlide();
    }
    //Pager Start
    private void Header(final int position){
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//        recycler.setLayoutManager(mManager);
//        LAHeader.setVisibility(View.VISIBLE);
//        TAHeader.setVisibility(View.GONE);
//        AHeader.setAnimation("loading.json");
//        AHeader.playAnimation();
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.Banner();
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    if (mItems.size()<1){
//                        TAHeader.setVisibility(View.VISIBLE);
//                        TAHeader.setText("Banner Belum Ada");
//                        AHeader.setAnimation("notfound.json");
//                        AHeader.playAnimation();
                    }else{
//                        LAHeader.setVisibility(View.GONE);
                        adapterBanner = new AdapterBanner(getActivity(),mItems);
                        mSlideViewPager.setAdapter(adapterBanner);
                        SizeBanner = mItems.size();
                        addDotsIndicator(position);
                        mSlideViewPager.addOnPageChangeListener(viewList);
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalah User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDotsIndicator(int posistion) {
        try {
            mDots = new TextView[SizeBanner];
            mDotLayout.removeAllViews();
            for (int i = 0; i < mDots.length; i++) {
                mDots[i] = new TextView(getActivity());
                mDots[i].setText(Html.fromHtml("&#8226;"));
                mDots[i].setTextSize(35);
                mDots[i].setTextColor(getResources().getColor(R.color.Accent));

                mDotLayout.addView(mDots[i]);
            }
            if (mDots.length > 0) {
                mDots[posistion].setTextColor(getResources().getColor(R.color.Primary));
            }
        }catch (Exception e){

        }
    }
    ViewPager.OnPageChangeListener viewList = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            CurrentPage = i;

            if (CurrentPage == 0) {

            } else if (i == mDots.length - 1) {

            } else {

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
//            final Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                public void run() {
//                    if (CurrentPage == 0) {
//                        forward=true;
//                        mSlideViewPager.setCurrentItem(CurrentPage + 1);
//                    } else if (CurrentPage == mDots.length - 1) {
//                        forward=false;
//                        mSlideViewPager.setCurrentItem(CurrentPage - 1);
//                    } else {
//                        if (forward){
//                            mSlideViewPager.setCurrentItem(CurrentPage + 1);
//                        }else{
//                            mSlideViewPager.setCurrentItem(CurrentPage - 1);
//                        }
//                    }
//                }
//            }, 5000);
        }
    };
    private void AutoSlide(){
//        if(CurrentPage == 0) {
//            forward=true;
//            mSlideViewPager.setCurrentItem(CurrentPage + 1);
//        }else if(CurrentPage == mDots.length - 1) {
//            forward=false;
//            mSlideViewPager.setCurrentItem(CurrentPage - 1);
//        }else{
//            if (forward){
//                mSlideViewPager.setCurrentItem(CurrentPage + 1);
//            }else{
//                mSlideViewPager.setCurrentItem(CurrentPage - 1);
//            }
//        }
    }
    //PagerEnd


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
                ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                Call<NewResponse> Corona = api.InfoCovid();
                Corona.enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, retrofit2.Response<NewResponse> response) {
                        try {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getLink_setting()));
                            startActivity(browserIntent);
                        }catch (Exception e){
                            try {
                                Toast.makeText(getActivity(), "Link Tidak Valid", Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){

                        }

                    }
                });

//                Intent intent = new Intent(getActivity(), PariwisataActivity.class);
//                startActivity(intent);
            }
        });
        UMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                Call<NewResponse> Corona = api.InfoUMKM();
                Corona.enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, retrofit2.Response<NewResponse> response) {
                        try {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getLink_setting()));
                            startActivity(browserIntent);
                        }catch (Exception e){
                            try {
                                Toast.makeText(getActivity(), "Link Tidak Valid", Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){

                        }

                    }
                });
            }
        });
        Bansos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                Call<NewResponse> Corona = api.InfoBansos();
                Corona.enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, retrofit2.Response<NewResponse> response) {
                        try {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getLink_setting()));
                            startActivity(browserIntent);
                        }catch (Exception e){
                            try {
                                Toast.makeText(getActivity(), "Link Tidak Valid", Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){

                        }

                    }
                });
            }
        });
        DPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                Call<NewResponse> Corona = api.InfoDPT();
                Corona.enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, retrofit2.Response<NewResponse> response) {
                        try {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getLink_setting()));
                            startActivity(browserIntent);
                        }catch (Exception e){
                            try {
                                Toast.makeText(getActivity(), "Link Tidak Valid", Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){

                        }

                    }
                });
            }
        });
        KemajuanDesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Anggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                Call<NewResponse> Corona = api.InfoAnggaran();
                Corona.enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, retrofit2.Response<NewResponse> response) {
                        try {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getLink_setting()));
                            startActivity(browserIntent);
                        }catch (Exception e){
                            try {
                                Toast.makeText(getActivity(), "Link Tidak Valid", Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){

                        }

                    }
                });
            }
        });
        Profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                Call<NewResponse> Corona = api.InfoProfilDesa();
                Corona.enqueue(new Callback<NewResponse>() {
                    @Override
                    public void onResponse(Call<NewResponse> call, retrofit2.Response<NewResponse> response) {
                        try {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().getData().getLink_setting()));
                            startActivity(browserIntent);
                        }catch (Exception e){
                            try {
                                Toast.makeText(getActivity(), "Link Tidak Valid", Toast.LENGTH_SHORT).show();
                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){

                        }

                    }
                });
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