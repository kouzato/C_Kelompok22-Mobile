package com.kelompok22.veterinarycareapp.API;

import com.kelompok22.veterinarycareapp.model.Keluhan;
import com.kelompok22.veterinarycareapp.model.ListDokter;
import com.kelompok22.veterinarycareapp.model.Login;
import com.kelompok22.veterinarycareapp.model.Register;
import com.kelompok22.veterinarycareapp.model.ResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIRequestData {

    @GET("dashboard3")
    Call<ResponseModel> ardArtikelData();



    @Headers({"Accept: application/json"})
    //@FormUrlEncoded
    @POST("login")
    Call<Login> loginresponse (
            @Query("username") String username,
            @Query("password") String password
    );
    @Headers({"Accept: application/json"})
    //@FormUrlEncoded
    @POST("register")
    Call<Register> registerresponse (
            @Query("name") String name,
            @Query("username") String username,
            @Query("email") String email,
            @Query("password") String password,
            @Query("password_confirmation") String password_confirmation
    );
    @Headers({"Accept: application/json"})
    @GET("dashboard3")
    Call<ResponseModel> search(
            @Query("query") String query
    );
    @Multipart
    @POST("profile3")
    Call<Keluhan> keluhanResponse(

            @Query("nama") String nama,
            @Query("email") String email,
            @Query("isi") String isi,
            @Query("foto") String foto
    );

    @GET("listdokter")
    Call<ListDokter> listdokter();



}