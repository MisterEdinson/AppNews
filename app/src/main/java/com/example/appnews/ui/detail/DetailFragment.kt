package com.example.appnews.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appnews.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private val mbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return mbinding.root
    }
}