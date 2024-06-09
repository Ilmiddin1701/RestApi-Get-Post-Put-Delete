package com.ilmiddin1701.restapi_get_post_put_delete.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://plans1.pythonanywhere.com/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}