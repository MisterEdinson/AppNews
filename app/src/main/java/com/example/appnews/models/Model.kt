package com.example.appnews.models

import java.io.Serializable

data class Model(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
): Serializable