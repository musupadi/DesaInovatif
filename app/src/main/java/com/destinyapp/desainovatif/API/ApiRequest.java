package com.destinyapp.desainovatif.API;

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

public interface ApiRequest {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("username") String username,
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
    @POST("toko/my_toko")
    Call<ResponseModel> MyToko(@Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("toko/gallery_produk")
    Call<ResponseModel> GalleryProduk(@Field("id_produk") String id_produk);

    @FormUrlEncoded
    @POST("toko/gallery")
    Call<ResponseModel> GalleryToko(@Field("id_toko") String id_toko);

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
    @POST("toko")
    Call<ResponseModel> PostToko(@Header("Authorization") String authHeader,
                                     @Part("namaToko") RequestBody namaToko,
                                     @Part("noHpToko") RequestBody noHpToko,
                                     @Part("deskripsiToko") RequestBody deskripsiToko,
                                     @Part MultipartBody.Part fotoCover,
                                     @Part MultipartBody.Part foto1);

    @Multipart
    @POST("toko")
    Call<ResponseModel> PostToko(@Header("Authorization") String authHeader,
                                 @Part("namaToko") RequestBody namaToko,
                                 @Part("noHpToko") RequestBody noHpToko,
                                 @Part("deskripsiToko") RequestBody deskripsiToko,
                                 @Part MultipartBody.Part fotoCover,
                                 @Part MultipartBody.Part foto1,
                                 @Part MultipartBody.Part foto2);

    @Multipart
    @POST("toko")
    Call<ResponseModel> PostToko(@Header("Authorization") String authHeader,
                                 @Part("namaToko") RequestBody namaToko,
                                 @Part("noHpToko") RequestBody noHpToko,
                                 @Part("deskripsiToko") RequestBody deskripsiToko,
                                 @Part MultipartBody.Part fotoCover,
                                 @Part MultipartBody.Part foto1,
                                 @Part MultipartBody.Part foto2,
                                 @Part MultipartBody.Part foto3);

    @Multipart
    @POST("toko")
    Call<ResponseModel> PostToko(@Header("Authorization") String authHeader,
                                 @Part("namaToko") RequestBody namaToko,
                                 @Part("noHpToko") RequestBody noHpToko,
                                 @Part("deskripsiToko") RequestBody deskripsiToko,
                                 @Part MultipartBody.Part fotoCover,
                                 @Part MultipartBody.Part foto1,
                                 @Part MultipartBody.Part foto2,
                                 @Part MultipartBody.Part foto3,
                                 @Part MultipartBody.Part foto4);

    @FormUrlEncoded
    @POST("toko/produk")
    Call<ResponseModel> Produk(@Field("id_toko") String id_toko);
    //GET
    @GET("kat_surat")
    Call<ResponseModel> Kategori_Surat();

    @GET("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader);

    @GET("berita")
    Call<ResponseModel> Berita(@Header("Authorization") String authHeader);

    @GET("pariwisata")
    Call<ResponseModel> Pariwisata(@Header("Authorization") String authHeader);
}
