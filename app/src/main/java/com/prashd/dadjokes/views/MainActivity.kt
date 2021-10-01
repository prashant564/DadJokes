package com.prashd.dadjokes.views

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.prashd.dadjokes.R
import com.prashd.dadjokes.databinding.ActivityMainBinding
import com.prashd.dadjokes.repository.JokeRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewmodel by lazy {
        val factory = JokeViewModelFactory(JokeRepository())
        ViewModelProviders.of(this, factory).get(JokeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DadJokes)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tf: Typeface = Typeface.createFromAsset(assets, "fonts/Raleway-Regular.ttf")
        binding.tvTest.typeface = tf
        binding.tvTest.movementMethod = ScrollingMovementMethod()

        viewmodel.randomJoke.observe(this, Observer {
            if (it == null) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.tvTest.text = it.joke
            }
        })

        binding.btnGenerate.setOnClickListener {
            viewmodel.getRandomDadJoke()
            binding.progressBar.visibility = View.VISIBLE
            viewmodel.randomJoke.observe(this, Observer {
                if (it != null) {
                    binding.progressBar.visibility = View.GONE
                    binding.tvTest.text = it.joke
                }
            })
        }

        binding.tvTest.setOnClickListener {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", binding.tvTest.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Joke copied to clipboard", Toast.LENGTH_LONG).show()
        }
    }
}
