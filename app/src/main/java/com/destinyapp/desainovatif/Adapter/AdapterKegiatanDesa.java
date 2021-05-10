package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Activity.ui.Menu.Toko.DetailProductActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.Model.NewModel.Data;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterKegiatanDesa extends RecyclerView.Adapter<AdapterKegiatanDesa.HolderData>{
    private Context ctx;
    String data[];
    Destiny destiny;
    public AdapterKegiatanDesa(Context ctx, String data[]){
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterKegiatanDesa.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_kegiatan_desa,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        holderData.Nama.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Nama;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvKegiatan);
            Image = v.findViewById(R.id.ivKegiatan);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

