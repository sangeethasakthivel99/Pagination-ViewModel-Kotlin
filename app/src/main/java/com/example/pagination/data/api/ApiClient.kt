package com.example.pagination.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    const val api_key = "6e63c2317fbe963d76c3bdc2b785f6d1"
    const val base_url = "https://api.themoviedb.org/3/"
    const val poster_base_url = "https://image.tmdb.org/t/p/w342"
    const val FIRST_PAGE = 1
    const val POST_PER_PAGE = 20

    fun getClient(): ApiService {
        val requestInterceptor = Interceptor {
            val url = it.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", api_key)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor it.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(base_url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create() )
            .build()
            .create(ApiService::class.java)
    }
}