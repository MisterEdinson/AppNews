package com.example.appnews.models

data class Model(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)