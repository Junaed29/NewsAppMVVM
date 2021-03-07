package com.example.newsappmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.ActivityNewsBinding
import com.example.newsappmvvm.db.ArticleDatabase
import com.example.newsappmvvm.repository.NewsRepository

class NewsActivity : AppCompatActivity() {
    private lateinit var activityNewsBinding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        activityNewsBinding.bottomNavigationView.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.newsNavHostFragment
            )
        )
    }
}