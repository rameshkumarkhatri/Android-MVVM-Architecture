package com.mobifyall.mvvm.service

import com.mobifyall.mvvm.models.EpisodesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Ramesh Kumar
 * r.khatri91@gmail.com
 */
interface ApiService {
    @GET("v3/anime/1/episodes/1")
    fun getEpisodes(): Call<EpisodesResponse>
}

class RetrofitFactory {
    companion object {
        private val client = OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
                .build()
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        val service: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }
}
