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

    @SerializedName("id_berita")
    @Expose
    public String id_berita;

    @SerializedName("judul_berita")
    @Expose
    public String judul_berita;

    @SerializedName("sumber_berita")
    @Expose
    public String sumber_berita;

    @SerializedName("link_berita")
    @Expose
    public String link_berita;

    @SerializedName("cover_berita")
    @Expose
    public String cover_berita;

    @SerializedName("isi_berita")
    @Expose
    public String isi_berita;

    @SerializedName("status_berita")
    @Expose
    public String status_berita;

    @SerializedName("created_at_berita")
    @Expose
    public String created_at_berita;

    @SerializedName("nama_desa")
    @Expose
    public String nama_desa;

    @SerializedName("alamat_desa")
    @Expose
    public String alamat_desa;

    @SerializedName("kepala_desa")
    @Expose
    public String kepala_desa;

    @SerializedName("logo_desa")
    @Expose
    public String logo_desa;

    @SerializedName("nik_user")
    @Expose
    public String nik_user;

    @SerializedName("pekerjaan_user")
    @Expose
    public String pekerjaan_user;

    @SerializedName("tgl_aktif")
    @Expose
    public String tgl_aktif;

    @SerializedName("status_user")
    @Expose
    public String status_user;

    //Pariwisata
    @SerializedName("id_pariwisata")
    @Expose
    public String id_pariwisata;

    @SerializedName("nama_pariwisata")
    @Expose
    public String nama_pariwisata;

    @SerializedName("deskripsi_pariwisata")
    @Expose
    public String deskripsi_pariwisata;

    @SerializedName("alamat_pariwisata")
    @Expose
    public String alamat_pariwisata;

    @SerializedName("deskripsi_harga")
    @Expose
    public String deskripsi_harga;

    @SerializedName("no_telp_pengurus")
    @Expose
    public String no_telp_pengurus;

    @SerializedName("cover_pariwisata")
    @Expose
    public String cover_pariwisata;

    @SerializedName("status_pariwisata")
    @Expose
    public String status_pariwisata;

    @SerializedName("tgl_upload_pariwisata")
    @Expose
    public String tgl_upload_pariwisata;




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

    public String getId_berita() {
        return id_berita;
    }

    public void setId_berita(String id_berita) {
        this.id_berita = id_berita;
    }

    public String getJudul_berita() {
        return judul_berita;
    }

    public void setJudul_berita(String judul_berita) {
        this.judul_berita = judul_berita;
    }

    public String getSumber_berita() {
        return sumber_berita;
    }

    public void setSumber_berita(String sumber_berita) {
        this.sumber_berita = sumber_berita;
    }

    public String getLink_berita() {
        return link_berita;
    }

    public void setLink_berita(String link_berita) {
        this.link_berita = link_berita;
    }

    public String getCover_berita() {
        return cover_berita;
    }

    public void setCover_berita(String cover_berita) {
        this.cover_berita = cover_berita;
    }

    public String getIsi_berita() {
        return isi_berita;
    }

    public void setIsi_berita(String isi_berita) {
        this.isi_berita = isi_berita;
    }

    public String getStatus_berita() {
        return status_berita;
    }

    public void setStatus_berita(String status_berita) {
        this.status_berita = status_berita;
    }

    public String getCreated_at_berita() {
        return created_at_berita;
    }

    public void setCreated_at_berita(String created_at_berita) {
        this.created_at_berita = created_at_berita;
    }

    public String getNama_desa() {
        return nama_desa;
    }

    public void setNama_desa(String nama_desa) {
        this.nama_desa = nama_desa;
    }

    public String getAlamat_desa() {
        return alamat_desa;
    }

    public void setAlamat_desa(String alamat_desa) {
        this.alamat_desa = alamat_desa;
    }

    public String getKepala_desa() {
        return kepala_desa;
    }

    public void setKepala_desa(String kepala_desa) {
        this.kepala_desa = kepala_desa;
    }

    public String getLogo_desa() {
        return logo_desa;
    }

    public void setLogo_desa(String logo_desa) {
        this.logo_desa = logo_desa;
    }

    public String getNik_user() {
        return nik_user;
    }

    public void setNik_user(String nik_user) {
        this.nik_user = nik_user;
    }

    public String getPekerjaan_user() {
        return pekerjaan_user;
    }

    public void setPekerjaan_user(String pekerjaan_user) {
        this.pekerjaan_user = pekerjaan_user;
    }

    public String getTgl_aktif() {
        return tgl_aktif;
    }

    public void setTgl_aktif(String tgl_aktif) {
        this.tgl_aktif = tgl_aktif;
    }

    public String getStatus_user() {
        return status_user;
    }

    public void setStatus_user(String status_user) {
        this.status_user = status_user;
    }

    public String getId_pariwisata() {
        return id_pariwisata;
    }

    public void setId_pariwisata(String id_pariwisata) {
        this.id_pariwisata = id_pariwisata;
    }

    public String getNama_pariwisata() {
        return nama_pariwisata;
    }

    public void setNama_pariwisata(String nama_pariwisata) {
        this.nama_pariwisata = nama_pariwisata;
    }

    public String getDeskripsi_pariwisata() {
        return deskripsi_pariwisata;
    }

    public void setDeskripsi_pariwisata(String deskripsi_pariwisata) {
        this.deskripsi_pariwisata = deskripsi_pariwisata;
    }

    public String getAlamat_pariwisata() {
        return alamat_pariwisata;
    }

    public void setAlamat_pariwisata(String alamat_pariwisata) {
        this.alamat_pariwisata = alamat_pariwisata;
    }

    public String getDeskripsi_harga() {
        return deskripsi_harga;
    }

    public void setDeskripsi_harga(String deskripsi_harga) {
        this.deskripsi_harga = deskripsi_harga;
    }

    public String getNo_telp_pengurus() {
        return no_telp_pengurus;
    }

    public void setNo_telp_pengurus(String no_telp_pengurus) {
        this.no_telp_pengurus = no_telp_pengurus;
    }

    public String getCover_pariwisata() {
        return cover_pariwisata;
    }

    public void setCover_pariwisata(String cover_pariwisata) {
        this.cover_pariwisata = cover_pariwisata;
    }

    public String getStatus_pariwisata() {
        return status_pariwisata;
    }

    public void setStatus_pariwisata(String status_pariwisata) {
        this.status_pariwisata = status_pariwisata;
    }

    public String getTgl_upload_pariwisata() {
        return tgl_upload_pariwisata;
    }

    public void setTgl_upload_pariwisata(String tgl_upload_pariwisata) {
        this.tgl_upload_pariwisata = tgl_upload_pariwisata;
    }
}
