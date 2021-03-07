package com.example.newsappmvvm.ui

import androidx.lifecycle.ViewModel
import com.example.newsappmvvm.repository.NewsRepository

class NewsViewModel(
    val repository: NewsRepository
) : ViewModel() {
}