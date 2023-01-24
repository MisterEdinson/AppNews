package com.example.appnews.ui.home

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.databinding.FragmentHomeBinding
import com.example.appnews.ui.adapters.NewsAdapter
import com.example.appnews.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val mbinding get() = binding!!

    private val viewModel by viewModels<HomeViewModel>()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        var job: Job? = null
        etSearchHome.addTextChangedListener { text: Editable? ->
            job?.cancel()
            job = MainScope().launch {
                text?.let {
                    if (it.toString().isEmpty()) {
                        viewModel.getNews("ua")
                    } else {
                        viewModel.getNews(text.toString())
                    }
                }
            }
        }
        viewModel.newsLiveData.observe(viewLifecycleOwner) { responce ->
            when (responce) {
                is Resource.Success -> {
                    responce.data?.let {
                        progBarNews.setVisibility(View.INVISIBLE)
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    //need progress bar hidden
                    responce.data?.let {
                        Log.e("check data", "Home fragment: error : ${it}")
                    }
                }
                is Resource.Loading -> {
                    progBarNews.setVisibility(View.VISIBLE)
                }
            }
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        rvHome.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}