package com.destinyapp.desainovatif.Model.NewModel.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Title {
    @SerializedName("rendered")
    @Nullable
    String rendered;

    @Nullable
    public String getRendered() {
        return rendered;
    }

    public void setRendered(@Nullable String rendered) {
        this.rendered = rendered;
    }

}
