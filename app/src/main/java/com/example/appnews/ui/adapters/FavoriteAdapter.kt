package com.example.appnews.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appnews.R
import com.example.appnews.models.Article
import kotlinx.android.synthetic.main.activity_item_news.view.*

class FavoriteAdapter(
    val delFavoriteNews: (Article) -> Unit,
    val onClick: (Article) -> Unit,
    val refreshPage: (Article) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteNewsHolder>() {
    class FavoriteNewsHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_item_news, parent, false)

        return FavoriteNewsHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteNewsHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(imgItemHeader)
            imgItemHeader.clipToOutline = true
            tvItemHeader.text = article.title
            tvItemAuth.text = article.author ?: "NEWS CNN"

            imgClickDetails.setOnClickListener {
                onClick(article)
            }
            imgAddFavorite.setOnClickListener {
                delFavoriteNews(article)
                refreshPage(article)
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

}

