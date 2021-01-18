package com.prashd.dadjokes.repository

import com.prashd.dadjokes.network.RetrofitClientInstance

class JokeRepository {
    suspend fun getDadJokeFromNetwork() = RetrofitClientInstance.api.getDadJokeFromNetwork("application/json")
}