package com.example.appnews.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnews.R
import com.example.appnews.databinding.FragmentDetailBinding
import com.example.appnews.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var binding : FragmentSearchBinding? = null
    private val mbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        return mbinding.root
    }
}