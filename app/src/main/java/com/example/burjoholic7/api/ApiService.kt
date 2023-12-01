package com.example.burjoholic7.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login") // Replace with your actual login endpoint
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}