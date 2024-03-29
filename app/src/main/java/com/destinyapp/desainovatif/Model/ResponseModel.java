package com.destinyapp.desainovatif.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {
    @SerializedName("statusCode")
    @Expose
    @Nullable
    public String statusCode;

    @SerializedName("statusMessage")
    @Expose
    @Nullable
    public String statusMessage;

    @SerializedName("message")
    @Expose
    @Nullable
    public String Message;

    @SerializedName("data")
    @Nullable
    List<DataModel> data;



    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Nullable
    public List<DataModel> getData() {
        return data;
    }

    public void setData(@Nullable List<DataModel> data) {
        this.data = data;
    }

    @Nullable
    public String getMessage() {
        return Message;
    }

    public void setMessage(@Nullable String message) {
        Message = message;
    }
}
