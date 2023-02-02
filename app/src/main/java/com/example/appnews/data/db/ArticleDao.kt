package com.example.appnews.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appnews.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getAllArticles():List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

}