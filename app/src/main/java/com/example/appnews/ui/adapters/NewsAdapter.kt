package com.example.appnews.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appnews.R
import com.example.appnews.models.Article
import kotlinx.android.synthetic.main.activity_item_news.view.*
import kotlinx.android.synthetic.main.fragment_detail.view.*


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_item_news, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(imgItemHeader)
            imgItemHeader.clipToOutline = true
            tvItemHeader.text = article.title
            tvItemAuth.text = article.id.toString()

            itemLayout.setOnClickListener{
                //var intent:Intent = (R.id.action_homeFragment_to_detailFragment)

                //Toast.makeText(context,"${tvItemHeader.text}",LENGTH_SHORT).show()
                val bundle = bundleOf("article" to it)
                view.run {
                    view.findNavController().navigate(
                                R.id.action_homeFragment_to_detailFragment,
                                bundle
                            )
                }
            }
        }


    }



    override fun getItemCount() = differ.currentList.size
}


