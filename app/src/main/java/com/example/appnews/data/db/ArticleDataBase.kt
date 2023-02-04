package com.example.appnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnews.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = true)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}