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
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.ui.LaporanFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratAdministrasiumumFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKelahiranFragment;
import com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment.SuratKematianFragment;
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
        if (Level.equals("member")){
            lUntuk.setVisibility(View.GONE);
            lOrangBersangkutan.setVisibility(View.GONE);
        }
        GetKategori();
        GetOrangBersangkutan();
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
                        bundle.putString("ID", "ID");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKelahiranFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else if (clickedItems.equals("7")){
                        Bundle bundle = new Bundle();
                        bundle.putString("ID", "ID");
                        lSubSurat.setVisibility(View.GONE);
                        fragment = new SuratKematianFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        fragment = new SuratAdministrasiumumFragment();
                        ChangeFragment(fragment);
                        lSubSurat.setVisibility(View.VISIBLE);
                        GetSubKategori(kat.getText().toString());
                        spinnerSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                                String clickedItems = clickedItem.getId_surat_kategori_sub();
                                sub.setText(clickedItems);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
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