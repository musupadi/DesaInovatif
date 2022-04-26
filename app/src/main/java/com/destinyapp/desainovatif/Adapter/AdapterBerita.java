package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.desainovatif.Activity.ui.Menu.Berita.DetailBeritaActivity;
import com.destinyapp.desainovatif.Method.Destiny;
import com.destinyapp.desainovatif.Model.NewModel.Data;
import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.HolderData> {
    private List<Data> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterBerita(Context ctx, List<Data> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_berita,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final Data dm = mList.get(posistion);
        holderData.Judul.setText(dm.getTitle().getRendered());
//        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getContent().getRendered())));
//        holderData.web.loadData(dm.getContent().getRendered(),"text/html","UTF-8");
        holderData.Tanggal.setText(destiny.MagicDateChange(dm.getDate()));
        Glide.with(ctx)
                .load(dm.getImg_cover())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, DetailBeritaActivity.class);
                i.putExtra("JUDUL", dm.getTitle().getRendered());
                i.putExtra("ISI",dm.getContent().getRendered());
                i.putExtra("TANGGAL",dm.getDate());
                i.putExtra("GAMBAR", dm.getImg_cover());
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
        TextView Judul,Deskripsi,Tanggal;
        WebView web;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvJudul);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            Tanggal = v.findViewById(R.id.tvTanggal);
            web = v.findViewById(R.id.webDeskripsi);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
