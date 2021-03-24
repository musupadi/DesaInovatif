package com.destinyapp.desainovatif.Activity.ui.Menu.Laporan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.destinyapp.desainovatif.R;

public class ListLaporanActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);
    }
}