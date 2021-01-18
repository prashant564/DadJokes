package com.prashd.dadjokes.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientInstance {

    companion object {

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl("https://icanhazdadjoke.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api: JokeApi by lazy {
            retrofit.create(JokeApi::class.java)
        }
    }
}