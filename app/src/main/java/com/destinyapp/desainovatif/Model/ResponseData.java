package com.destinyapp.desainovatif.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {
    @SerializedName("data")
    @Nullable
    DataModel data;

    @Nullable
    public DataModel getData() {
        return data;
    }

    public void setData(@Nullable DataModel data) {
        this.data = data;
    }
}
