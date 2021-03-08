package com.example.newsappmvvm.repository

import com.example.newsappmvvm.api.RetrofitInstance
import com.example.newsappmvvm.db.ArticleDatabase
import com.example.newsappmvvm.model.Article
import com.example.newsappmvvm.model.NewsResponse
import retrofit2.Response

class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSaveNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticles(article)
}