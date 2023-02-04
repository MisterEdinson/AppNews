package com.example.appnews.data.db

import androidx.room.*
import com.example.appnews.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY id DESC")
    suspend fun getAllArticles():List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

}