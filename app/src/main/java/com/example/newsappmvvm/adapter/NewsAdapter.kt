package com.example.newsappmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappmvvm.databinding.ItemArticlePreviewBinding
import com.example.newsappmvvm.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        //Ctrl+i = Implement Methods
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //Check by a unique identity
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            // if areItemsTheSame == true
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = differ.currentList.get(position)
        holder.binding.apply {
            Glide.with(this.root).load(article.urlToImage).into(this.ivArticleImage)
            this.tvSource.text = article.source.name
            this.tvTitle.text = article.title
            this.tvDescription.text = article.description
            this.tvPublishedAt.text = article.publishedAt
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(article)
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemclickListener(listener : ((Article)->Unit)){
        onItemClickListener = listener
    }

    override fun getItemCount() = differ.currentList.size

    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}