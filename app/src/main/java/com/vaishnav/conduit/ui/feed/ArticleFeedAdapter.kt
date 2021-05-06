package com.vaishnav.conduit.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaishnav.api.models.entities.Article
import com.vaishnav.conduit.databinding.ListItemArticleBinding

class ArticleFeedAdapter :
    ListAdapter<Article, ArticleFeedAdapter.ArticleFeedViewHolder>(ArticleComparator()) {
    class ArticleFeedViewHolder(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                authorTextView.text = article.author.username
                titleTextView.text = article.title
                bodySnippetTextView.text = article.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleFeedViewHolder {
        val binding =
            ListItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleFeedViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    class ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem.toString() == newItem.toString()

    }
}