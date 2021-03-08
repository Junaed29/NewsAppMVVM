package com.example.newsappmvvm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.model.NewsResponse
import com.example.newsappmvvm.repository.NewsRepository
import com.example.newsappmvvm.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    val breakingNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val searchNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String): Job = viewModelScope.launch {
        breakingNewsLiveData.postValue(Resource.Loading())
        val response = repository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNewsLiveData.postValue(handleBreakingResponse(response))
    }

    fun getSearchNews(searchQuery: String): Job = viewModelScope.launch {
        searchNewsLiveData.postValue(Resource.Loading())
        val response = repository.getSearchNews(searchQuery, searchNewsPage)
        searchNewsLiveData.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.upsert(article)
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        repository.deleteArticle(article)
    }

    fun getSaveNews() = repository.getSaveNews()
}