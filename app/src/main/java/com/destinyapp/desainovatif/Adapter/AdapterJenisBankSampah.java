package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Activity.ui.Menu.Pariwisata.DetailPariwisataActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterJenisBankSampah extends RecyclerView.Adapter<AdapterJenisBankSampah.HolderData>{
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterJenisBankSampah(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterJenisBankSampah.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_jenis_sampah,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        holderData.Nama.setText(dm.getNama_jenis_sampah());
        holderData.Kode.setText(dm.getKode_jenis_sampah());
        holderData.Satuan.setText(dm.getSatuan_jenis_sampah());
        holderData.Harga.setText(dm.getHarga_jenis_sampah());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Nama,Kode,Satuan,Harga;
        Button Status;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNamaJenis);
            Kode = v.findViewById(R.id.tvKodeJenis);
            Satuan = v.findViewById(R.id.tvSatuanJenis);
            Harga = v.findViewById(R.id.tvHargaJenis);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

