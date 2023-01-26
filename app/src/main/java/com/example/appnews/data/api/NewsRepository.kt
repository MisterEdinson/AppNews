package com.example.appnews.data.api

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: ApiService) {
    //all news
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getHeadLines(countryCode, pageNumber)

    //search
    suspend fun searchNews(query: String, pageNumber: Int) =
        newsService.getEveryThing(query, pageNumber)

    //favorite
    suspend fun favoriteNews(query: String, pageNumber: Int) =
        newsService.getEveryThing(query, pageNumber)
}