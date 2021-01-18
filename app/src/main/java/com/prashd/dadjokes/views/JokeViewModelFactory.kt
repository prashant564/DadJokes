package com.prashd.dadjokes.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prashd.dadjokes.repository.JokeRepository

class JokeViewModelFactory(private val jokeRepository: JokeRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(jokeRepository) as T
    }
}