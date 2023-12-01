package com.example.burjoholic7.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login") // Replace with your actual login endpoint
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        ): Call<LoginResponse>
}