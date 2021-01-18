package com.prashd.dadjokes.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.prashd.dadjokes.R
import com.prashd.dadjokes.databinding.ActivityMainBinding
import com.prashd.dadjokes.network.RetrofitClientInstance
import com.prashd.dadjokes.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewmodel by lazy {
        val factory = JokeViewModelFactory(JokeRepository())
        ViewModelProviders.of(this, factory).get(JokeViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel.randomJoke.observe(this, Observer {
            binding.tvTest.text = it.joke
        })

        binding.btnGenerate.setOnClickListener {
            viewmodel.getRandomDadJoke()
            viewmodel.randomJoke.observe(this, Observer {
                binding.tvTest.text = it.joke
            })
        }
    }
}