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
import android.widget.Toast;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.HomeActivity;
import com.destinyapp.desainovatif.Activity.MainActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataFragment extends Fragment {

    //Cut Here
    Button Submit;
    Destiny destiny;
    //Main
    EditText NamaSurat, NoteSurat;
    String IDS;
    DB_Helper dbHelper;
    String Username,Password,Namas,Photo,ID,ID_Desa,Level;
    //Cut Here
    public DataFragment() {
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
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Cut Here
        destiny = new Destiny();
        Bundle bundle = getArguments();
        IDS = bundle.getString("ID");
        Submit = view.findViewById(R.id.btnSubmit);
        NamaSurat = view.findViewById(R.id.etNamaSurat);
        NoteSurat = view.findViewById(R.id.etNoteSurat);
        //DB Helper
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Namas = cursor.getString(2);
                Photo = cursor.getString(3);
                ID = cursor.getString(4);
                ID_Desa = cursor.getString(5);
                Level = cursor.getString(6);
            }
        }
        //Cut Here
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
            }
        });


    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mengirimkan Permintaan Surat");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        final Call<Ress> data =api.PostSuratData(destiny.AUTH(),destiny.Kunci(),ID,ID_Desa,IDS,"0",NamaSurat.getText().toString(),NoteSurat.getText().toString());
        data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                pd.hide();
                try {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
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
}