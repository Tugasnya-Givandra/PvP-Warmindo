package com.example.burjoholic7.api

import com.example.burjoholic7.api.Login.LoginResponse
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @FormUrlEncoded
    @POST("login") // Replace with your actual login endpoint
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        ): Call<LoginResponse>

    // GET transaksi list
    @GET("transaksi")
    fun getTransaksiList(): Call<List<TransaksiResponse>>
}