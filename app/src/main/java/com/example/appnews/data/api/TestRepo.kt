package com.example.appnews.data.api

import javax.inject.Inject

class TestRepo @Inject constructor(private val newsService : ApiService) {
    suspend fun getAll() = newsService.getHeadLines()
}