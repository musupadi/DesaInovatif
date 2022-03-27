package com.destinyapp.desainovatif.Activity.ui.Menu.Surat.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.ResponseData;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratSKKMBPJSFragment extends Fragment {
    Button Submit;
    EditText NamaSurat,NoteSurat;
    LinearLayout LinearWarga1,LinearWarga2,LinearWarga3,LinearWarga4,LinearWarga5,
            LinearWarga6,LinearWarga7,LinearWarga8,LinearWarga9,LinearWarga10;
    Button Tambah2,Tambah3,Tambah4,Tambah5,Tambah6,Tambah7,Tambah8,Tambah9,Tambah10;
    EditText Nama1,Nama2,Nama3,Nama4,Nama5,Nama6,Nama7,Nama8,Nama9,Nama10;
    EditText NIK1,NIK2,NIK3,NIK4,NIK5,NIK6,NIK7,NIK8,NIK9,NIK10;
    EditText Alamat1,Alamat2,Alamat3,Alamat4,Alamat5,Alamat6,Alamat7,Alamat8,Alamat9,Alamat10;
    EditText Keterangan1,Keterangan2,Keterangan3,Keterangan4,Keterangan5,Keterangan6,Keterangan7,Keterangan8,Keterangan9,Keterangan10;


    ArrayList<String> Nama = new ArrayList<String>();
    ArrayList<String> NIK = new ArrayList<String>();
    ArrayList<String> Alamat = new ArrayList<String>();
    ArrayList<String> Keterangan = new ArrayList<String>();
    Destiny destiny = new Destiny();
    int Warga=1;
    DB_Helper dbHelper;
    String Username,Password,Namas,Photo,ID,ID_Desa,IDS;
    public SuratSKKMBPJSFragment() {
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
        return inflater.inflate(R.layout.fragment_surat_s_k_k_m_b_p_j_s, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper = new DB_Helper(getActivity());
        Bundle bundle = getArguments();
        IDS = bundle.getString("ID");
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Namas = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_Desa = cursor.getString(5);
            }
        }
        Submit = view.findViewById(R.id.btnSubmit);
        NamaSurat = view.findViewById(R.id.etNamaSurat);
        NoteSurat = view.findViewById(R.id.etNoteSurat);
        //Warga1
        LinearWarga1 = view.findViewById(R.id.linearDataWarga1);
        Nama1 = view.findViewById(R.id.etNama1);
        NIK1 = view.findViewById(R.id.etNIK1);
        Alamat1 = view.findViewById(R.id.etAlamat1);
        Keterangan1 = view.findViewById(R.id.etKeterangan1);
        //Warga2
        LinearWarga2 = view.findViewById(R.id.linearDataWarga2);
        Nama2 = view.findViewById(R.id.etNama2);
        NIK2 = view.findViewById(R.id.etNIK2);
        Alamat2 = view.findViewById(R.id.etAlamat2);
        Keterangan2 = view.findViewById(R.id.etKeterangan2);
        Tambah2 = view.findViewById(R.id.btnTambah2);
        //Warga3
        LinearWarga3 = view.findViewById(R.id.linearDataWarga3);
        Nama3 = view.findViewById(R.id.etNama3);
        NIK3 = view.findViewById(R.id.etNIK3);
        Alamat3 = view.findViewById(R.id.etAlamat3);
        Keterangan3 = view.findViewById(R.id.etKeterangan3);
        Tambah3 = view.findViewById(R.id.btnTambah3);
        //Warga4
        LinearWarga4 = view.findViewById(R.id.linearDataWarga4);
        Nama4 = view.findViewById(R.id.etNama4);
        NIK4 = view.findViewById(R.id.etNIK4);
        Alamat4 = view.findViewById(R.id.etAlamat4);
        Keterangan4 = view.findViewById(R.id.etKeterangan4);
        Tambah4 = view.findViewById(R.id.btnTambah4);
        //Warga5
        LinearWarga5 = view.findViewById(R.id.linearDataWarga5);
        Nama5 = view.findViewById(R.id.etNama5);
        NIK5 = view.findViewById(R.id.etNIK5);
        Alamat5 = view.findViewById(R.id.etAlamat5);
        Keterangan5 = view.findViewById(R.id.etKeterangan5);
        Tambah5 = view.findViewById(R.id.btnTambah5);
        //Warga6
        LinearWarga6 = view.findViewById(R.id.linearDataWarga6);
        Nama6 = view.findViewById(R.id.etNama6);
        NIK6 = view.findViewById(R.id.etNIK6);
        Alamat6 = view.findViewById(R.id.etAlamat6);
        Keterangan6 = view.findViewById(R.id.etKeterangan6);
        Tambah6 = view.findViewById(R.id.btnTambah6);
        //Warga7
        LinearWarga7 = view.findViewById(R.id.linearDataWarga7);
        Nama7 = view.findViewById(R.id.etNama7);
        NIK7 = view.findViewById(R.id.etNIK7);
        Alamat7 = view.findViewById(R.id.etAlamat7);
        Keterangan7 = view.findViewById(R.id.etKeterangan7);
        Tambah7 = view.findViewById(R.id.btnTambah7);
        //Warga8
        LinearWarga8 = view.findViewById(R.id.linearDataWarga8);
        Nama8 = view.findViewById(R.id.etNama8);
        NIK8 = view.findViewById(R.id.etNIK8);
        Alamat8 = view.findViewById(R.id.etAlamat8);
        Keterangan8 = view.findViewById(R.id.etKeterangan8);
        Tambah8 = view.findViewById(R.id.btnTambah8);
        //Warga9
        LinearWarga9 = view.findViewById(R.id.linearDataWarga9);
        Nama9 = view.findViewById(R.id.etNama9);
        NIK9 = view.findViewById(R.id.etNIK9);
        Alamat9 = view.findViewById(R.id.etAlamat9);
        Keterangan9 = view.findViewById(R.id.etKeterangan9);
        Tambah9 = view.findViewById(R.id.btnTambah9);
        //Warga10
        LinearWarga10 = view.findViewById(R.id.linearDataWarga10);
        Nama10 = view.findViewById(R.id.etNama10);
        NIK10 = view.findViewById(R.id.etNIK10);
        Alamat10 = view.findViewById(R.id.etAlamat10);
        Keterangan10 = view.findViewById(R.id.etKeterangan10);
        Tambah10 = view.findViewById(R.id.btnTambah10);


        Tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga2.setVisibility(View.VISIBLE);
                Tambah2.setVisibility(View.GONE);
                Tambah3.setVisibility(View.VISIBLE);
                Warga=2;
            }
        });
        Tambah3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga3.setVisibility(View.VISIBLE);
                Tambah3.setVisibility(View.GONE);
                Tambah4.setVisibility(View.VISIBLE);
                Warga=3;
            }
        });
        Tambah4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga4.setVisibility(View.VISIBLE);
                Tambah4.setVisibility(View.GONE);
                Tambah5.setVisibility(View.VISIBLE);
                Warga=4;
            }
        });
        Tambah5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga5.setVisibility(View.VISIBLE);
                Tambah5.setVisibility(View.GONE);
                Tambah6.setVisibility(View.VISIBLE);
                Warga=5;
            }
        });
        Tambah6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga6.setVisibility(View.VISIBLE);
                Tambah6.setVisibility(View.GONE);
                Tambah7.setVisibility(View.VISIBLE);
                Warga=6;
            }
        });
        Tambah6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga5.setVisibility(View.VISIBLE);
                Tambah6.setVisibility(View.GONE);
                Tambah7.setVisibility(View.VISIBLE);
                Warga=6;
            }
        });
        Tambah7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga7.setVisibility(View.VISIBLE);
                Tambah7.setVisibility(View.GONE);
                Tambah8.setVisibility(View.VISIBLE);
                Warga=7;
            }
        });
        Tambah8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga8.setVisibility(View.VISIBLE);
                Tambah8.setVisibility(View.GONE);
                Tambah9.setVisibility(View.VISIBLE);
                Warga=8;
            }
        });
        Tambah9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga9.setVisibility(View.VISIBLE);
                Tambah9.setVisibility(View.GONE);
                Tambah10.setVisibility(View.VISIBLE);
                Warga=9;
            }
        });
        Tambah10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearWarga10.setVisibility(View.VISIBLE);
                Tambah10.setVisibility(View.GONE);
                Warga=10;
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
            }
        });
    }
    private void Logic(){
        Input();
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mengirimkan Permintaan Surat");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        final Call<Ress> data =api.PostSuratSKKMBPJS(destiny.AUTH(),destiny.Kunci(),ID,ID_Desa,IDS,"0",NamaSurat.getText().toString(),NoteSurat.getText().toString(),Nama,NIK,Alamat,Keterangan);
        data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
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
    private void Input(){
        if (Warga==1){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());
        }else if (Warga==2){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());
        }else if (Warga==3){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());
        }else if (Warga==4){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());
        }else if (Warga==5){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());

            Nama.add(Nama5.getText().toString());
            NIK.add(NIK5.getText().toString());
            Alamat.add(Alamat5.getText().toString());
            Keterangan.add(Alamat5.getText().toString());
        }else if (Warga==6){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());

            Nama.add(Nama5.getText().toString());
            NIK.add(NIK5.getText().toString());
            Alamat.add(Alamat5.getText().toString());
            Keterangan.add(Alamat5.getText().toString());

            Nama.add(Nama6.getText().toString());
            NIK.add(NIK6.getText().toString());
            Alamat.add(Alamat6.getText().toString());
            Keterangan.add(Alamat6.getText().toString());
        }else if (Warga==7){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());

            Nama.add(Nama5.getText().toString());
            NIK.add(NIK5.getText().toString());
            Alamat.add(Alamat5.getText().toString());
            Keterangan.add(Alamat5.getText().toString());

            Nama.add(Nama6.getText().toString());
            NIK.add(NIK6.getText().toString());
            Alamat.add(Alamat6.getText().toString());
            Keterangan.add(Alamat6.getText().toString());

            Nama.add(Nama7.getText().toString());
            NIK.add(NIK7.getText().toString());
            Alamat.add(Alamat7.getText().toString());
            Keterangan.add(Alamat7.getText().toString());
        }else if (Warga==8){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());

            Nama.add(Nama5.getText().toString());
            NIK.add(NIK5.getText().toString());
            Alamat.add(Alamat5.getText().toString());
            Keterangan.add(Alamat5.getText().toString());

            Nama.add(Nama6.getText().toString());
            NIK.add(NIK6.getText().toString());
            Alamat.add(Alamat6.getText().toString());
            Keterangan.add(Alamat6.getText().toString());

            Nama.add(Nama7.getText().toString());
            NIK.add(NIK7.getText().toString());
            Alamat.add(Alamat7.getText().toString());
            Keterangan.add(Alamat7.getText().toString());

            Nama.add(Nama8.getText().toString());
            NIK.add(NIK8.getText().toString());
            Alamat.add(Alamat8.getText().toString());
            Keterangan.add(Alamat8.getText().toString());
        }else if (Warga==9){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());

            Nama.add(Nama5.getText().toString());
            NIK.add(NIK5.getText().toString());
            Alamat.add(Alamat5.getText().toString());
            Keterangan.add(Alamat5.getText().toString());

            Nama.add(Nama6.getText().toString());
            NIK.add(NIK6.getText().toString());
            Alamat.add(Alamat6.getText().toString());
            Keterangan.add(Alamat6.getText().toString());

            Nama.add(Nama7.getText().toString());
            NIK.add(NIK7.getText().toString());
            Alamat.add(Alamat7.getText().toString());
            Keterangan.add(Alamat7.getText().toString());

            Nama.add(Nama8.getText().toString());
            NIK.add(NIK8.getText().toString());
            Alamat.add(Alamat8.getText().toString());
            Keterangan.add(Alamat8.getText().toString());

            Nama.add(Nama9.getText().toString());
            NIK.add(NIK9.getText().toString());
            Alamat.add(Alamat9.getText().toString());
            Keterangan.add(Alamat9.getText().toString());
        }else if (Warga==10){
            Nama.add(Nama1.getText().toString());
            NIK.add(NIK1.getText().toString());
            Alamat.add(Alamat1.getText().toString());
            Keterangan.add(Alamat1.getText().toString());

            Nama.add(Nama2.getText().toString());
            NIK.add(NIK2.getText().toString());
            Alamat.add(Alamat2.getText().toString());
            Keterangan.add(Alamat2.getText().toString());

            Nama.add(Nama3.getText().toString());
            NIK.add(NIK3.getText().toString());
            Alamat.add(Alamat3.getText().toString());
            Keterangan.add(Alamat3.getText().toString());

            Nama.add(Nama4.getText().toString());
            NIK.add(NIK4.getText().toString());
            Alamat.add(Alamat4.getText().toString());
            Keterangan.add(Alamat4.getText().toString());

            Nama.add(Nama5.getText().toString());
            NIK.add(NIK5.getText().toString());
            Alamat.add(Alamat5.getText().toString());
            Keterangan.add(Alamat5.getText().toString());

            Nama.add(Nama6.getText().toString());
            NIK.add(NIK6.getText().toString());
            Alamat.add(Alamat6.getText().toString());
            Keterangan.add(Alamat6.getText().toString());

            Nama.add(Nama7.getText().toString());
            NIK.add(NIK7.getText().toString());
            Alamat.add(Alamat7.getText().toString());
            Keterangan.add(Alamat7.getText().toString());

            Nama.add(Nama8.getText().toString());
            NIK.add(NIK8.getText().toString());
            Alamat.add(Alamat8.getText().toString());
            Keterangan.add(Alamat8.getText().toString());

            Nama.add(Nama9.getText().toString());
            NIK.add(NIK9.getText().toString());
            Alamat.add(Alamat9.getText().toString());
            Keterangan.add(Alamat9.getText().toString());

            Nama.add(Nama10.getText().toString());
            NIK.add(NIK10.getText().toString());
            Alamat.add(Alamat10.getText().toString());
            Keterangan.add(Alamat10.getText().toString());
        }
    }
}