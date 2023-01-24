package com.example.appnews.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.data.api.NewsRepository
import com.example.appnews.data.api.TestRepo
import com.example.appnews.models.Model
import com.example.appnews.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository : NewsRepository):ViewModel() {
    val newsLiveData : MutableLiveData<Resource<Model>> = MutableLiveData()
    var newsPage = 1

    init {
        getNews(countryCode = "ua")
    }

    private fun getNews(countryCode:String) =
        viewModelScope.launch {
            newsLiveData.postValue(Resource.Loading())
            val responcse = repository.getNews(countryCode = countryCode, pageNumber = newsPage)
            if(responcse.isSuccessful){
                responcse.body().let {res->
                    newsLiveData.postValue(Resource.Success(res))
                }
            }else{
                newsLiveData.postValue(Resource.Error(message = responcse.message()))
            }
        }
}