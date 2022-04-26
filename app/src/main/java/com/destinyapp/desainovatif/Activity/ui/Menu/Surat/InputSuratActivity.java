package com.destinyapp.desainovatif.Activity.ui.Menu.Surat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.BlankFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.DataFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratBedaNIKFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratBedaNamaBSTFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratBedaNamaFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratBedaNamaPNSFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratEKTPFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKelahiranFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKematianFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKeteranganBedaDomisiliSementaraFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKeteranganBelumMenikah1Fragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKeteranganBelumMenikah2Fragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKeteranganTidakBekerjaFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKeteranganUsaha;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratNikahN1Fragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratNikahN2Fragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratNikahN4Fragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratPernyataanIzinDomisiliUsahaFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratPernyataanOrangTuaWaliFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratSKKMBPJSFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratSKTMFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratSKTMRSFragment;
import com.destinyapp.desainovatif.Adapter.AdapterListSubKatSurat;
import com.destinyapp.desainovatif.Adapter.Spinner.AdapterKategoriSurat;
import com.destinyapp.desainovatif.Adapter.Spinner.AdapterListUserRT;
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

public class InputSuratActivity extends AppCompatActivity {
    DB_Helper dbHelper;
    String Username,Password,Nama,Photo,ID,ID_Desa,Level;
    Spinner spinner,spinnerBersangkutan,spinnerSub,spinnerUntuk;
    LinearLayout lUntuk,lOrangBersangkutan,lSubSurat;
    TextView kat,ber,sub;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    Fragment fragment;
    Destiny destiny;
    LinearLayout Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_surat);
        destiny= new Destiny();

        kat = findViewById(R.id.tvKat);
        ber = findViewById(R.id.tvBersangkutan);
        sub = findViewById(R.id.tvSub);
        //Image
        spinnerBersangkutan = findViewById(R.id.spinnerBersangkutan);
        lOrangBersangkutan = findViewById(R.id.linearOrangBersangkutan);

        dbHelper = new DB_Helper(this);
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
        spinner = findViewById(R.id.spinner);
        spinnerSub = findViewById(R.id.spinnerSubKat);
        spinnerBersangkutan = findViewById(R.id.spinnerBersangkutan);
        lOrangBersangkutan = findViewById(R.id.linearOrangBersangkutan);
        lSubSurat = findViewById(R.id.linearSubSurat);
        lUntuk = findViewById(R.id.linearUntuk);
        spinnerUntuk = findViewById(R.id.spinnerUntuk);
//        if (Level.equals("member")){
//            lUntuk.setVisibility(View.GONE);
//            lOrangBersangkutan.setVisibility(View.GONE);
//        }
        lUntuk.setVisibility(View.GONE);
        lOrangBersangkutan.setVisibility(View.GONE);
        GetKategori();
        Back  = findViewById(R.id.linearBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        GetOrangBersangkutan();
        spinnerUntuk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spinnerUntuk.getSelectedItem().toString().equals("Diri Sendiri")){
                    lOrangBersangkutan.setVisibility(View.VISIBLE);
                    ber.setText("0");
                }else{
                    lOrangBersangkutan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                    String clickedItems = clickedItem.getId_surat_kategori();
                    kat.setText(clickedItems);
                    if (clickedItems.equals("6")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "6");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKelahiranFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("7")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "7");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKematianFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("8")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "8");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratBedaNamaFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("9")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "9");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratNikahN1Fragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("11")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "11");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratEKTPFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("16")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "16");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratPernyataanOrangTuaWaliFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("19")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "19");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKeteranganBedaDomisiliSementaraFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("20")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "20");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratSKTMFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("21")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "21");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratBedaNamaBSTFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("22")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "22");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratBedaNamaPNSFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("23")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "23");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratBedaNIKFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("24")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "24");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratSKTMRSFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("25")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "25");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratNikahN2Fragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("26")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "26");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratNikahN4Fragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("27")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "27");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKeteranganBelumMenikah1Fragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("28")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "28");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKeteranganBelumMenikah2Fragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("29")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "29");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKeteranganBedaDomisiliSementaraFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("30")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "30");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratPernyataanIzinDomisiliUsahaFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("35")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "35");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKeteranganTidakBekerjaFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("36")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "36");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKeteranganUsaha();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("41")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "41");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratSKKMBPJSFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", clickedItems);
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new DataFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }

                }catch (Exception e){
                    
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBersangkutan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!Level.equals("member")){
                    try {
                        DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                        String clickedItems = clickedItem.getId_user();
                        ber.setText(clickedItems);
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void GetSubKategori(String id){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> getProvinsi = api.SubKatSurat(destiny.AUTH(),destiny.Kunci(),id);
        getProvinsi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    AdapterListSubKatSurat adapter = new AdapterListSubKatSurat(InputSuratActivity.this,mItems);
                    spinnerSub.setAdapter(adapter);
                }catch (Exception e){
//                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(),"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetKategori(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> getProvinsi = api.Kategori_Surat(destiny.AUTH(),destiny.Kunci());
        getProvinsi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    AdapterKategoriSurat adapter = new AdapterKategoriSurat(InputSuratActivity.this,mItems);
                    spinner.setAdapter(adapter);
                }catch (Exception e){
//                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(),"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetOrangBersangkutan(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> getProvinsi = api.ListUserRT(destiny.AUTH(),destiny.Kunci(),ID);
        getProvinsi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    AdapterListUserRT adapter = new AdapterListUserRT(InputSuratActivity.this,mItems);
                    spinnerBersangkutan.setAdapter(adapter);
                }catch (Exception e){
//                    Toast.makeText(getActivity(), "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(),"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
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