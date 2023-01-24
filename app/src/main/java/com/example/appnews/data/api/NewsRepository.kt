package com.example.appnews.data.api

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: ApiService) {
    suspend fun getNews(countryCode:String,pageNumber:Int) =
        newsService.getHeadLines(countryCode = countryCode, page = pageNumber)
}