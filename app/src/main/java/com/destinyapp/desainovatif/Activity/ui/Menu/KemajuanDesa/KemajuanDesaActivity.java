package com.destinyapp.desainovatif.Activity.ui.Menu.KemajuanDesa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Adapter.AdapterKemajuanDesa;
import com.destinyapp.desainovatif.Adapter.Spinner.AdapterKegiatan;
import com.destinyapp.desainovatif.Adapter.Spinner.AdapterListUserRW;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.KontolFajar;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KemajuanDesaActivity extends AppCompatActivity {
    Destiny destiny;
    DB_Helper dbHelper;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    String data[];
    RecyclerView recyclerView;
    Spinner RW,Kegiatan;
    private List<DataModel> mItems = new ArrayList<>();
    String Username,Password,Nama,Photo,ID,ID_Desa,Level;
    TextView ID_RW;
    Boolean R_W=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kemajuan_desa);
        destiny = new Destiny();
        //DB Helper
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
        recyclerView = findViewById(R.id.recycler);
        Kegiatan = findViewById(R.id.spKegiatan);
        ID_RW = findViewById(R.id.tvIDRW);
        RW  = findViewById(R.id.spRW);
        RW.setVisibility(View.GONE);
//        getKegiatan();
        Toast.makeText(KemajuanDesaActivity.this, Username, Toast.LENGTH_SHORT).show();
        Kegiatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    R_W = false;
                    RW.setVisibility(View.GONE);
                }else{
                    R_W = true;
                    RW.setVisibility(View.VISIBLE);
                    getRW();
                    RW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                           try {
                               DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                               String clickedItems = clickedItem.getId_rw();
                               ID_RW.setText(clickedItems);
                           }catch (Exception e){

                           }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                DATA();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void getKegiatan(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<KontolFajar> Data=api.GetKegiatan();
        Data.enqueue(new Callback<KontolFajar>() {
            @Override
            public void onResponse(Call<KontolFajar> call, Response<KontolFajar> response) {
                data=response.body().getData();
//                Toast.makeText(KemajuanDesaActivity.this, String.valueOf(data.length), Toast.LENGTH_SHORT).show();
                AdapterKegiatan adapter = new AdapterKegiatan(KemajuanDesaActivity.this,data);
                Kegiatan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KontolFajar> call, Throwable t) {
                Toast.makeText(KemajuanDesaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getRW(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.ListRW(ID_Desa);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                mItems=response.body().getData();
                AdapterListUserRW adapter = new AdapterListUserRW(KemajuanDesaActivity.this,mItems);
                RW.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(KemajuanDesaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DATA(){
        String available="";
        if (R_W){
            available=ID_RW.getText().toString();
        }
        mManager = new GridLayoutManager(KemajuanDesaActivity.this,1);
        recyclerView.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.ListKemajuanDesa(ID_Desa,Kegiatan.getSelectedItem().toString(),available);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                mItems=response.body().getData();
                mAdapter = new AdapterKemajuanDesa(KemajuanDesaActivity.this,mItems);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(KemajuanDesaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}