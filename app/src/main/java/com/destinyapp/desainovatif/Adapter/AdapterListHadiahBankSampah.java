package com.destinyapp.desainovatif.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.desainovatif.API.ApiRequest;
import com.destinyapp.desainovatif.API.RetroServer2;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.BankSampah;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah.AmbilReward.AmbilRewardActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.BankSampah.Hadiah.DaftarRewardActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListHadiahBankSampah extends RecyclerView.Adapter<AdapterListHadiahBankSampah.HolderData>{
    private List<DataModel> mList;
    private Context ctx;
    String Username,Password,Nama,Photo,ID,ID_Desa,Level;
    DB_Helper dbHelper;
    Destiny destiny;
    RecyclerView recyclerView;
    TextView Saldo;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    public AdapterListHadiahBankSampah(Context ctx, List<DataModel> mList,RecyclerView recyclerView,TextView Saldo){
        this.ctx = ctx;
        this.mList = mList;
        this.recyclerView = recyclerView;
        this.Saldo = Saldo;
    }

    @NonNull
    @Override
    public AdapterListHadiahBankSampah.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_reward_bs,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        dbHelper = new DB_Helper(ctx);
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
        holderData.Nama.setText(dm.getNama_reward());
        holderData.Qty.setText(dm.getQty_reward());
        holderData.Deskripsi.setText("RP "+destiny.MagicNumber(Double.parseDouble(dm.getNilai_dibutuhkan())));
        if (Integer.parseInt(dm.getQty_reward()) < 3){
            holderData.Qty.setTextColor(Color.rgb(255,0,0));
            holderData.QtyTittle.setTextColor(Color.rgb(255,0,0));
        }else{
            holderData.Qty.setTextColor(Color.rgb(83,210,220));
            holderData.QtyTittle.setTextColor(Color.rgb(83,210,220));
        }
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KlaimHadiah(Integer.parseInt(dm.getNilai_dibutuhkan()),dm.getId_reward_bs(),dm.getNama_reward());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama,Deskripsi,QtyTittle,Qty;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            QtyTittle = v.findViewById(R.id.tvQtyTittle);
            Qty = v.findViewById(R.id.tvQty);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
    private void KlaimHadiah(int harga,String idReward,String namaReward){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> saldoBankSampah = api.SaldoBankSampah(destiny.AUTH(),destiny.Kunci(),ID);
        saldoBankSampah.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                if (harga>Integer.parseInt(destiny.ChangeNumToInt(response.body().getData().getSaldo_user()))){
                    Toast.makeText(ctx, "Saldo Anda Tidak Mencukupi", Toast.LENGTH_SHORT).show();
                }else{
                    int Total = Integer.parseInt(destiny.ChangeNumToInt(response.body().getData().getSaldo_user()))-harga;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                    // Set a title for alert dialog
                    builder.setTitle("Pemberitahuan");

                    // Ask the final question
                    builder.setMessage("Apakah anda yakin ingin mengkalim "+namaReward+" ?\nSaldo Anda saat ini adalah Rp "+destiny.MagicNumber(Double.parseDouble(destiny.ChangeNumToInt(response.body().getData().getSaldo_user())))+" dan akan menjadi Rp "
                            +String.valueOf(destiny.MagicNumber(Double.parseDouble(String.valueOf(Total))))+" Jika "+namaReward+" di klaim");

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when user clicked the Yes button
                            // Set the TextView visibility GONE
                            AmbilHadiah(idReward,"1",namaReward);
                        }
                    });

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AmbilHadiah(String idReward,String Qty,String namaReward){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<Ress> Data = api.AmbilReward(destiny.AUTH(),destiny.Kunci(),ID,idReward,Qty,ID_Desa);
        Data.enqueue(new Callback<Ress>() {
            @Override
            public void onResponse(Call<Ress> call, Response<Ress> response) {
                Toast.makeText(ctx, namaReward+" Berhasil di Klaim", Toast.LENGTH_SHORT).show();
                GetData();
                GetSaldo();
            }

            @Override
            public void onFailure(Call<Ress> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetSaldo(){
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<NewResponse> saldoBankSampah = api.SaldoBankSampah(destiny.AUTH(),destiny.Kunci(),ID);
        saldoBankSampah.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                Saldo.setText(response.body().getData().getSaldo_user());
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Saldo.setText("Error");
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetData(){
//        recyclerView.setLayoutManager(mManager);
        ApiRequest api = RetroServer2.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.ListRewardBankSampah(destiny.AUTH(),destiny.Kunci(),ID_Desa);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    mAdapter = new AdapterListHadiahBankSampah(ctx,mItems,recyclerView,Saldo);
                    recyclerView.setAdapter(mAdapter);
                }catch (Exception e){
                    Toast.makeText(ctx, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


