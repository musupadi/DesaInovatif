package com.destinyapp.desainovatif.API;

import com.destinyapp.desainovatif.Model.NewModel.Covid.ResponseCovid;
import com.destinyapp.desainovatif.Model.NewModel.KontolFajar;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.NewModel.Response;
import com.destinyapp.desainovatif.Model.ResponseData;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Header("Authorization") String authHeader,
                              @Field("kunci_kamps") String kunci_kamps,
                              @Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("user/login")
    Call<NewResponse> Login_user(@Header("Authorization") String authHeader,
                                 @Field("kunci_kamps") String kunci_kamps,
                                 @Field("no_hp") String no_hp,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader,
                              @Field("kunci_kamps") String kunci_kamps,
                              @Field("namaSurat") String namaSurat);

    @FormUrlEncoded
    @POST("pecorine")
    Call<ResponseData> Kyoko(@Header("Authorization") String authHeader,
                             @Field("kunci_kamps") String kunci_kamps,
                             @Field("no_hp") String no_hp);

    @FormUrlEncoded
    @POST("user/change_pass")
    Call<ResponseModel> ChangePassword(@Header("Authorization") String authHeader,
                                       @Field("kunci_kamps") String kunci_kamps,
                                       @Field("id_user") String id_user,
                                       @Field("pass_lama") String pass_lama,
                                       @Field("pass_conf") String pass_conf,
                                       @Field("pass_baru") String pass_baru);

    @FormUrlEncoded
    @POST("user/edit_foto")
    Call<ResponseModel> EditFoto(@Header("Authorization") String authHeader,
                                 @Part("kunci_kamps") RequestBody kunci_kamps,
                                 @Part("id_user") RequestBody id_user,
                                 @Part MultipartBody.Part FotoUser);

    @FormUrlEncoded
    @POST("toko")
    Call<ResponseModel> Toko(@Header("Authorization") String authHeader,
                             @Field("kunci_kamps") String kunci_kamps,@Field("id_toko") String id_toko,
                             @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("info/daftar_rt")
    Call<ResponseModel> PostDaftarRT(@Header("Authorization") String authHeader,
                                     @Field("kunci_kamps") String kunci_kamps,
                                     @Field("id_rw") String id_rw);

    @FormUrlEncoded
    @POST("info/daftar_rw")
    Call<ResponseModel> PostDaftarRW(@Header("Authorization") String authHeader,
                                     @Field("kunci_kamps") String kunci_kamps);



    @FormUrlEncoded
    @POST("kat_surat/sub_kat")
    Call<ResponseModel> SubKatSurat(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_surat_kategori") String id_surat_kategori);

    @FormUrlEncoded
    @POST("toko/my_toko")
    Call<ResponseModel> MyToko(@Header("Authorization") String authHeader,
                               @Field("kunci_kamps") String kunci_kamps,
                               @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("toko/gallery_produk")
    Call<ResponseModel> GalleryProduk(@Header("Authorization") String authHeader,
                                      @Field("kunci_kamps") String kunci_kamps,
                                      @Field("id_produk") String id_produk);

    @FormUrlEncoded
    @POST("bank_sampah/reward")
    Call<ResponseModel> ListRewardBankSampah(@Header("Authorization") String authHeader,
                                             @Field("kunci_kamps") String kunci_kamps,
                                             @Field("id_desa") String id_desa);


    @FormUrlEncoded
    @POST("bank_sampah/daftar_jenis_sampah")
    Call<ResponseModel> JenisSampah(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("bank_sampah/trans_reward")
    Call<ResponseModel> TransaksiSampah(@Header("Authorization") String authHeader,
                                        @Field("kunci_kamps") String kunci_kamps,
                                        @Field("id_user") String id_user);



    @FormUrlEncoded
    @POST("laporan/surat")
    Call<ResponseModel> SuratLaporan(@Header("Authorization") String authHeader,
                                     @Field("kunci_kamps") String kunci_kamps,
                                     @Field("id_user") String id_user,
                                     @Field("id_surat_kategori") String id_surat_kategori,
                                     @Field("nama_surat") String nama_surat);



    @FormUrlEncoded
    @POST("toko/gallery")
    Call<ResponseModel> GalleryToko(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_toko") String id_toko);

    @FormUrlEncoded
    @POST("user/list_user_rt")
    Call<ResponseModel> ListUserRT(@Header("Authorization") String authHeader,
                                   @Field("kunci_kamps") String kunci_kamps,
                                   @Field("id_user") String id_user);


    @Multipart
    @POST("user/register")
    Call<Ress> Register(@Header("Authorization") String authHeader,
                        @Part("kunci_kamps") RequestBody kunci_kamps,
                        @Part("nama_user") RequestBody nama_user,
                        @Part("password_user") RequestBody password_user,
                        @Part("alamat_user") RequestBody alamat_user,
                        @Part("id_rw") RequestBody id_rw,
                        @Part("id_rt") RequestBody id_rt,
                        @Part("no_hp_user") RequestBody no_hp_user,
                        @Part("email_user") RequestBody email_user,
                        @Part("pekerjaan_user") RequestBody pekerjaan_user,
                        @Part MultipartBody.Part FileSyarat1);

    @Multipart
    @POST("user/register")
    Call<Ress> Register(@Header("Authorization") String authHeader,
                        @Part("kunci_kamps") RequestBody kunci_kamps,
                        @Part("nama_user") RequestBody nama_user,   
                        @Part("password_user") RequestBody password_user,
                        @Part("alamat_user") RequestBody alamat_user,
                        @Part("id_rw") RequestBody id_rw,
                        @Part("id_rt") RequestBody id_rt,
                        @Part("no_hp_user") RequestBody no_hp_user,
                        @Part("email_user") RequestBody email_user,
                        @Part("pekerjaan_user") RequestBody pekerjaan_user);


    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratSKKMBPJS(@Header("Authorization") String authHeader,
                                          @Field("kunci_kamps") String kunci_kamps,
                                          @Field("id_user") String id_user,
                                          @Field("id_desa") String id_desa,
                                          @Field("id_surat_kategori") String id_surat_kategori,
                                          @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                          @Field("nama_surat") String nama_surat,
                                          @Field("note_surat") String note_surat,
                                          @Field("nama[]") ArrayList<String> nama,
                                          @Field("nik[]") ArrayList<String> nik,
                                          @Field("alamat[]") ArrayList<String> alamat,
                                          @Field("keterangan[]") ArrayList<String> keterangan);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratBedaNamaBST(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_user") String id_user,
                                    @Field("id_desa") String id_desa,
                                    @Field("id_surat_kategori") String id_surat_kategori,
                                    @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                    @Field("nama_surat") String nama_surat,
                                    @Field("note_surat") String note_surat,
                                    @Field("nama") String nama,
                                    @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("agama") String agama,
                                    @Field("pekerjaan") String pekerjaan,
                                    @Field("status_pernikahan") String status_pernikahan,
                                    @Field("warga_negara") String warga_negara,
                                    @Field("no_ktp") String no_ktp,
                                    @Field("alamat") String alamat,
                                    @Field("nama_bst_baru") String nama_bst_baru);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratBedaDomisili(@Header("Authorization") String authHeader,
                                     @Field("kunci_kamps") String kunci_kamps,
                                     @Field("id_user") String id_user,
                                     @Field("id_desa") String id_desa,
                                     @Field("id_surat_kategori") String id_surat_kategori,
                                     @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                     @Field("nama_surat") String nama_surat,
                                     @Field("note_surat") String note_surat,
                                     @Field("nama") String nama,
                                     @Field("nik") String nik,
                                     @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                     @Field("jenis_kelamin") String jenis_kelamin,
                                     @Field("agama") String agama,
                                     @Field("pekerjaan") String pekerjaan,
                                     @Field("status_perkawinan") String status_perkawinan,
                                     @Field("nama_kampung") String nama_kampung,
                                     @Field("rt") String rt,
                                     @Field("rw") String rw,
                                     @Field("tinggal_sejak") String tinggal_sejak);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratBedaNamaPNS(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_user") String id_user,
                                    @Field("id_desa") String id_desa,
                                    @Field("id_surat_kategori") String id_surat_kategori,
                                    @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                    @Field("nama_surat") String nama_surat,
                                    @Field("note_surat") String note_surat,
                                    @Field("nama") String nama,
                                    @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("agama") String agama,
                                    @Field("pekerjaan") String pekerjaan,
                                    @Field("status_pernikahan") String status_pernikahan,
                                    @Field("warga_negara") String warga_negara,
                                    @Field("no_ktp") String no_ktp,
                                    @Field("alamat") String alamat,
                                    @Field("nama_pns_baru") String nama_pns_baru);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratBedaNIK(@Header("Authorization") String authHeader,
                                @Field("kunci_kamps") String kunci_kamps,
                                @Field("id_user") String id_user,
                                @Field("id_desa") String id_desa,
                                @Field("id_surat_kategori") String id_surat_kategori,
                                @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                @Field("nama_surat") String nama_surat,
                                @Field("note_surat") String note_surat,
                                @Field("nama") String nama,
                                @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                @Field("jenis_kelamin") String jenis_kelamin,
                                @Field("agama") String agama,
                                @Field("pekerjaan") String pekerjaan,
                                @Field("status_pernikahan") String status_pernikahan,
                                @Field("warga_negara") String warga_negara,
                                @Field("no_ktp") String no_ktp,
                                @Field("alamat") String alamat,
                                @Field("nik_lama") String nik_lama,
                                @Field("nik_baru") String nik_baru);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratSKTM(@Header("Authorization") String authHeader,
                             @Field("kunci_kamps") String kunci_kamps,
                             @Field("id_user") String id_user,
                             @Field("id_desa") String id_desa,
                             @Field("id_surat_kategori") String id_surat_kategori,
                             @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                             @Field("nama_surat") String nama_surat,
                             @Field("note_surat") String note_surat,
                             @Field("nama") String nama,
                             @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                             @Field("jenis_kelamin") String jenis_kelamin,
                             @Field("agama") String agama,
                             @Field("pekerjaan") String pekerjaan,
                             @Field("alamat") String alamat,
                             @Field("nama_ortu") String nama_ortu,
                             @Field("nik_ortu") String nik_ortu,
                             @Field("tempat_tgl_lahir_ortu") String tempat_tgl_lahir_ortu,
                             @Field("agama_ortu") String agama_ortu,
                             @Field("pekerjaan_ortu") String pekerjaan_ortu,
                             @Field("alamat_ortu") String alamat_ortu,
                             @Field("nama_sekolah") String nama_sekolah);



    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratBelumMenikah1(@Header("Authorization") String authHeader,
                                      @Field("kunci_kamps") String kunci_kamps,
                                      @Field("id_user") String id_user,
                                      @Field("id_desa") String id_desa,
                                      @Field("id_surat_kategori") String id_surat_kategori,
                                      @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                      @Field("nama_surat") String nama_surat,
                                      @Field("note_surat") String note_surat,
                                      @Field("nama") String nama,
                                      @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                      @Field("jenis_kelamin") String jenis_kelamin,
                                      @Field("agama") String agama,
                                      @Field("pekerjaan") String pekerjaan,
                                      @Field("no_ktp") String no_ktp,
                                      @Field("alamat") String alamat,
                                      @Field("wali_bertanggung_jawab") String wali_bertanggung_jawab,
                                      @Field("pembuat_pernyataan") String pembuat_pernyataan,
                                      @Field("saksi_rt") String saksi_rt,
                                      @Field("saksi_rw") String saksi_rw);


    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratSKTMRS(@Header("Authorization") String authHeader,
                             @Field("kunci_kamps") String kunci_kamps,
                             @Field("id_user") String id_user,
                             @Field("id_desa") String id_desa,
                             @Field("id_surat_kategori") String id_surat_kategori,
                             @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                             @Field("nama_surat") String nama_surat,
                             @Field("note_surat") String note_surat,
                             @Field("nama") String nama,
                             @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                             @Field("jenis_kelamin") String jenis_kelamin,
                             @Field("agama") String agama,
                             @Field("pekerjaan") String pekerjaan,
                             @Field("alamat") String alamat);



    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratNikahN1(@Header("Authorization") String authHeader,
                                @Field("kunci_kamps") String kunci_kamps,
                                @Field("id_user") String id_user,
                                @Field("id_desa") String id_desa,
                                @Field("id_surat_kategori") String id_surat_kategori,
                                @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                @Field("nama_surat") String nama_surat,
                                @Field("note_surat") String note_surat,
                                @Field("nama") String nama,
                                @Field("nik") String nik,
                                @Field("agama") String agama,
                                @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                @Field("jenis_kelamin") String jenis_kelamin,
                                @Field("pekerjaan") String pekerjaan,
                                @Field("alamat") String alamat,
                                @Field("warga_negara") String warga_negara,
                                @Field("status_perkawinan") String status_perkawinan,
                                @Field("nama_pria") String nama_pria,
                                @Field("nik_pria") String nik_pria,
                                @Field("tempat_tgl_lahir_pria") String tempat_tgl_lahir_pria,
                                @Field("kewarnegaraan_pria") String kewarnegaraan_pria,
                                @Field("agama_pria") String agama_pria,
                                @Field("pekerjaan_pria") String pekerjaan_pria,
                                @Field("alamat_pria") String alamat_pria,
                                @Field("nama_wanita") String nama_wanita,
                                @Field("nik_wanita") String nik_wanita,
                                @Field("tempat_tgl_lahir_wanita") String tempat_tgl_lahir_wanita,
                                @Field("kewarnegaraan_wanita") String kewarnegaraan_wanita,
                                @Field("agama_wanita") String agama_wanita,
                                @Field("pekerjaan_wanita") String pekerjaan_wanita,
                                @Field("alamat_wanita") String alamat_wanita);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratNikahN2(@Header("Authorization") String authHeader,
                                @Field("kunci_kamps") String kunci_kamps,
                                @Field("id_user") String id_user,
                                @Field("id_desa") String id_desa,
                                @Field("id_surat_kategori") String id_surat_kategori,
                                @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                @Field("nama_surat") String nama_surat,
                                @Field("note_surat") String note_surat,
                                @Field("nama_pemohon") String nama_pemohon,
                                @Field("calon_suami") String calon_suami,
                                @Field("calon_istri") String calon_istri,
                                @Field("hari_tanggal_jam") String hari_tanggal_jam,
                                @Field("tempat_akad_nikah") String tempat_akad_nikah);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratNikahN3(@Header("Authorization") String authHeader,
                                @Field("kunci_kamps") String kunci_kamps,
                                @Field("id_user") String id_user,
                                @Field("id_desa") String id_desa,
                                @Field("id_surat_kategori") String id_surat_kategori,
                                @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                @Field("nama_surat") String nama_surat,
                                @Field("note_surat") String note_surat,
                                @Field("nama_suami") String nama_suami,
                                @Field("bin_suami") String bin_suami,
                                @Field("nik_suami") String nik_suami,
                                @Field("tempat_tgl_lahir_suami") String tempat_tgl_lahir_suami,
                                @Field("kewarnegaraan_suami") String kewarnegaraan_suami,
                                @Field("agama_suami") String agama_suami,
                                @Field("pekerjaan_suami") String pekerjaan_suami,
                                @Field("alamat_suami") String alamat_suami,
                                @Field("nama_istri") String nama_istri,
                                @Field("binti_istri") String binti_istri,
                                @Field("nik_istri") String nik_istri,
                                @Field("tempat_tgl_lahir_istri") String tempat_tgl_lahir_istri,
                                @Field("kewarnegaraan_istri") String kewarnegaraan_istri,
                                @Field("agama_istri") String agama_istri,
                                @Field("pekerjaan_istri") String pekerjaan_istri,
                                @Field("alamat_istri") String alamat_istri);

    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratBedaNamaKTP(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_user") String id_user,
                                    @Field("id_desa") String id_desa,
                                    @Field("id_surat_kategori") String id_surat_kategori,
                                    @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                    @Field("nama_surat") String nama_surat,
                                    @Field("note_surat") String note_surat,
                                    @Field("nama") String nama,
                                    @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("agama") String agama,
                                    @Field("pekerjaan") String pekerjaan,
                                    @Field("status_pernikahan") String status_pernikahan,
                                    @Field("warga_negara") String warga_negara,
                                    @Field("no_ktp") String no_ktp,
                                    @Field("alamat") String alamat,
                                    @Field("nama_ktp_baru") String nama_ktp_baru);


    @FormUrlEncoded
    @POST("surat")
    Call<Ress> PostSuratKelahiran(@Header("Authorization") String authHeader,
                                  @Field("kunci_kamps") String kunci_kamps,
                                  @Field("id_user") String id_user,
                                  @Field("id_desa") String id_desa,
                                  @Field("id_surat_kategori") String id_surat_kategori,
                                  @Field("id_surat_kategori_sub") String id_surat_kategori_sub,
                                  @Field("nama_surat") String nama_surat,
                                  @Field("note_surat") String note_surat,
                                  @Field("anak_ke") String anak_ke,
                                  @Field("nama_ayah") String nama_ayah,
                                  @Field("tempat_tgl_lahir_ayah") String tempat_tgl_lahir_ayah,
                                  @Field("pekerjaan_ayah") String pekerjaan_ayah,
                                  @Field("agama_ayah") String agama_ayah,
                                  @Field("no_ktp_ayah") String no_ktp_ayah,
                                  @Field("alamat_ayah") String alamat_ayah,
                                  @Field("nama_ibu") String nama_ibu,
                                  @Field("tempat_tgl_lahir_ibu") String tempat_tgl_lahir_ibu,
                                  @Field("pekerjaan_ibu") String pekerjaan_ibu,
                                  @Field("agama_ibu") String agama_ibu,
                                  @Field("no_ktp_ibu") String no_ktp_ibu,
                                  @Field("alamat_ibu") String alamat_ibu,
                                  @Field("tgl_lahir_bayi") String tgl_lahir_bayi,
                                  @Field("pukul") String pukul,
                                  @Field("nama_bayi") String nama_bayi);

    //Insert Surat Kelahiran
    //1
    @Multipart
    @POST("surat")
    Call<Ress> PostKelahiran1(@Header("Authorization") String authHeader,
                              @Part("kunci_kamps") RequestBody kunci_kamps,
                              @Part("id_desa") RequestBody id_desa,
                              @Part("id_user") RequestBody id_user,
                              @Part("id_surat_kategori") RequestBody id_surat_kategori,
                              @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                              @Part("nama_surat") RequestBody nama_surat,
                              @Part("note_surat") RequestBody note_surat,
                              @Part("anak_ke") RequestBody anak_ke,
                              @Part("tgl_lahir_bayi") RequestBody tgl_lahir_bayi,
                              @Part("pukul") RequestBody pukul,
                              @Part("nama_bayi") RequestBody nama_bayi,
                              @Part("nama_ayah") RequestBody nama_ayah,
                              @Part("tempat_tgl_lahir_ayah") RequestBody tempat_tgl_lahir_ayah,
                              @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                              @Part("agama_ayah") RequestBody agama_ayah,
                              @Part("no_ktp_ayah") RequestBody no_ktp_ayah,
                              @Part("alamat_ayah") RequestBody alamat_ayah,
                              @Part("nama_ibu") RequestBody nama_ibu,
                              @Part("tempat_tgl_lahir_ibu") RequestBody tempat_tgl_lahir_ibu,
                              @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                              @Part("agama_ibu") RequestBody agama_ibu,
                              @Part("no_ktp_ibu") RequestBody no_ktp_ibu,
                              @Part("alamat_ibu") RequestBody alamat_ibu,
                              @Part MultipartBody.Part FileSyarat1,
                              @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    //2
    @Multipart
    @POST("surat")
    Call<Ress> PostKelahiran2(@Header("Authorization") String authHeader,
                              @Part("kunci_kamps") RequestBody kunci_kamps,
                              @Part("id_desa") RequestBody id_desa,
                              @Part("id_user") RequestBody id_user,
                              @Part("id_surat_kategori") RequestBody id_surat_kategori,
                              @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                              @Part("nama_surat") RequestBody nama_surat,
                              @Part("note_surat") RequestBody note_surat,
                              @Part("anak_ke") RequestBody anak_ke,
                              @Part("tgl_lahir_bayi") RequestBody tgl_lahir_bayi,
                              @Part("pukul") RequestBody pukul,
                              @Part("nama_bayi") RequestBody nama_bayi,
                              @Part("nama_ayah") RequestBody nama_ayah,
                              @Part("tempat_tgl_lahir_ayah") RequestBody tempat_tgl_lahir_ayah,
                              @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                              @Part("agama_ayah") RequestBody agama_ayah,
                              @Part("no_ktp_ayah") RequestBody no_ktp_ayah,
                              @Part("alamat_ayah") RequestBody alamat_ayah,
                              @Part("nama_ibu") RequestBody nama_ibu,
                              @Part("tempat_tgl_lahir_ibu") RequestBody tempat_tgl_lahir_ibu,
                              @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                              @Part("agama_ibu") RequestBody agama_ibu,
                              @Part("no_ktp_ibu") RequestBody no_ktp_ibu,
                              @Part("alamat_ibu") RequestBody alamat_ibu,
                              @Part MultipartBody.Part FileSyarat1,
                              @Part MultipartBody.Part FileSyarat2,
                              @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    //3
    @Multipart
    @POST("surat")
    Call<Ress> PostKelahiran3(@Header("Authorization") String authHeader,
                              @Part("kunci_kamps") RequestBody kunci_kamps,
                              @Part("id_desa") RequestBody id_desa,
                              @Part("id_user") RequestBody id_user,
                              @Part("id_surat_kategori") RequestBody id_surat_kategori,
                              @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                              @Part("nama_surat") RequestBody nama_surat,
                              @Part("note_surat") RequestBody note_surat,
                              @Part("anak_ke") RequestBody anak_ke,
                              @Part("tgl_lahir_bayi") RequestBody tgl_lahir_bayi,
                              @Part("pukul") RequestBody pukul,
                              @Part("nama_bayi") RequestBody nama_bayi,
                              @Part("nama_ayah") RequestBody nama_ayah,
                              @Part("tempat_tgl_lahir_ayah") RequestBody tempat_tgl_lahir_ayah,
                              @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                              @Part("agama_ayah") RequestBody agama_ayah,
                              @Part("no_ktp_ayah") RequestBody no_ktp_ayah,
                              @Part("alamat_ayah") RequestBody alamat_ayah,
                              @Part("nama_ibu") RequestBody nama_ibu,
                              @Part("tempat_tgl_lahir_ibu") RequestBody tempat_tgl_lahir_ibu,
                              @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                              @Part("agama_ibu") RequestBody agama_ibu,
                              @Part("no_ktp_ibu") RequestBody no_ktp_ibu,
                              @Part("alamat_ibu") RequestBody alamat_ibu,
                              @Part MultipartBody.Part FileSyarat1,
                              @Part MultipartBody.Part FileSyarat2,
                              @Part MultipartBody.Part FileSyarat3,
                              @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    //4
    @Multipart
    @POST("surat")
    Call<Ress> PostKelahiran4(@Header("Authorization") String authHeader,
                              @Part("kunci_kamps") RequestBody kunci_kamps,
                              @Part("id_desa") RequestBody id_desa,
                              @Part("id_user") RequestBody id_user,
                              @Part("id_surat_kategori") RequestBody id_surat_kategori,
                              @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                              @Part("nama_surat") RequestBody nama_surat,
                              @Part("note_surat") RequestBody note_surat,
                              @Part("anak_ke") RequestBody anak_ke,
                              @Part("tgl_lahir_bayi") RequestBody tgl_lahir_bayi,
                              @Part("pukul") RequestBody pukul,
                              @Part("nama_bayi") RequestBody nama_bayi,
                              @Part("nama_ayah") RequestBody nama_ayah,
                              @Part("tempat_tgl_lahir_ayah") RequestBody tempat_tgl_lahir_ayah,
                              @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                              @Part("agama_ayah") RequestBody agama_ayah,
                              @Part("no_ktp_ayah") RequestBody no_ktp_ayah,
                              @Part("alamat_ayah") RequestBody alamat_ayah,
                              @Part("nama_ibu") RequestBody nama_ibu,
                              @Part("tempat_tgl_lahir_ibu") RequestBody tempat_tgl_lahir_ibu,
                              @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                              @Part("agama_ibu") RequestBody agama_ibu,
                              @Part("no_ktp_ibu") RequestBody no_ktp_ibu,
                              @Part("alamat_ibu") RequestBody alamat_ibu,
                              @Part MultipartBody.Part FileSyarat1,
                              @Part MultipartBody.Part FileSyarat2,
                              @Part MultipartBody.Part FileSyarat3,
                              @Part MultipartBody.Part FileSyarat4,
                              @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);


    //Insert Surat Kelahiran
    //1
    @Multipart
    @POST("surat")
    Call<Ress> PostKematian1(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_surat_kategori") RequestBody id_surat_kategori,
                            @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                            @Part("nama_surat") RequestBody nama_surat,
                            @Part("note_surat") RequestBody note_surat,
                            @Part("nama") RequestBody nama,
                            @Part("tgl_lahir") RequestBody tgl_lahir,
                            @Part("jenis_kelamin") RequestBody jenis_kelamin,
                            @Part("agama") RequestBody agama,
                            @Part("pekerjaan") RequestBody pekerjaan,
                            @Part("alamat") RequestBody alamat,
                            @Part("meninggal_tgl") RequestBody meninggal_tgl,
                            @Part("jam") RequestBody jam,
                            @Part("di") RequestBody di,
                            @Part("disebabkan_karena") RequestBody disebabkan_karena,
                            @Part MultipartBody.Part FileSyarat1,
                            @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);
    //2
    @Multipart
    @POST("surat")
    Call<Ress> PostKematian2(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_surat_kategori") RequestBody id_surat_kategori,
                            @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                            @Part("nama_surat") RequestBody nama_surat,
                            @Part("note_surat") RequestBody note_surat,
                            @Part("nama") RequestBody nama,
                            @Part("tgl_lahir") RequestBody tgl_lahir,
                            @Part("jenis_kelamin") RequestBody jenis_kelamin,
                            @Part("agama") RequestBody agama,
                            @Part("pekerjaan") RequestBody pekerjaan,
                            @Part("alamat") RequestBody alamat,
                            @Part("meninggal_tgl") RequestBody meninggal_tgl,
                            @Part("jam") RequestBody jam,
                            @Part("di") RequestBody di,
                            @Part("disebabkan_karena") RequestBody disebabkan_karena,
                            @Part MultipartBody.Part FileSyarat1,
                            @Part MultipartBody.Part FileSyarat2,
                            @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);
    //2
    @Multipart
    @POST("surat")
    Call<Ress> PostKematian3(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_surat_kategori") RequestBody id_surat_kategori,
                            @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                            @Part("nama_surat") RequestBody nama_surat,
                            @Part("note_surat") RequestBody note_surat,
                            @Part("nama") RequestBody nama,
                            @Part("tgl_lahir") RequestBody tgl_lahir,
                            @Part("jenis_kelamin") RequestBody jenis_kelamin,
                            @Part("agama") RequestBody agama,
                            @Part("pekerjaan") RequestBody pekerjaan,
                            @Part("alamat") RequestBody alamat,
                            @Part("meninggal_tgl") RequestBody meninggal_tgl,
                            @Part("jam") RequestBody jam,
                            @Part("di") RequestBody di,
                            @Part("disebabkan_karena") RequestBody disebabkan_karena,
                            @Part MultipartBody.Part FileSyarat1,
                            @Part MultipartBody.Part FileSyarat2,
                            @Part MultipartBody.Part FileSyarat3,
                            @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    //4
    @Multipart
    @POST("surat")
    Call<Ress> PostKematian4(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_surat_kategori") RequestBody id_surat_kategori,
                            @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                            @Part("nama_surat") RequestBody nama_surat,
                            @Part("note_surat") RequestBody note_surat,
                            @Part("nama") RequestBody nama,
                            @Part("tgl_lahir") RequestBody tgl_lahir,
                            @Part("jenis_kelamin") RequestBody jenis_kelamin,
                            @Part("agama") RequestBody agama,
                            @Part("pekerjaan") RequestBody pekerjaan,
                            @Part("alamat") RequestBody alamat,
                            @Part("meninggal_tgl") RequestBody meninggal_tgl,
                            @Part("jam") RequestBody jam,
                            @Part("di") RequestBody di,
                            @Part("disebabkan_karena") RequestBody disebabkan_karena,
                            @Part MultipartBody.Part FileSyarat1,
                            @Part MultipartBody.Part FileSyarat2,
                            @Part MultipartBody.Part FileSyarat3,
                            @Part MultipartBody.Part FileSyarat4,
                            @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);



    //Insert Surat 1
    @Multipart
    @POST("surat")
    Call<Ress> PostSurat(@Header("Authorization") String authHeader,
                         @Part("kunci_kamps") RequestBody kunci_kamps,
                         @Part("id_desa") RequestBody id_desa,
                         @Part("id_user") RequestBody id_user,
                         @Part("id_surat_kategori") RequestBody id_surat_kategori,
                         @Part("nama_surat") RequestBody nama_surat,
                         @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                         @Part("note_surat") RequestBody note_surat,
                         @Part MultipartBody.Part FileSyarat1,
                         @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    @Multipart
    @POST("surat")
    Call<Ress> PostSurat(@Header("Authorization") String authHeader,
                         @Part("kunci_kamps") RequestBody kunci_kamps,
                         @Part("id_desa") RequestBody id_desa,
                         @Part("id_user") RequestBody id_user,
                         @Part("id_surat_kategori") RequestBody id_surat_kategori,
                         @Part("nama_surat") RequestBody nama_surat,
                         @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                         @Part("note_surat") RequestBody note_surat,
                         @Part MultipartBody.Part FileSyarat1,
                         @Part MultipartBody.Part FileSyarat2,
                         @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    @Multipart
    @POST("surat")
    Call<Ress> PostSurat(@Header("Authorization") String authHeader,
                         @Part("kunci_kamps") RequestBody kunci_kamps,
                         @Part("id_desa") RequestBody id_desa,
                         @Part("id_user") RequestBody id_user,
                         @Part("id_surat_kategori") RequestBody id_surat_kategori,
                         @Part("nama_surat") RequestBody nama_surat,
                         @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                         @Part("note_surat") RequestBody note_surat,
                         @Part MultipartBody.Part FileSyarat1,
                         @Part MultipartBody.Part FileSyarat2,
                         @Part MultipartBody.Part FileSyarat3,
                         @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    @Multipart
    @POST("surat")
    Call<Ress> PostSurat(@Header("Authorization") String authHeader,
                         @Part("kunci_kamps") RequestBody kunci_kamps,
                         @Part("id_desa") RequestBody id_desa,
                         @Part("id_user") RequestBody id_user,
                         @Part("id_surat_kategori") RequestBody id_surat_kategori,
                         @Part("nama_surat") RequestBody nama_surat,
                         @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                         @Part("note_surat") RequestBody note_surat,
                         @Part MultipartBody.Part FileSyarat1,
                         @Part MultipartBody.Part FileSyarat2,
                         @Part MultipartBody.Part FileSyarat3,
                         @Part MultipartBody.Part FileSyarat4,
                         @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);



    //Insert Laporan 1
    @Multipart
    @POST("laporan/new")
    Call<Ress> PostLaporan1(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_kategori") RequestBody id_kategori,
                            @Part("nama_laporan") RequestBody nama_laporan,
                            @Part("deskripsi_laporan") RequestBody deskripsi_laporan,
                            @Part MultipartBody.Part foto1);

    //Insert Laporan 2
    @Multipart
    @POST("laporan/new")
    Call<Ress> PostLaporan2(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_kategori") RequestBody id_kategori,
                            @Part("nama_laporan") RequestBody nama_laporan,
                            @Part("deskripsi_laporan") RequestBody deskripsi_laporan,
                            @Part MultipartBody.Part foto1,
                            @Part MultipartBody.Part foto2);

    //Insert Laporan 3
    @Multipart
    @POST("laporan/new")
    Call<Ress> PostLaporan3(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_kategori") RequestBody id_kategori,
                            @Part("nama_laporan") RequestBody nama_laporan,
                            @Part("deskripsi_laporan") RequestBody deskripsi_laporan,
                            @Part MultipartBody.Part foto1,
                            @Part MultipartBody.Part foto2,
                            @Part MultipartBody.Part foto3);

    //Insert Laporan 4
    @Multipart
    @POST("laporan/new")
    Call<Ress> PostLaporan4(@Header("Authorization") String authHeader,
                            @Part("kunci_kamps") RequestBody kunci_kamps,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_kategori") RequestBody id_kategori,
                            @Part("nama_laporan") RequestBody nama_laporan,
                            @Part("deskripsi_laporan") RequestBody deskripsi_laporan,
                            @Part MultipartBody.Part foto1,
                            @Part MultipartBody.Part foto2,
                            @Part MultipartBody.Part foto3,
                            @Part MultipartBody.Part foto4);

    //Insert Produk 1
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Header("Authorization") String authHeader,
                          @Part("kunci_kamps") RequestBody kunci_kamps,
                          @Part("id_toko") RequestBody id_toko,
                          @Part("nama_produk") RequestBody nama_produk,
                          @Part("harga_produk") RequestBody harga_produk,
                          @Part("deskripsi_produk") RequestBody deskripsi_produk,
                          @Part MultipartBody.Part foto1);

    //Insert Produk 2
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Header("Authorization") String authHeader,
                          @Part("kunci_kamps") RequestBody kunci_kamps,
                          @Part("id_toko") RequestBody id_toko,
                          @Part("nama_produk") RequestBody nama_produk,
                          @Part("harga_produk") RequestBody harga_produk,
                          @Part("deskripsi_produk") RequestBody deskripsi_produk,
                          @Part MultipartBody.Part foto1,
                          @Part MultipartBody.Part foto2);

    //Insert Produk 3
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Header("Authorization") String authHeader,
                          @Part("kunci_kamps") RequestBody kunci_kamps,
                          @Part("id_toko") RequestBody id_toko,
                          @Part("nama_produk") RequestBody nama_produk,
                          @Part("harga_produk") RequestBody harga_produk,
                          @Part("deskripsi_produk") RequestBody deskripsi_produk,
                          @Part MultipartBody.Part foto1,
                          @Part MultipartBody.Part foto2,
                          @Part MultipartBody.Part foto3);

    //Insert Produk 4
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Header("Authorization") String authHeader,
                          @Part("kunci_kamps") RequestBody kunci_kamps,
                          @Part("id_toko") RequestBody id_toko,
                          @Part("nama_produk") RequestBody nama_produk,
                          @Part("harga_produk") RequestBody harga_produk,
                          @Part("deskripsi_produk") RequestBody deskripsi_produk,
                          @Part MultipartBody.Part foto1,
                          @Part MultipartBody.Part foto2,
                          @Part MultipartBody.Part foto3,
                          @Part MultipartBody.Part foto4);

    //Toko 1
    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Header("Authorization") String authHeader,
                        @Part("kunci_kamps") RequestBody kunci_kamps,
                        @Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1);

    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Header("Authorization") String authHeader,
                        @Part("kunci_kamps") RequestBody kunci_kamps,
                        @Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1,
                        @Part MultipartBody.Part foto2);

    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Header("Authorization") String authHeader,
                        @Part("kunci_kamps") RequestBody kunci_kamps,
                        @Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1,
                        @Part MultipartBody.Part foto2,
                        @Part MultipartBody.Part foto3);

    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Header("Authorization") String authHeader,
                        @Part("kunci_kamps") RequestBody kunci_kamps,
                        @Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1,
                        @Part MultipartBody.Part foto2,
                        @Part MultipartBody.Part foto3,
                        @Part MultipartBody.Part foto4);

    @FormUrlEncoded
    @POST("bank_sampah/saldo")
    Call<NewResponse> SaldoBankSampah(@Header("Authorization") String authHeader,
                                      @Field("kunci_kamps") String kunci_kamps,
                                      @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("bank_sampah/ambil_reward")
    Call<Ress> AmbilReward(@Header("Authorization") String authHeader,
                           @Field("kunci_kamps") String kunci_kamps,
                           @Field("id_user") String id_user,
                           @Field("id_reward_bs") String id_reward_bs,
                           @Field("qty") String qty,
                           @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("toko/produk")
    Call<ResponseModel> Produk(@Header("Authorization") String authHeader,
                               @Field("kunci_kamps") String kunci_kamps,
                               @Field("id_toko") String id_toko);

    @FormUrlEncoded
    @POST("bank_sampah/lokasi")
    Call<ResponseModel> LokasiBankSampah(@Header("Authorization") String authHeader,
                                         @Field("kunci_kamps") String kunci_kamps,
                                         @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("surat/list")
    Call<ResponseModel> GetSurat(@Header("Authorization") String authHeader,
                                 @Field("kunci_kamps") String kunci_kamps,
                                 @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("user/detail")
    Call<NewResponse> user_detail(@Header("Authorization") String authHeader,
                                  @Field("kunci_kamps") String kunci_kamps,
                                  @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("berita_bogor")
    Call<Response> Berita_bogor(@Header("Authorization") String authHeader,
                                @Field("kunci_kamps") String kunci_kamps,
                                @Field("page") Integer page);

    @FormUrlEncoded
    @POST("laporan/kategori")
    Call<ResponseModel> Laporan_Kategori(@Header("Authorization") String authHeader,
                                         @Field("kunci_kamps") String kunci_kamps,
                                         @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("laporan/list")
    Call<ResponseModel> ListLaporan(@Header("Authorization") String authHeader,
                                    @Field("kunci_kamps") String kunci_kamps,
                                    @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("kemajuan_desa/list_rw")
    Call<ResponseModel> ListRW(@Header("Authorization") String authHeader,
                               @Field("kunci_kamps") String kunci_kamps,
                               @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("kemajuan_desa/list")
    Call<ResponseModel> ListKemajuanDesa(@Header("Authorization") String authHeader,
                                         @Field("kunci_kamps") String kunci_kamps,
                                         @Field("id_desa") String id_desa,
                                         @Field("kegiatan") String kegiatan,
                                         @Field("rw") String rw);

    //GET
    @GET("kemajuan_desa/get_kegiatan")
    Call<KontolFajar> GetKegiatan(@Header("Authorization") String authHeader,
                                  @Query("kunci_kamps") String kunci_kamps);

    @GET("kat_surat")
    Call<ResponseModel> Kategori_Surat(@Header("Authorization") String authHeader,
                                       @Query("kunci_kamps") String kunci_kamps);

    @GET("info/covid")
    Call<NewResponse> InfoCovid(@Header("Authorization") String authHeader,
                                @Query("kunci_kamps") String kunci_kamps);

    @GET("info/anggaran")
    Call<NewResponse> InfoAnggaran(@Header("Authorization") String authHeader,
                                   @Query("kunci_kamps") String kunci_kamps);

    @GET("info/bansos")
    Call<NewResponse> InfoBansos(@Header("Authorization") String authHeader,
                                 @Query("kunci_kamps") String kunci_kamps);

    @GET("info/profil_desa")
    Call<NewResponse> InfoProfilDesa(@Header("Authorization") String authHeader,
                                     @Query("kunci_kamps") String kunci_kamps);

    @GET("info/umkm")
    Call<NewResponse> InfoUMKM(@Header("Authorization") String authHeader,
                               @Query("kunci_kamps") String kunci_kamps);

    @GET("info/dpt")
    Call<NewResponse> InfoDPT(@Header("Authorization") String authHeader,
                              @Query("kunci_kamps") String kunci_kamps);

//    @GET("surat/list")
//    Call<ResponseModel> GetSurat(@Query("id_user") String id_user);

    @GET("laporan/kategori")
    Call<ResponseModel> Kategori_Laporan(@Header("Authorization") String authHeader,
                                         @Query("kunci_kamps") String kunci_kamps);

    @GET("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader,
                              @Query("kunci_kamps") String kunci_kamps);

    @GET("berita")
    Call<ResponseModel> Berita(@Header("Authorization") String authHeader,
                               @Query("kunci_kamps") String kunci_kamps);

    @FormUrlEncoded
    @POST("pariwisata")
    Call<ResponseModel> Pariwisata(@Header("Authorization") String authHeader,
                                   @Field("kunci_kamps") String kunci_kamps,
                                   @Field("id_desa") String id_desa);

    @GET("banner")
    Call<ResponseModel> Banner(@Header("Authorization") String authHeader,
                               @Query("kunci_kamps") String kunci_kamps);


    @GET("prov.json")
    Call<ResponseCovid> Covid(@Header("Authorization") String authHeader,
                              @Query("kunci_kamps") String kunci_kamps);
}
