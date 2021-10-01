package com.prashd.dadjokes.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashd.dadjokes.network.JokeUI
import com.prashd.dadjokes.repository.JokeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {
    val randomJoke: MutableLiveData<JokeUI> = MutableLiveData()

    init {
        getRandomDadJoke()
    }

    fun getRandomDadJoke() = viewModelScope.launch {
        jokeRepository.getDadJokeFromNetwork()
            .catch {e ->
                Log.d("Joke", "${e.message}")
            }
            .collect {response ->
                response.body().let {
                    randomJoke.postValue(it)
            }
    }

    }
}