package com.example.appnews.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.R
import com.example.appnews.databinding.FragmentFavoriteBinding
import com.example.appnews.models.Article
import com.example.appnews.ui.adapters.FavoriteAdapter
import com.example.appnews.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private val _binding get() = binding!!
    private val viewModel by viewModels<FavoriteViewModel>()
    private var favoriteAdapter: FavoriteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.getFavoriteDB()
        viewModel.newsLiveData.observe(viewLifecycleOwner){ it ->
            when(it){
                is Resource.Success->{
                    it.data?.let {
                        //progBarNews.setVisibility(View.INVISIBLE)
                        favoriteAdapter?.differ?.submitList(it)
                    }
                }
                is Resource.Loading -> {
                    //progBarNews.setVisibility(View.VISIBLE)
                }
                else->{

                }
            }

        }
    }

    private fun init() {
        favoriteAdapter = FavoriteAdapter (
            { article -> delFavoriteNews(article) },
            { article -> openFragmentDetails(article) },
            { article -> refreshPage(article) }
        )

        rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun openFragmentDetails(article: Article) {
        val bundle = bundleOf("article" to article)
        findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
    }
    private fun delFavoriteNews(article: Article) {
        viewModel.delFavoriteDB(article)
    }
    private fun refreshPage(article: Article){
        val bundle = bundleOf("article" to article)
        findNavController().navigate(R.id.favoriteFragment, bundle)
    }

}