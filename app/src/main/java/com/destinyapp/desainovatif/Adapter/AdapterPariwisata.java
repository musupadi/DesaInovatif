package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Activity.ui.Menu.Berita.DetailBeritaActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.Pariwisata.DetailPariwisataActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterPariwisata extends RecyclerView.Adapter<AdapterPariwisata.HolderData>{
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterPariwisata(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterPariwisata.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_pariwisata,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        holderData.Nama.setText(dm.getNama_pariwisata());
        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getDeskripsi_pariwisata())));
        holderData.Desa.setText(dm.getNama_desa());
        if (dm.getStatus_pariwisata().equals("buka")){
            holderData.Status.setBackgroundColor(Color.rgb(83,210,220));
        }else{
            holderData.Status.setBackgroundColor(Color.rgb(255,0,0));
        }
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getCover_pariwisata())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, DetailPariwisataActivity.class);
                i.putExtra("NAMA", dm.getNama_pariwisata());
                i.putExtra("ALAMAT",dm.getAlamat_pariwisata());
                i.putExtra("DESKRIPSI",dm.getDeskripsi_pariwisata());
                i.putExtra("HARGA",dm.getDeskripsi_harga());
                i.putExtra("GAMBAR", destiny.BASE_URL()+dm.getCover_pariwisata());
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
        TextView Nama,Deskripsi,Desa;
        Button Status;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNamaPariwisata);
            Image = v.findViewById(R.id.ivCover);
            Deskripsi = v.findViewById(R.id.tvDeskripsiPariwisata);
            Desa = v.findViewById(R.id.tvNamaDesa);
            Status = v.findViewById(R.id.btnStatus);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
