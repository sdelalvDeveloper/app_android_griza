package com.sebasdelalv.proyecto_griza.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.102:8080/"

    fun getRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos
            .build()
            .create(ApiService::class.java)
    }
}