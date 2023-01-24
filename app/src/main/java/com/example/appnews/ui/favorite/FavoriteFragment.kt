package com.example.appnews.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnews.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var binding : FragmentFavoriteBinding? = null
    private val mbinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return mbinding.root
    }
}