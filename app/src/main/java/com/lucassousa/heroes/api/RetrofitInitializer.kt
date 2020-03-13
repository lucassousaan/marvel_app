package com.lucassousa.heroes.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lucassousa.heroes.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInitializer {
    private const val BASE_URL = BuildConfig.BASE_URL
    private var retrofit: Retrofit? = null
    private val gson: Gson = GsonBuilder().create()

    private val httpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    fun <T> create(serviceClass: Class<T>): T {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!.create(serviceClass)
    }
}