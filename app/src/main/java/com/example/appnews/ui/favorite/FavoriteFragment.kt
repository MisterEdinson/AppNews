package com.example.appnews.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.databinding.FragmentFavoriteBinding
import com.example.appnews.ui.adapters.FavoriteAdapter
import com.example.appnews.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private val mbinding get() = binding!!
    lateinit var recyclerView: RecyclerView
    private val bundleArgs: FavoriteFragmentArgs by navArgs()
    private val viewModel by viewModels<FavoriteViewModel>()
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.getFavoriteDB()
        viewModel.newsLiveData.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success->{
                    it.data?.let {
                        favoriteAdapter.differ.submitList(it)
                    }
                }
                else->{

                }
            }

        }
    }

    private fun init() {
        favoriteAdapter = FavoriteAdapter { article -> getFavorite() }
        rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getFavorite() {
        viewModel.getFavoriteDB()
    }

}