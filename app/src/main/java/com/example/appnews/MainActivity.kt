package com.example.appnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appnews.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private val mbinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mbinding.root)
            btmNavView.setupWithNavController(
                navController = nav_host_fragment.findNavController()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}