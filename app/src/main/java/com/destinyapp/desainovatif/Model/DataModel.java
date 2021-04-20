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

    @SerializedName("level")
    @Expose
    public String level;


    //Banner
    @SerializedName("id_banner")
    @Expose
    public String id_banner;

    @SerializedName("nama_banner")
    @Expose
    public String nama_banner;

    @SerializedName("file_foto_banner")
    @Expose
    public String file_foto_banner;

    @SerializedName("sort_num")
    @Expose
    public String sort_num;

    @SerializedName("created_at_banner")
    @Expose
    public String created_at_banner;

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

    //Berita
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

    //Laporan
    @SerializedName("id_laporan_kategori")
    @Expose
    public String id_laporan_kategori;

    @SerializedName("nama_laporan_kategori")
    @Expose
    public String nama_laporan_kategori;

    @SerializedName("deskripsi_laporan_kategori")
    @Expose
    public String deskripsi_laporan_kategori;

    //Lokasi
    @SerializedName("id_lokasi_bs")
    @Expose
    public String id_lokasi_bs;

    @SerializedName("nama_lokasi_bs")
    @Expose
    public String nama_lokasi_bs;

    @SerializedName("alamat_bs")
    @Expose
    public String alamat_bs;

    @SerializedName("latitude_bs")
    @Expose
    public String latitude_bs;

    @SerializedName("longitude_bs")
    @Expose
    public String longitude_bs;

    @SerializedName("tgl_tambah_bs")
    @Expose
    public String tgl_tambah_bs;

    //Laporan
    @SerializedName("tgl_upload_laporan")
    @Expose
    public String tgl_upload_laporan;

    @SerializedName("id_laporan")
    @Expose
    public String id_laporan;

    @SerializedName("nama_laporan")
    @Expose
    public String nama_laporan;

    @SerializedName("deskripsi_laporan")
    @Expose
    public String deskripsi_laporan;

    @SerializedName("status_laporan")
    @Expose
    public String status_laporan;

    //Jenis Sampah
    @SerializedName("id_jenis_sampah")
    @Expose
    public String id_jenis_sampah;

    @SerializedName("id_user_penambah")
    @Expose
    public String id_user_penambah;

    @SerializedName("nama_jenis_sampah")
    @Expose
    public String nama_jenis_sampah;

    @SerializedName("kode_jenis_sampah")
    @Expose
    public String kode_jenis_sampah;

    @SerializedName("satuan_jenis_sampah")
    @Expose
    public String satuan_jenis_sampah;

    @SerializedName("harga_jenis_sampah")
    @Expose
    public String harga_jenis_sampah;

    //Transaksi Sampah
    @SerializedName("tgl_transaksi_bs")
    @Expose
    public String tgl_transaksi_bs;

    //Reward Bank Sampah
    @SerializedName("id_reward_bs")
    @Expose
    public String id_reward_bs;

    @SerializedName("nama_reward")
    @Expose
    public String nama_reward;

    @SerializedName("nilai_dibutuhkan")
    @Expose
    public String nilai_dibutuhkan;

    @SerializedName("qty_reward")
    @Expose
    public String qty_reward;

    //History Transaksi BS
    @SerializedName("id_transaksi_reward_bs")
    @Expose
    public String id_transaksi_reward_bs;

    @SerializedName("qty_get_reward")
    @Expose
    public String qty_get_reward;

    @SerializedName("total_nilai")
    @Expose
    public String total_nilai;

    @SerializedName("status_transaksi_reward")
    @Expose
    public String status_transaksi_reward;

    @SerializedName("tgl_klaim_reward")
    @Expose
    public String tgl_klaim_reward;

    @SerializedName("tgl_ambil_reward")
    @Expose
    public String tgl_ambil_reward;


    //List User
    @SerializedName("email_user")
    @Expose
    public String email_user;

    @SerializedName("alamat_user")
    @Expose
    public String alamat_user;

    //Sub Kategori
    @SerializedName("id_surat_kategori_sub")
    @Expose
    public String id_surat_kategori_sub;

    @SerializedName("nama_surat_kategori_sub")
    @Expose
    public String nama_surat_kategori_sub;

    @SerializedName("syarat_sub_kategori")
    @Expose
    public String syarat_sub_kategori;

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getAlamat_user() {
        return alamat_user;
    }

    public void setAlamat_user(String alamat_user) {
        this.alamat_user = alamat_user;
    }

    public String getId_transaksi_reward_bs() {
        return id_transaksi_reward_bs;
    }

    public void setId_transaksi_reward_bs(String id_transaksi_reward_bs) {
        this.id_transaksi_reward_bs = id_transaksi_reward_bs;
    }

    public String getQty_get_reward() {
        return qty_get_reward;
    }

    public void setQty_get_reward(String qty_get_reward) {
        this.qty_get_reward = qty_get_reward;
    }

    public String getTotal_nilai() {
        return total_nilai;
    }

    public void setTotal_nilai(String total_nilai) {
        this.total_nilai = total_nilai;
    }

    public String getStatus_transaksi_reward() {
        return status_transaksi_reward;
    }

    public void setStatus_transaksi_reward(String status_transaksi_reward) {
        this.status_transaksi_reward = status_transaksi_reward;
    }

    public String getTgl_klaim_reward() {
        return tgl_klaim_reward;
    }

    public void setTgl_klaim_reward(String tgl_klaim_reward) {
        this.tgl_klaim_reward = tgl_klaim_reward;
    }

    public String getTgl_ambil_reward() {
        return tgl_ambil_reward;
    }

    public void setTgl_ambil_reward(String tgl_ambil_reward) {
        this.tgl_ambil_reward = tgl_ambil_reward;
    }

    public String getId_reward_bs() {
        return id_reward_bs;
    }

    public void setId_reward_bs(String id_reward_bs) {
        this.id_reward_bs = id_reward_bs;
    }

    public String getNama_reward() {
        return nama_reward;
    }

    public void setNama_reward(String nama_reward) {
        this.nama_reward = nama_reward;
    }

    public String getNilai_dibutuhkan() {
        return nilai_dibutuhkan;
    }

    public void setNilai_dibutuhkan(String nilai_dibutuhkan) {
        this.nilai_dibutuhkan = nilai_dibutuhkan;
    }

    public String getQty_reward() {
        return qty_reward;
    }

    public void setQty_reward(String qty_reward) {
        this.qty_reward = qty_reward;
    }

    public String getTgl_upload_laporan() {
        return tgl_upload_laporan;
    }

    public void setTgl_upload_laporan(String tgl_upload_laporan) {
        this.tgl_upload_laporan = tgl_upload_laporan;
    }

    public String getTgl_transaksi_bs() {
        return tgl_transaksi_bs;
    }

    public void setTgl_transaksi_bs(String tgl_transaksi_bs) {
        this.tgl_transaksi_bs = tgl_transaksi_bs;
    }

    public String getId_laporan() {
        return id_laporan;
    }

    public void setId_laporan(String id_laporan) {
        this.id_laporan = id_laporan;
    }

    public String getNama_laporan() {
        return nama_laporan;
    }

    public void setNama_laporan(String nama_laporan) {
        this.nama_laporan = nama_laporan;
    }

    public String getDeskripsi_laporan() {
        return deskripsi_laporan;
    }

    public void setDeskripsi_laporan(String deskripsi_laporan) {
        this.deskripsi_laporan = deskripsi_laporan;
    }

    public String getStatus_laporan() {
        return status_laporan;
    }

    public void setStatus_laporan(String status_laporan) {
        this.status_laporan = status_laporan;
    }

    public String getId_jenis_sampah() {
        return id_jenis_sampah;
    }

    public void setId_jenis_sampah(String id_jenis_sampah) {
        this.id_jenis_sampah = id_jenis_sampah;
    }

    public String getId_user_penambah() {
        return id_user_penambah;
    }

    public void setId_user_penambah(String id_user_penambah) {
        this.id_user_penambah = id_user_penambah;
    }

    public String getNama_jenis_sampah() {
        return nama_jenis_sampah;
    }

    public void setNama_jenis_sampah(String nama_jenis_sampah) {
        this.nama_jenis_sampah = nama_jenis_sampah;
    }

    public String getKode_jenis_sampah() {
        return kode_jenis_sampah;
    }

    public void setKode_jenis_sampah(String kode_jenis_sampah) {
        this.kode_jenis_sampah = kode_jenis_sampah;
    }

    public String getSatuan_jenis_sampah() {
        return satuan_jenis_sampah;
    }

    public void setSatuan_jenis_sampah(String satuan_jenis_sampah) {
        this.satuan_jenis_sampah = satuan_jenis_sampah;
    }

    public String getHarga_jenis_sampah() {
        return harga_jenis_sampah;
    }

    public void setHarga_jenis_sampah(String harga_jenis_sampah) {
        this.harga_jenis_sampah = harga_jenis_sampah;
    }

    public String getId_lokasi_bs() {
        return id_lokasi_bs;
    }

    public void setId_lokasi_bs(String id_lokasi_bs) {
        this.id_lokasi_bs = id_lokasi_bs;
    }

    public String getNama_lokasi_bs() {
        return nama_lokasi_bs;
    }

    public void setNama_lokasi_bs(String nama_lokasi_bs) {
        this.nama_lokasi_bs = nama_lokasi_bs;
    }

    public String getAlamat_bs() {
        return alamat_bs;
    }

    public void setAlamat_bs(String alamat_bs) {
        this.alamat_bs = alamat_bs;
    }

    public String getLatitude_bs() {
        return latitude_bs;
    }

    public void setLatitude_bs(String latitude_bs) {
        this.latitude_bs = latitude_bs;
    }

    public String getLongitude_bs() {
        return longitude_bs;
    }

    public void setLongitude_bs(String longitude_bs) {
        this.longitude_bs = longitude_bs;
    }

    public String getTgl_tambah_bs() {
        return tgl_tambah_bs;
    }

    public void setTgl_tambah_bs(String tgl_tambah_bs) {
        this.tgl_tambah_bs = tgl_tambah_bs;
    }

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

    public String getId_banner() {
        return id_banner;
    }

    public void setId_banner(String id_banner) {
        this.id_banner = id_banner;
    }

    public String getNama_banner() {
        return nama_banner;
    }

    public void setNama_banner(String nama_banner) {
        this.nama_banner = nama_banner;
    }

    public String getFile_foto_banner() {
        return file_foto_banner;
    }

    public void setFile_foto_banner(String file_foto_banner) {
        this.file_foto_banner = file_foto_banner;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
    }

    public String getCreated_at_banner() {
        return created_at_banner;
    }

    public void setCreated_at_banner(String created_at_banner) {
        this.created_at_banner = created_at_banner;
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

    public String getId_laporan_kategori() {
        return id_laporan_kategori;
    }

    public void setId_laporan_kategori(String id_laporan_kategori) {
        this.id_laporan_kategori = id_laporan_kategori;
    }

    public String getNama_laporan_kategori() {
        return nama_laporan_kategori;
    }

    public void setNama_laporan_kategori(String nama_laporan_kategori) {
        this.nama_laporan_kategori = nama_laporan_kategori;
    }

    public String getDeskripsi_laporan_kategori() {
        return deskripsi_laporan_kategori;
    }

    public void setDeskripsi_laporan_kategori(String deskripsi_laporan_kategori) {
        this.deskripsi_laporan_kategori = deskripsi_laporan_kategori;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId_surat_kategori_sub() {
        return id_surat_kategori_sub;
    }

    public void setId_surat_kategori_sub(String id_surat_kategori_sub) {
        this.id_surat_kategori_sub = id_surat_kategori_sub;
    }

    public String getNama_surat_kategori_sub() {
        return nama_surat_kategori_sub;
    }

    public void setNama_surat_kategori_sub(String nama_surat_kategori_sub) {
        this.nama_surat_kategori_sub = nama_surat_kategori_sub;
    }

    public String getSyarat_sub_kategori() {
        return syarat_sub_kategori;
    }

    public void setSyarat_sub_kategori(String syarat_sub_kategori) {
        this.syarat_sub_kategori = syarat_sub_kategori;
    }
}
