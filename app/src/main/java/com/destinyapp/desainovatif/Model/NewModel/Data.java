package com.destinyapp.desainovatif.Model.NewModel;

import androidx.annotation.Nullable;

import com.destinyapp.desainovatif.Model.NewModel.Model.Content;
import com.destinyapp.desainovatif.Model.NewModel.Model.Excerpt;
import com.destinyapp.desainovatif.Model.NewModel.Model.Title;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class


Data {
    @SerializedName("date")
    @Nullable
    String date;

    @SerializedName("status")
    @Nullable
    String status;

    @SerializedName("id_user")
    @Nullable
    String id_user;

    @SerializedName("nama_user")
    @Nullable
    String nama_user;

    @SerializedName("foto_user")
    @Nullable
    String foto_user;

    @SerializedName("email_user")
    @Nullable
    String email_user;

    @SerializedName("saldo_user")
    @Nullable
    String saldo_user;

    @SerializedName("pekerjaan_user")
    @Nullable
    String pekerjaan_user;

    @SerializedName("img_cover")
    @Nullable
    String img_cover;

    @SerializedName("id_desa")
    @Nullable
    String id_desa;

    @SerializedName("level")
    @Expose
    public String level;

    @SerializedName("title")
    @Nullable
    public Title title = new Title();

    @SerializedName("content")
    @Nullable
    public Content content = new Content();

    @SerializedName("excerpt")
    @Nullable
    public Excerpt excerpt = new Excerpt();

    @SerializedName("id_setting")
    @Nullable
    String id_setting;

    @SerializedName("nama_setting")
    @Nullable
    String nama_setting;

    @SerializedName("link_setting")
    @Nullable
    String link_setting;

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

    @Nullable
    public String getId_user() {
        return id_user;
    }

    public void setId_user(@Nullable String id_user) {
        this.id_user = id_user;
    }

    @Nullable
    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(@Nullable String nama_user) {
        this.nama_user = nama_user;
    }

    @Nullable
    public String getFoto_user() {
        return foto_user;
    }

    public void setFoto_user(@Nullable String foto_user) {
        this.foto_user = foto_user;
    }

    @Nullable
    public String getSaldo_user() {
        return saldo_user;
    }

    public void setSaldo_user(@Nullable String saldo_user) {
        this.saldo_user = saldo_user;
    }

    @Nullable
    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(@Nullable String email_user) {
        this.email_user = email_user;
    }

    @Nullable
    public String getPekerjaan_user() {
        return pekerjaan_user;
    }

    public void setPekerjaan_user(@Nullable String pekerjaan_user) {
        this.pekerjaan_user = pekerjaan_user;
    }

    @Nullable
    public String getId_desa() {
        return id_desa;
    }

    public void setId_desa(@Nullable String id_desa) {
        this.id_desa = id_desa;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Nullable
    public String getId_setting() {
        return id_setting;
    }

    public void setId_setting(@Nullable String id_setting) {
        this.id_setting = id_setting;
    }

    @Nullable
    public String getNama_setting() {
        return nama_setting;
    }

    public void setNama_setting(@Nullable String nama_setting) {
        this.nama_setting = nama_setting;
    }

    @Nullable
    public String getLink_setting() {
        return link_setting;
    }

    public void setLink_setting(@Nullable String link_setting) {
        this.link_setting = link_setting;
    }
}
