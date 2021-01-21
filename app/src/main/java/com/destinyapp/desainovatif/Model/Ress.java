package com.destinyapp.desainovatif.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Ress {
    @SerializedName("status")
    @Nullable
    String status;

    @SerializedName("data")
    @Nullable
    String data;

    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public String getData() {
        return data;
    }

    public void setData(@Nullable String data) {
        this.data = data;
    }
}
