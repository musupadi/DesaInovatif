package com.destinyapp.desainovatif.API;

import com.destinyapp.desainovatif.Model.NewModel.Covid.ResponseCovid;
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
    Call<ResponseModel> login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("user/login")
    Call<NewResponse> Login_user(@Field("no_hp") String no_hp,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader,
                              @Field("namaSurat") String namaSurat);

    @FormUrlEncoded
    @POST("pecorine")
    Call<ResponseData> Kyoko(@Field("no_hp") String no_hp);

    @FormUrlEncoded
    @POST("toko")
    Call<ResponseModel> Toko(@Field("id_toko") String id_toko,
                              @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("kat_surat/sub_kat")
    Call<ResponseModel> SubKatSurat(@Field("id_surat_kategori") String id_surat_kategori);

    @FormUrlEncoded
    @POST("toko/my_toko")
    Call<ResponseModel> MyToko(@Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("toko/gallery_produk")
    Call<ResponseModel> GalleryProduk(@Field("id_produk") String id_produk);

    @FormUrlEncoded
    @POST("bank_sampah/reward")
    Call<ResponseModel> ListRewardBankSampah(@Field("id_desa") String id_desa);


    @FormUrlEncoded
    @POST("bank_sampah/daftar_jenis_sampah")
    Call<ResponseModel> JenisSampah(@Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("bank_sampah/trans_reward")
    Call<ResponseModel> TransaksiSampah(@Field("id_user") String id_user);



    @FormUrlEncoded
    @POST("laporan/surat")
    Call<ResponseModel> SuratLaporan(@Field("id_user") String id_user,
                                     @Field("id_surat_kategori") String id_surat_kategori,
                                     @Field("nama_surat") String nama_surat);



    @FormUrlEncoded
    @POST("toko/gallery")
    Call<ResponseModel> GalleryToko(@Field("id_toko") String id_toko);

    @FormUrlEncoded
    @POST("user/list_user_rt")
    Call<ResponseModel> ListUserRT(@Field("id_user") String id_user);

    //Insert Surat 1
    @Multipart
    @POST("surat")
    Call<Ress> PostSurat(@Part("id_desa") RequestBody id_desa,
                            @Part("id_user") RequestBody id_user,
                            @Part("id_surat_kategori") RequestBody id_surat_kategori,
                            @Part("nama_surat") RequestBody nama_surat,
                            @Part("id_surat_kategori_sub") RequestBody id_surat_kategori_sub,
                            @Part("note_surat") RequestBody note_surat,
                            @Part MultipartBody.Part FileSyarat1,
                            @Part("id_user_bersangkutan") RequestBody id_user_bersangkutan);

    @Multipart
    @POST("surat")
    Call<Ress> PostSurat(@Part("id_desa") RequestBody id_desa,
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
    Call<Ress> PostSurat(@Part("id_desa") RequestBody id_desa,
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
    Call<Ress> PostSurat(@Part("id_desa") RequestBody id_desa,
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
    Call<Ress> PostLaporan1(@Part("id_user") RequestBody id_user,
                                     @Part("id_desa") RequestBody id_desa,
                                     @Part("id_kategori") RequestBody id_kategori,
                                     @Part("nama_laporan") RequestBody nama_laporan,
                                     @Part("deskripsi_laporan") RequestBody deskripsi_laporan,
                                     @Part MultipartBody.Part foto1);

    //Insert Laporan 2
    @Multipart
    @POST("laporan/new")
    Call<Ress> PostLaporan2(@Part("id_user") RequestBody id_user,
                            @Part("id_desa") RequestBody id_desa,
                            @Part("id_kategori") RequestBody id_kategori,
                            @Part("nama_laporan") RequestBody nama_laporan,
                            @Part("deskripsi_laporan") RequestBody deskripsi_laporan,
                            @Part MultipartBody.Part foto1,
                            @Part MultipartBody.Part foto2);

    //Insert Laporan 3
    @Multipart
    @POST("laporan/new")
    Call<Ress> PostLaporan3(@Part("id_user") RequestBody id_user,
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
    Call<Ress> PostLaporan4(@Part("id_user") RequestBody id_user,
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
    Call<Ress> PostProduk(@Part("id_toko") RequestBody id_toko,
                                 @Part("nama_produk") RequestBody nama_produk,
                                 @Part("harga_produk") RequestBody harga_produk,
                                 @Part("deskripsi_produk") RequestBody deskripsi_produk,
                                 @Part MultipartBody.Part foto1);

    //Insert Produk 2
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Part("id_toko") RequestBody id_toko,
                          @Part("nama_produk") RequestBody nama_produk,
                          @Part("harga_produk") RequestBody harga_produk,
                          @Part("deskripsi_produk") RequestBody deskripsi_produk,
                          @Part MultipartBody.Part foto1,
                          @Part MultipartBody.Part foto2);

    //Insert Produk 3
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Part("id_toko") RequestBody id_toko,
                          @Part("nama_produk") RequestBody nama_produk,
                          @Part("harga_produk") RequestBody harga_produk,
                          @Part("deskripsi_produk") RequestBody deskripsi_produk,
                          @Part MultipartBody.Part foto1,
                          @Part MultipartBody.Part foto2,
                          @Part MultipartBody.Part foto3);

    //Insert Produk 4
    @Multipart
    @POST("toko/produk_ins")
    Call<Ress> PostProduk(@Part("id_toko") RequestBody id_toko,
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
    Call<Ress> PostToko(@Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1);

    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1,
                        @Part MultipartBody.Part foto2);

    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Part("id_user") RequestBody id_user,
                        @Part("nama_toko") RequestBody nama_toko,
                        @Part("no_hp_toko") RequestBody no_hp_toko,
                        @Part("deskripsi_toko") RequestBody deskripsi_toko,
                        @Part("id_desa") RequestBody id_desa,
                        @Part MultipartBody.Part foto1,
                        @Part MultipartBody.Part foto2,
                        @Part MultipartBody.Part foto3);

    @Multipart
    @POST("toko/input_toko")
    Call<Ress> PostToko(@Part("id_user") RequestBody id_user,
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
    Call<NewResponse> SaldoBankSampah(@Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("bank_sampah/ambil_reward")
    Call<Ress> AmbilReward(@Field("id_user") String id_user,
                                  @Field("id_reward_bs") String id_reward_bs,
                                  @Field("qty") String qty,
                                  @Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("toko/produk")
    Call<ResponseModel> Produk(@Field("id_toko") String id_toko);

    @FormUrlEncoded
    @POST("bank_sampah/lokasi")
    Call<ResponseModel> LokasiBankSampah(@Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("surat/list")
    Call<ResponseModel> GetSurat(@Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("user/detail")
    Call<NewResponse> user_detail(@Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("berita_bogor")
    Call<Response> Berita_bogor(@Field("page") Integer page);

    @FormUrlEncoded
    @POST("laporan/kategori")
    Call<ResponseModel> Laporan_Kategori(@Field("id_desa") String id_desa);

    @FormUrlEncoded
    @POST("laporan/list")
    Call<ResponseModel> ListLaporan(@Field("id_user") String id_user);

    //GET
    @GET("kat_surat")
    Call<ResponseModel> Kategori_Surat();


//    @GET("surat/list")
//    Call<ResponseModel> GetSurat(@Query("id_user") String id_user);

    @GET("laporan/kategori")
    Call<ResponseModel> Kategori_Laporan();

    @GET("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader);

    @GET("berita")
    Call<ResponseModel> Berita(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("pariwisata")
    Call<ResponseModel> Pariwisata(@Field("id_desa") String id_desa);


    @GET("prov.json")
    Call<ResponseCovid> Covid();
}
