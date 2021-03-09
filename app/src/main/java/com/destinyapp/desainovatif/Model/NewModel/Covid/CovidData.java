package com.destinyapp.desainovatif.Model.NewModel.Covid;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class CovidData {
    @SerializedName("key")
    @Nullable
    String key;

    @SerializedName("jumlah_kasus")
    @Nullable
    String jumlah_kasus;

    @SerializedName("jumlah_sembuh")
    @Nullable
    String jumlah_sembuh;

    @SerializedName("jumlah_meninggal")
    @Nullable
    String jumlah_meninggal;

    @SerializedName("jumlah_dirawat")
    @Nullable
    String jumlah_dirawat;

    @Nullable
    public String getKey() {
        return key;
    }

    public void setKey(@Nullable String key) {
        this.key = key;
    }

    @Nullable
    public String getJumlah_kasus() {
        return jumlah_kasus;
    }

    public void setJumlah_kasus(@Nullable String jumlah_kasus) {
        this.jumlah_kasus = jumlah_kasus;
    }

    @Nullable
    public String getJumlah_sembuh() {
        return jumlah_sembuh;
    }

    public void setJumlah_sembuh(@Nullable String jumlah_sembuh) {
        this.jumlah_sembuh = jumlah_sembuh;
    }

    @Nullable
    public String getJumlah_meninggal() {
        return jumlah_meninggal;
    }

    public void setJumlah_meninggal(@Nullable String jumlah_meninggal) {
        this.jumlah_meninggal = jumlah_meninggal;
    }

    @Nullable
    public String getJumlah_dirawat() {
        return jumlah_dirawat;
    }

    public void setJumlah_dirawat(@Nullable String jumlah_dirawat) {
        this.jumlah_dirawat = jumlah_dirawat;
    }
}
