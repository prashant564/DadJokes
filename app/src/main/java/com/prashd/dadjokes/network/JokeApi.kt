package com.prashd.dadjokes.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface JokeApi {
    @GET("/")
    suspend fun getDadJokeFromNetwork(@Header("Accept") accept: String): Response<JokeUI>
}