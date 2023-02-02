package com.example.appnews.di

import android.content.Context
import androidx.room.Room
import com.example.appnews.data.api.ApiService
import com.example.appnews.data.db.ArticleDao
import com.example.appnews.data.db.ArticleDataBase
import com.example.appnews.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun BaseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(logging()).build()

    @Provides
    @Singleton
    fun provideRetroFit(baseUrl:String):ApiService =
        Retrofit.Builder()
            .baseUrl(BaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ApiService::class.java)
    @Provides
    @Singleton
    fun provideArticleDataBase(@ApplicationContext context:Context) =
        Room.databaseBuilder(
            context,
            ArticleDataBase::class.java,
            "article_database"
        ).build()

    @Provides
    fun provideArticleDao(appDataBase : ArticleDataBase): ArticleDao{
        return appDataBase.getArticleDao()
    }
}