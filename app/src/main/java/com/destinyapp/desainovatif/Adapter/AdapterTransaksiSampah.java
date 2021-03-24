package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterTransaksiSampah extends RecyclerView.Adapter<AdapterTransaksiSampah.HolderData>{
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterTransaksiSampah(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterTransaksiSampah.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_transaksi,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        holderData.Tanggal.setText(destiny.MagicDateChange(dm.getTgl_transaksi_bs()));
        holderData.Tempat.setText(dm.getNama_lokasi_bs());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Tanggal,Tempat;
        Button Status;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Tempat = v.findViewById(R.id.tvLokasi);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

