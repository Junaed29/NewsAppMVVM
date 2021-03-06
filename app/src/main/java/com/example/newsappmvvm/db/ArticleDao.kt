package com.example.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsappmvvm.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticles(article: Article)
}