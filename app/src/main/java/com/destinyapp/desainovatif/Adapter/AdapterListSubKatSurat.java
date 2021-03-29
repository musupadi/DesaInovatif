package com.destinyapp.desainovatif.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterListSubKatSurat extends ArrayAdapter<DataModel> {

    public AdapterListSubKatSurat(Context context, List<DataModel> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_spinner, parent, false
            );
        }

        TextView textSpinner = convertView.findViewById(R.id.tvSpinner);
        TextView textId = convertView.findViewById(R.id.tvId);
        TextView textWeb = convertView.findViewById(R.id.tvWeb);
        DataModel currentItem = getItem(position);

        if (currentItem != null) {
            textSpinner.setText(" " + currentItem.getNama_surat_kategori_sub());
            textId.setText(String.valueOf(currentItem.getId_surat_kategori_sub()));
            textWeb.setText(String.valueOf(currentItem.getSyarat_sub_kategori()));
        }

        return convertView;
    }
}
