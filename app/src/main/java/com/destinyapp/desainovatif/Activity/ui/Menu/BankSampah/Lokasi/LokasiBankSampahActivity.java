package com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Lokasi;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
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

public class LokasiBankSampahActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<DataModel> mItems = new ArrayList<>();
    Destiny destiny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_bank_sampah);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        destiny=new Destiny();
        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(-6.2468965, 106.85238361358644);
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.bogor_maju);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b,1,1,false);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title("Bogor")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12F));
        googleMap.addMarker(markerOptions);
        SuperLogic(mMap);
    }
    private void SuperLogic(final GoogleMap googleMap){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> ListBS = api.LokasiBankSampah(destiny.AUTH(),destiny.Kunci(),"2");
        ListBS.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int w = 0;
                mItems = response.body().getData();
                while (w < mItems.size()) {
                    final DataModel dm = mItems.get(w);
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.bogor_maju);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(dm.getLatitude_bs()),Double.parseDouble(dm.getLongitude_bs())))
                            .anchor(0.5f,0.5f)
                            .title(dm.getNama_lokasi_bs())
                            .snippet(dm.getNama_lokasi_bs())
                            .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            Toast.makeText(LokasiBankSampahActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LokasiBankSampahActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}