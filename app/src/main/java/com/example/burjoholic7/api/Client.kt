package com.example.burjoholic7.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class Client {
    companion object {

        var apiService: ApiService = rebuild_client("No Token")
        fun rebuild_client (token: String): ApiService {
            var client = OkHttpClient.Builder()
                .addInterceptor(ServiceInterceptor(token))
                .build()

            apiService = Retrofit.Builder()
                    .baseUrl("https://burjo.zzidzz.me/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)

            return apiService
        }
    }

    class ServiceInterceptor(token: String?) : Interceptor {
        var token = token
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .build();

            return chain.proceed(request)
        }

    }
}