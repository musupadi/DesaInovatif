package com.destinyapp.desainovatif.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    @SerializedName("accessToken")
    @Expose
    public String accessToken;

    @SerializedName("usernameUser")
    @Expose
    public String usernameUser;

    @SerializedName("namaUser")
    @Expose
    public String  namaUser;

    @SerializedName("emailUser")
    @Expose
    public String emailUser;

    @SerializedName("fotoUser")
    @Expose
    public String fotoUser;

    @SerializedName("noTelp")
    @Expose
    public String noTelp;

    @SerializedName("statusUser")
    @Expose
    public String statusUser;

    //Surat
    @SerializedName("id_surat")
    @Expose
    public String id_surat;

    @SerializedName("id_desa")
    @Expose
    public String id_desa;

    @SerializedName("id_user")
    @Expose
    public String id_user;

    @SerializedName("nama_surat")
    @Expose
    public String nama_surat;

    @SerializedName("tgl_request")
    @Expose
    public String tgl_request;

    @SerializedName("tgl_kirim")
    @Expose
    public String tgl_kirim;

    @SerializedName("link_file_surat")
    @Expose
    public String link_file_surat;

    @SerializedName("status_surat")
    @Expose
    public String status_surat;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getFotoUser() {
        return fotoUser;
    }

    public void setFotoUser(String fotoUser) {
        this.fotoUser = fotoUser;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public String getId_surat() {
        return id_surat;
    }

    public void setId_surat(String id_surat) {
        this.id_surat = id_surat;
    }

    public String getId_desa() {
        return id_desa;
    }

    public void setId_desa(String id_desa) {
        this.id_desa = id_desa;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_surat() {
        return nama_surat;
    }

    public void setNama_surat(String nama_surat) {
        this.nama_surat = nama_surat;
    }

    public String getTgl_request() {
        return tgl_request;
    }

    public void setTgl_request(String tgl_request) {
        this.tgl_request = tgl_request;
    }

    public String getTgl_kirim() {
        return tgl_kirim;
    }

    public void setTgl_kirim(String tgl_kirim) {
        this.tgl_kirim = tgl_kirim;
    }

    public String getLink_file_surat() {
        return link_file_surat;
    }

    public void setLink_file_surat(String link_file_surat) {
        this.link_file_surat = link_file_surat;
    }

    public String getStatus_surat() {
        return status_surat;
    }

    public void setStatus_surat(String status_surat) {
        this.status_surat = status_surat;
    }
}
