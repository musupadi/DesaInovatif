package com.destinyapp.desainovatif.Adapter.Spinner;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.destinyapp.desainovatif.Model.DataModel;
import com.destinyapp.desainovatif.R;

import java.util.List;

public class AdapterKegiatan extends ArrayAdapter<String> {
    public AdapterKegiatan(Context context, String[] data) {
        super(context, 0, data);
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
        String currentItem = getItem(position);
        if (currentItem != null) {
            textSpinner.setText(currentItem);
        }

        return convertView;
    }
}
