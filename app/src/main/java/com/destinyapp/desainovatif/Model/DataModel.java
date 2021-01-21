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

    //Kategori Surat
    @SerializedName("id_surat_kategori")
    @Expose
    public String id_surat_kategori;

    @SerializedName("nama_kategori_surat")
    @Expose
    public String nama_kategori_surat;

    @SerializedName("deskripsi_kategori_surat")
    @Expose
    public String deskripsi_kategori_surat;

    //Toko
    @SerializedName("id_toko")
    @Expose
    public String id_toko;

    @SerializedName("nama_toko")
    @Expose
    public String nama_toko;

    @SerializedName("deskripsi_toko")
    @Expose
    public String deskripsi_toko;

    @SerializedName("id_foto_cover")
    @Expose
    public String id_foto_cover;

    @SerializedName("tgl_toko_request")
    @Expose
    public String tgl_toko_request;

    @SerializedName("tgl_toko_disetujui")
    @Expose
    public String tgl_toko_disetujui;

    @SerializedName("status_toko")
    @Expose
    public String status_toko;

    @SerializedName("nama_user")
    @Expose
    public String nama_user;

    @SerializedName("link_file_toko_foto")
    @Expose
    public String link_file_toko_foto;

    //Produk
    @SerializedName("id_toko_produk")
    @Expose
    public String id_toko_produk;

    @SerializedName("nama_produk")
    @Expose
    public String nama_produk;

    @SerializedName("deskripsi_produk")
    @Expose
    public String deskripsi_produk;

    @SerializedName("id_cover_produk_foto")
    @Expose
    public String id_cover_produk_foto;

    @SerializedName("tgl_upload_produk")
    @Expose
    public String tgl_upload_produk;

    @SerializedName("status_produk")
    @Expose
    public String status_produk;

    @SerializedName("link_file_produk_foto")
    @Expose
    public String link_file_produk_foto;

    @SerializedName("harga_produk")
    @Expose
    public String harga_produk;

    //Gallery
    @SerializedName("id_toko_foto")
    @Expose
    public String id_toko_foto;


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

    public String getId_surat_kategori() {
        return id_surat_kategori;
    }

    public void setId_surat_kategori(String id_surat_kategori) {
        this.id_surat_kategori = id_surat_kategori;
    }

    public String getNama_kategori_surat() {
        return nama_kategori_surat;
    }

    public void setNama_kategori_surat(String nama_kategori_surat) {
        this.nama_kategori_surat = nama_kategori_surat;
    }

    public String getDeskripsi_kategori_surat() {
        return deskripsi_kategori_surat;
    }

    public void setDeskripsi_kategori_surat(String deskripsi_kategori_surat) {
        this.deskripsi_kategori_surat = deskripsi_kategori_surat;
    }

    public String getId_toko() {
        return id_toko;
    }

    public void setId_toko(String id_toko) {
        this.id_toko = id_toko;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getDeskripsi_toko() {
        return deskripsi_toko;
    }

    public void setDeskripsi_toko(String deskripsi_toko) {
        this.deskripsi_toko = deskripsi_toko;
    }

    public String getId_foto_cover() {
        return id_foto_cover;
    }

    public void setId_foto_cover(String id_foto_cover) {
        this.id_foto_cover = id_foto_cover;
    }

    public String getTgl_toko_request() {
        return tgl_toko_request;
    }

    public void setTgl_toko_request(String tgl_toko_request) {
        this.tgl_toko_request = tgl_toko_request;
    }

    public String getTgl_toko_disetujui() {
        return tgl_toko_disetujui;
    }

    public void setTgl_toko_disetujui(String tgl_toko_disetujui) {
        this.tgl_toko_disetujui = tgl_toko_disetujui;
    }

    public String getStatus_toko() {
        return status_toko;
    }

    public void setStatus_toko(String status_toko) {
        this.status_toko = status_toko;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getLink_file_toko_foto() {
        return link_file_toko_foto;
    }

    public void setLink_file_toko_foto(String link_file_toko_foto) {
        this.link_file_toko_foto = link_file_toko_foto;
    }

    public String getId_toko_produk() {
        return id_toko_produk;
    }

    public void setId_toko_produk(String id_toko_produk) {
        this.id_toko_produk = id_toko_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getDeskripsi_produk() {
        return deskripsi_produk;
    }

    public void setDeskripsi_produk(String deskripsi_produk) {
        this.deskripsi_produk = deskripsi_produk;
    }

    public String getId_cover_produk_foto() {
        return id_cover_produk_foto;
    }

    public void setId_cover_produk_foto(String id_cover_produk_foto) {
        this.id_cover_produk_foto = id_cover_produk_foto;
    }

    public String getTgl_upload_produk() {
        return tgl_upload_produk;
    }

    public void setTgl_upload_produk(String tgl_upload_produk) {
        this.tgl_upload_produk = tgl_upload_produk;
    }

    public String getStatus_produk() {
        return status_produk;
    }

    public void setStatus_produk(String status_produk) {
        this.status_produk = status_produk;
    }

    public String getLink_file_produk_foto() {
        return link_file_produk_foto;
    }

    public void setLink_file_produk_foto(String link_file_produk_foto) {
        this.link_file_produk_foto = link_file_produk_foto;
    }

    public String getId_toko_foto() {
        return id_toko_foto;
    }

    public void setId_toko_foto(String id_toko_foto) {
        this.id_toko_foto = id_toko_foto;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(String harga_produk) {
        this.harga_produk = harga_produk;
    }
}
