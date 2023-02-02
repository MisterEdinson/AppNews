package com.example.appnews.ui.detail

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.CATEGORY_APP_BROWSER
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.appnews.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.activity_item_news.view.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private val mbinding get() = binding!!
    private val bundleArs:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val articleArgs = bundleArs.article
        articleArgs.let { article ->
            Glide.with(this).load(article.urlToImage).into(mbinding.imgDetail)
            mbinding.imgDetail.clipToOutline = true
            mbinding.tvDetailHeader.text = article.title
            mbinding.tvDetailDescription.text = article.description

            mbinding.btnClickWeb.setOnClickListener{
                try {
                    Intent()
                        .setAction(Intent.ACTION_VIEW)
                        .addCategory(Intent.CATEGORY_APP_BROWSER)
                        .setData(Uri.parse(takeIf { URLUtil.isValidUrl(article.url) }
                            ?.let {
                                article.url
                            }?:"https://google.com"))
                        .let {
                            ContextCompat.startActivity(requireContext(),it,null)
                        }
                }catch (_:Exception){

                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                startActivity(browserIntent)
            }
        }

        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}