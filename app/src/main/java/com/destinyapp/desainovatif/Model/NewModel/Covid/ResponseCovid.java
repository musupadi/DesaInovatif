package com.destinyapp.desainovatif.Model.NewModel.Covid;

import androidx.annotation.Nullable;

import com.destinyapp.desainovatif.Model.NewModel.Data;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCovid {
    @SerializedName("last_date")
    @Nullable
    String last_date;

    @SerializedName("list_data")
    @Nullable
    List<CovidData> list_data;

    @Nullable
    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(@Nullable String last_date) {
        this.last_date = last_date;
    }

    @Nullable
    public List<CovidData> getList_data() {
        return list_data;
    }

    public void setList_data(@Nullable List<CovidData> list_data) {
        this.list_data = list_data;
    }
}
