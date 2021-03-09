package com.destinyapp.desainovatif.Model.NewModel;

import androidx.annotation.Nullable;

import com.destinyapp.desainovatif.Model.DataModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("status")
    @Nullable
    String status;

    @SerializedName("data")
    @Nullable
    List<Data> data;

    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public List<Data> getData() {
        return data;
    }

    public void setData(@Nullable List<Data> data) {
        this.data = data;
    }
}
