package com.example.burjoholic7.api

import android.R.attr.value
import com.example.burjoholic7.api.Login.LoginResponse
import com.example.burjoholic7.api.Transaksi.TransaksiCreateRequest
import com.example.burjoholic7.api.Transaksi.TransaksiCreateResponse
import com.example.burjoholic7.api.Transaksi.TransaksiDetailResponse
import com.example.burjoholic7.api.Transaksi.TransaksiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @FormUrlEncoded
    @POST("login") // Replace with your actual login endpoint
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        ): Call<LoginResponse>

    // GET transaksi list
    @GET("transaksi/list")
    fun getTransaksiList(
        @Query("shift") shift: Int,
    ): Call<TransaksiResponse>

    @GET("transaksi/history")
    fun getTransaksiHistories(): Call<TransaksiResponse>

    @POST("transaksi/create")
    fun createTransaksi(@Body request: TransaksiCreateRequest): Call<TransaksiCreateResponse>
    
    @GET("transaksi/detail/{transaction_id}")
    fun getTransaksiDetail(
        @Path(value="transaction_id") transactionId: Int,
    ) : Call<TransaksiDetailResponse>

    @GET("transaksi/update-status-transaksi/{transaction_id}")
    fun updateTransaksi(
        @Path(value="transaction_id") transactionId: Int,
    ): Call<BasicResponse>

    @GET("menu/list")
    fun getMenuList(): Call<BasicListResponse>

    @GET("transaksi/rekap-shift")
    fun getRekapShift(
        @Query("shift")shift: Int,
    ): Call<BasicResponse>

}

