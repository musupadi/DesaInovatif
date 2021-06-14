package com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Lokasi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.ui.Menu.Laporan.LaporanActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LokasiSampah extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<DataModel> mItems = new ArrayList<>();
    String Username,Password,Nama,Photo,ID,ID_Desa;
    DB_Helper dbHelper;
    Destiny destiny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_sampah);
        destiny=new Destiny();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(-6.669466827425746, 106.85238361358644);
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.bogor_maju);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b,1,1,false);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title("Bogor")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12F));
        googleMap.addMarker(markerOptions);

        dbHelper = new DB_Helper(LokasiSampah.this);
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
        }
        SuperLogic(mMap);
    }
    private void SuperLogic(final GoogleMap googleMap){
        mMap = googleMap;
        mMap.clear();
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> ListBS = api.LokasiBankSampah(destiny.AUTH(),destiny.Kunci(),ID_Desa);
        ListBS.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int w = 0;
                List<DataModel> mItems = new ArrayList<>();
                if (response.isSuccessful()){
                    mItems = response.body().getData();
                    while (w < mItems.size()) {
                        final DataModel dm = mItems.get(w);
                        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.bogor_maju);
                        Bitmap b=bitmapdraw.getBitmap();
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b,100,100,false);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(Double.parseDouble(dm.getLatitude_bs()),Double.parseDouble(dm.getLongitude_bs())))
                                .anchor(0.5f,0.5f)
                                .title(dm.getNama_lokasi_bs())
                                .snippet(dm.getNama_lokasi_bs())
                                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Toast.makeText(LokasiSampah.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });
                        w++;
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LokasiSampah.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}