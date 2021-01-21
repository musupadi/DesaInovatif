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
import com.destinyapp.desainovatif.Activity.ui.Menu.Toko.DetailProductActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Toko.DetailTokoActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderData>{
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterProduk(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterProduk.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_produk,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        holderData.Nama.setText(dm.getNama_produk());
        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.deskripsi_produk)));
        Glide.with(ctx)
                .load(destiny.BASE_URL()+"/"+dm.getLink_file_produk_foto())
                .into(holderData.Image);
        holderData.harga.setText(destiny.MagicRP(Double.parseDouble(dm.getHarga_produk())));
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailProductActivity.class);
                i.putExtra("ID", dm.getId_toko_produk());
                i.putExtra("NAMA",dm.getNama_produk());
                i.putExtra("DESKRIPSI",dm.getDeskripsi_produk());
                i.putExtra("HARGA",dm.getHarga_produk());
                i.putExtra("STATUS",dm.getStatus_produk());
                i.putExtra("ID_USER",dm.getId_user());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Nama,Deskripsi,harga;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Image = v.findViewById(R.id.ivCover);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            harga = v.findViewById(R.id.tvHarga);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
