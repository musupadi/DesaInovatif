package com.destinyapp.desainovatif.Model.NewModel.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("rendered")
    @Nullable
    String rendered;

    @SerializedName("protected")
    @Nullable
    String protecteds;

    @Nullable
    public String getRendered() {
        return rendered;
    }

    public void setRendered(@Nullable String rendered) {
        this.rendered = rendered;
    }

    @Nullable
    public String getProtecteds() {
        return protecteds;
    }

    public void setProtecteds(@Nullable String protecteds) {
        this.protecteds = protecteds;
    }
}
