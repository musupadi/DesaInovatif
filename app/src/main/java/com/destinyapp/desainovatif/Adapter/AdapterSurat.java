package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterSurat extends RecyclerView.Adapter<AdapterSurat.HolderData>   {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterSurat(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_surat,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Nama.setText(dm.getNama_surat());
        holderData.Tanggal.setText(destiny.MagicDateChange(dm.getTgl_request()));
        if (dm.getStatus_surat().equals("requested")){
            holderData.Status.setText(" Meminta");
            holderData.Status.setTextColor(Color.rgb(255,227,179));
            holderData.StatusImage.setImageResource(R.drawable.round_secondary);
        }else if(dm.getStatus_surat().equals("terkirim")){
            holderData.Status.setText(" Terkirim");
            holderData.Status.setTextColor(Color.rgb(83,210,220));
            holderData.StatusImage.setImageResource(R.drawable.round_primary);
        }else{
            holderData.Status.setText(" Ditolak");
            holderData.Status.setTextColor(Color.rgb(255,0,0));
            holderData.StatusImage.setImageResource(R.drawable.round_red);
        }
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dm.getLink_file_surat().isEmpty()){
                    Toast.makeText(ctx, "File Surat Belum Ada", Toast.LENGTH_SHORT).show();
                }else{
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(destiny.BASE_URL()+dm.getLink_file_surat()));
                    ctx.startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Nama,Tanggal,Status;
        ImageView StatusImage;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.LayoutCardView);
            Status= v.findViewById(R.id.tvStatus);
            Tanggal = v.findViewById(R.id.tvTanggal);
            StatusImage = v.findViewById(R.id.ivStatus);
        }
    }
}
