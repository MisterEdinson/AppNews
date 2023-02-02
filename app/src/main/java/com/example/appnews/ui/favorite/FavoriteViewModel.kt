package com.example.appnews.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.api.NewsRepository
import com.example.appnews.models.Article
import com.example.appnews.models.Model
import com.example.appnews.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val newsLiveData: MutableLiveData<Resource<List<Article>>> = MutableLiveData()
    init {
        getFavoriteDB()
    }

    fun getFavoriteDB() = viewModelScope.launch(Dispatchers.IO) {
        val res = repository.getFavoriteArticles()
        newsLiveData.postValue(Resource.Success(res))
    }

    fun saveFavorite(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        repository.addToFavorite(article = article)
    }

    fun getAllFavoriteNews() {
        viewModelScope.launch {
            repository.getFavoriteArticles()
        }
    }
}