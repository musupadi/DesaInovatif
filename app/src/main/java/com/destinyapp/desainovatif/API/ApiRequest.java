package com.destinyapp.desainovatif.API;

import com.destinyapp.desainovatif.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader,
                              @Field("namaSurat") String namaSurat);

    //GET
    @GET("surat")
    Call<ResponseModel> Surat(@Header("Authorization") String authHeader);

    @GET("berita")
    Call<ResponseModel> Berita(@Header("Authorization") String authHeader);

    @GET("pariwisata")
    Call<ResponseModel> Pariwisata(@Header("Authorization") String authHeader);
}
