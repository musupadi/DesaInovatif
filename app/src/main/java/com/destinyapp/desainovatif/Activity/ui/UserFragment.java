package com.destinyapp.desainovatif.Activity.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.LoginActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Laporan.LaporanActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    ImageView Picture;
    TextView nama,email,pekerjaan,saldo;
    DB_Helper dbHelper;
    Dialog myDialog;
    EditText oldPassword,newPassword,newPassword2,saran;
    String Username,Password,Nama,Photo,Email,ID;
    Destiny destiny;
    Button submit;
    Button logout,Laporan,UbahPassword;
    public UserFragment() {
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = view.findViewById(R.id.tvNama);
        saldo = view.findViewById(R.id.tvSaldo);
        pekerjaan = view.findViewById(R.id.tvPekerjaan);
        email = view.findViewById(R.id.tvEmail);
        Picture = view.findViewById(R.id.ivProfile);
        logout = view.findViewById(R.id.btnLogot);
        Laporan = view.findViewById(R.id.btnLaporan);
        UbahPassword = view.findViewById(R.id.btnUbahPassword);

        //Dialog Change Password
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_change_password);
        oldPassword=myDialog.findViewById(R.id.etPasswordOld);
        newPassword=myDialog.findViewById(R.id.etPassword);
        newPassword2=myDialog.findViewById(R.id.etPassword2);
        submit=myDialog.findViewById(R.id.changePassword);

        dbHelper = new DB_Helper(getActivity());
        destiny = new Destiny();
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
            }
        }
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> User = api.user_detail(destiny.AUTH(),destiny.Kunci(),ID);
        User.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                if (!Photo.isEmpty()){
                    Glide.with(getActivity())
                            .load(destiny.BASE_URL()+Photo)
                            .into(Picture);
                }
                nama.setText(response.body().getData().getNama_user());
                email.setText(response.body().getData().getEmail_user());
                pekerjaan.setText(response.body().getData().getPekerjaan_user());
                saldo.setText(response.body().getData().getSaldo_user());
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imam_Kontol();
            }
        });

        Laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LaporanActivity.class);
                startActivity(intent);
            }
        });
        UbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Kata Sandi Lama Kosong", Toast.LENGTH_SHORT).show();
                }else if (newPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Kata Sandi Baru Kosong", Toast.LENGTH_SHORT).show();
                }else if(newPassword2.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Konfirmasi Kata Sandi baru Kosong", Toast.LENGTH_SHORT).show();
                }else if(!newPassword.getText().toString().equals(newPassword2.getText().toString())){
                    Toast.makeText(getActivity(), "Kata Sandi baru dan Konfirmasi Kata Sandi Baru Berbeda", Toast.LENGTH_SHORT).show();
                }else{
                    ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
                    Call<ResponseModel> logic = api.ChangePassword(destiny.AUTH(),
                            "hazxclkadkSA0Ijsad20sl02335sjlso20",
                            ID,
                            oldPassword.getText().toString(),
                            newPassword2.getText().toString(),
                            newPassword.getText().toString());
                    logic.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if (response.body().getMessage().equals("sukses")){
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                myDialog.hide();
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.Logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Akun Anda Telah Berhasil Keluar", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
            }
        });
    }
    private void Imam_Kontol(){

    }
}