package com.example.appnews.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.api.NewsRepository
import com.example.appnews.models.Model
import com.example.appnews.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val newsLiveData: MutableLiveData<Resource<Model>> = MutableLiveData()
    var newsPage = 1

    init {
        searchNews("ua")
    }

    fun searchNews(url: String) =
        viewModelScope.launch {
            newsLiveData.postValue(Resource.Loading())
            val responcse = repository.searchNews(url, newsPage)
            if (responcse.isSuccessful) {
                responcse.body().let { res ->
                    newsLiveData.postValue(Resource.Success(res))
                }
            } else {
                newsLiveData.postValue(Resource.Error(responcse.message()))
            }
        }
}