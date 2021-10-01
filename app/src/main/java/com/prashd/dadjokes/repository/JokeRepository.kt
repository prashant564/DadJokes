package com.prashd.dadjokes.repository

import com.prashd.dadjokes.network.JokeUI
import com.prashd.dadjokes.network.RetrofitClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class JokeRepository {
    fun getDadJokeFromNetwork(): Flow<Response<JokeUI>> = flow {
        val jokesList = RetrofitClientInstance.api.getDadJokeFromNetwork("application/json")
        emit(jokesList)
    }.flowOn(Dispatchers.IO)
}
