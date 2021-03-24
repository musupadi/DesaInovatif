package com.destinyapp.desainovatif.Model.NewModel;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewResponse {
    @SerializedName("status")
    @Nullable
    String status;

    @SerializedName("data")
    @Nullable
    public Data data = new Data();


    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public Data getData() {
        return data;
    }

    public void setData(@Nullable Data data) {
        this.data = data;
    }
}
