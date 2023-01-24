package com.example.appnews.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.R
import com.example.appnews.databinding.FragmentDetailBinding
import com.example.appnews.databinding.FragmentHomeBinding
import com.example.appnews.ui.adapters.NewsAdapter
import com.example.appnews.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    private val mbinding get() = binding!!

    private val viewModel by viewModels<HomeViewModel>()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.newsLiveData.observe(viewLifecycleOwner){ responce ->
            when(responce){
                is Resource.Success ->{
                    responce.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error ->{
                    //need progress bar hidden
                    responce.data?.let {
                        Log.e("check data", "Home fragment: error : ${it}")
                    }
                }
                is Resource.Loading ->{
                    //progress bar visible
                }
            }
        }
    }

    private fun initAdapter(){
        newsAdapter = NewsAdapter()
        rvHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}