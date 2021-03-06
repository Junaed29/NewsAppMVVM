package com.example.newsappmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    private lateinit var activityNewsBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)

        activityNewsBinding.bottomNavigationView.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.newsNavHostFragment
            )
        )
    }
}