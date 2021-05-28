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
import com.destinyapp.desainovatif.Activity.ui.Menu.Berita.DetailBeritaActivity;
import com.destinyapp.desainovatif.Activity.ui.Menu.KemajuanDesa.DetailKemajuanDesa;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterKemajuanDesa extends RecyclerView.Adapter<AdapterKemajuanDesa.HolderData>{
    private Context ctx;
    private List<DataModel> mList;
    Destiny destiny;
    public AdapterKemajuanDesa(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterKemajuanDesa.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_kemajuan_desa,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        holderData.Nama.setText(dm.getNama_kemajuan_desa());
        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getDeskripsi_kemajuan_desa())));
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getCover_kemajuan_desa())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailKemajuanDesa.class);
                i.putExtra("JUDUL", dm.getNama_kemajuan_desa());
                i.putExtra("ISI",dm.getDeskripsi_kemajuan_desa());
                i.putExtra("TANGGAL",dm.getTgl_kemajuan_desa());
                i.putExtra("GAMBAR", dm.getCover_kemajuan_desa());
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
        TextView Nama,Deskripsi;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Image = v.findViewById(R.id.ivCover);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

