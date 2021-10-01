package com.destinyapp.desainovatif.API;

import com.destinyapp.desainovatif.Model.NewModel.Covid.ResponseCovid;
import com.destinyapp.desainovatif.Model.NewModel.KontolFajar;
import com.destinyapp.desainovatif.Model.NewModel.NewResponse;
import com.destinyapp.desainovatif.Model.NewModel.Response;
import com.destinyapp.desainovatif.Model.ResponseData;
import com.destinyapp.desainovatif.Model.ResponseModel;
import com.destinyapp.desainovatif.Model.Ress;

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
