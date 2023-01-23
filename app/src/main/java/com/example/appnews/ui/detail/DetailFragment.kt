package com.example.appnews.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnews.R
import com.example.appnews.databinding.ActivityMainBinding
import com.example.appnews.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var binding : FragmentDetailBinding? = null
    private val mbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return mbinding.root
    }
}