package com.example.appnews.data.api

import com.example.appnews.models.Model
import com.example.appnews.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun getEveryThing(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String = API_KEY,
    ): Response<Model>

    @GET("/v2/top-headlines")
    suspend fun getHeadLines(
        @Query("country") countryCode: String = "",
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String = API_KEY,
    ): Response<Model>
}