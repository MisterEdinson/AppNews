package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private val mbinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_splash)
        Handler(Looper.myLooper()!!).postDelayed({
            setContentView(mbinding.root)
            btmNavView.setupWithNavController(
                navController = nav_host_fragment.findNavController()
            )
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}