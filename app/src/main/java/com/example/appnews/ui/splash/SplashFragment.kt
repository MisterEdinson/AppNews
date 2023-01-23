package com.example.appnews.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnews.R
import com.example.appnews.databinding.FragmentDetailBinding
import com.example.appnews.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var binding : FragmentSplashBinding? = null
    private val mbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return mbinding.root
    }
}