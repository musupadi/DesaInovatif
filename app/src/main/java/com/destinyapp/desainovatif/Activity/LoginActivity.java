package com.destinyapp.desainovatif.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LinearLayout login;
    DB_Helper dbHelper;
    EditText user,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Declaration();
        OnClick();
    }
    private void Declaration(){
        login = findViewById(R.id.linearLogin);
        user = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        dbHelper = new DB_Helper(LoginActivity.this);
    }
    private void OnClick(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }
    private void Login(){
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Sedang Mencoba Login");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(user.getText().toString(),password.getText().toString());
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        dbHelper.SaveUser(response.body().getData().get(0).getUsernameUser(),password.getText().toString(),response.body().getData().get(0).getNamaUser(),response.body().getData().get(0).getAccessToken(),response.body().getData().get(0).getFotoUser(),response.body().getData().get(0).getEmailUser());
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                    pd.hide();
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                pd.hide();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
}