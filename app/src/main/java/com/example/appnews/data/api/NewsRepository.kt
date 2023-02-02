package com.example.appnews.data.api

import com.example.appnews.data.db.ArticleDao
import com.example.appnews.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: ApiService,
    private val articleDao: ArticleDao
    ) {
    //all news
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getHeadLines(countryCode, pageNumber)

    //search
    suspend fun searchNews(query: String, pageNumber: Int) =
        newsService.getEveryThing(query, pageNumber)

    //favorite
    suspend fun favoriteNews(query: String, pageNumber: Int) =
        newsService.getEveryThing(query, pageNumber)

    suspend fun getFavoriteArticles() = articleDao.getAllArticles()
    suspend fun addToFavorite(article: Article) = articleDao.insert(article)
    suspend fun deleteFromFavorite(article: Article) = articleDao.delete(article)
}