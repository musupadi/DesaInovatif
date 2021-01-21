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
import com.destinyapp.desainovatif.Activity.ui.Menu.Toko.DetailTokoActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterTokoSaya extends RecyclerView.Adapter<AdapterTokoSaya.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterTokoSaya(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterTokoSaya.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_toko_saya,viewGroup,false);
        AdapterTokoSaya.HolderData holder = new AdapterTokoSaya.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterTokoSaya.HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getNama_toko());
        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getDeskripsi_toko())));
        if (dm.getStatus_toko().equals("buka")){
            holderData.status.setText("Buka");
            holderData.status.setBackgroundResource(R.drawable.round_primary);
        }else if(dm.getStatus_toko().equals("tutup")){
            holderData.status.setText("Tutup");
            holderData.status.setBackgroundResource(R.drawable.round_red);
        }else{
            holderData.status.setText("Pending");
            holderData.status.setBackgroundResource(R.drawable.round_secondary);
        }
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getLink_file_toko_foto())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailTokoActivity.class);
                i.putExtra("ID_TOKO", dm.getId_toko());
                i.putExtra("NAMA_TOKO",dm.getNama_toko());
                i.putExtra("DESKRIPSI_TOKO",dm.getDeskripsi_toko());
                i.putExtra("STATUS_TOKO",dm.getStatus_toko());
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
        TextView Judul,Deskripsi;
        Button status;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivCover);
            Judul = v.findViewById(R.id.tvNamaToko);
            Deskripsi = v.findViewById(R.id.tvDeskripsiToko);
            status = v.findViewById(R.id.btnStatus);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
