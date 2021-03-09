package com.destinyapp.desainovatif.Model.NewModel;

import androidx.annotation.Nullable;

import com.destinyapp.desainovatif.Model.NewModel.Model.Content;
import com.destinyapp.desainovatif.Model.NewModel.Model.Excerpt;
import com.destinyapp.desainovatif.Model.NewModel.Model.Title;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("date")
    @Nullable
    String date;

    @SerializedName("status")
    @Nullable
    String status;

    @SerializedName("img_cover")
    @Nullable
    String img_cover;

    @SerializedName("title")
    @Nullable
    public Title title = new Title();

    @SerializedName("content")
    @Nullable
    public Content content = new Content();

    @SerializedName("excerpt")
    @Nullable
    public Excerpt excerpt = new Excerpt();

    @Nullable
    public String getDate() {
        return date;
    }

    public void setDate(@Nullable String date) {
        this.date = date;
    }

    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public String getImg_cover() {
        return img_cover;
    }

    public void setImg_cover(@Nullable String img_cover) {
        this.img_cover = img_cover;
    }

    @Nullable
    public Title getTitle() {
        return title;
    }

    public void setTitle(@Nullable Title title) {
        this.title = title;
    }

    @Nullable
    public Content getContent() {
        return content;
    }

    public void setContent(@Nullable Content content) {
        this.content = content;
    }

    @Nullable
    public Excerpt getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(@Nullable Excerpt excerpt) {
        this.excerpt = excerpt;
    }
}
