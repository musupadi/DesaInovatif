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

public class AdapterHistoryRewardBS extends RecyclerView.Adapter<AdapterHistoryRewardBS.HolderData>{
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterHistoryRewardBS(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterHistoryRewardBS.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_history_bs,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        destiny = new Destiny();
        final DataModel dm = mList.get(position);
        if (destiny.Dates(dm.getTgl_ambil_reward()).equals("Nope")){
            holderData.Ambil.setText("Belum Diambil");
        }else{
            holderData.Ambil.setText(destiny.MagicDateChange(dm.getTgl_ambil_reward()));
        }
        holderData.Nama.setText(dm.getNama_reward());
        holderData.Total.setText("Rp "+destiny.MagicNumber(Double.parseDouble(dm.getTotal_nilai())));
        holderData.Klaim.setText(destiny.MagicDateChange(dm.getTgl_klaim_reward()));
        holderData.Status.setText(dm.getStatus_transaksi_reward());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Nama,Total,Klaim,Ambil,Status;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Total = v.findViewById(R.id.tvNilai);
            Klaim = v.findViewById(R.id.tvTglKlaimReward);
            Ambil = v.findViewById(R.id.tvTglAmbilReward);
            Status = v.findViewById(R.id.tvStatus);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}


